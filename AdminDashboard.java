import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {
    // Constants for colors, border thickness, and assets path
    private static final Color LIGHT_BROWN = Color.decode("#F2E9E2");
    private static final Color DARK_BROWN = Color.decode("#733B38");
    private static final Color TAN = Color.decode("#c49b66");
    private static final int BORDER_THICKNESS = 3;
    private static final String ASSETS_PATH = "src/assets/";

    // Panels for different content
    private JPanel homePanel;
    private JPanel employeesPanel;
    private JPanel departmentsPanel;
    private JPanel productsPanel;
    private JPanel customersPanel;
    private JPanel vendorsPanel;
    private JPanel customerledgerPanel;
    private JPanel graphsPanel;

    // Add more panels for other content types as needed...
    
    // CardLayout to manage content switching
    private CardLayout cardLayout;
    private JPanel contentPanel;


    // Constructor to initialize the UI
    public AdminDashboard() {
        initializeUI();
    }

    // Method to initialize the UI components
    private void initializeUI() {
        setTitle("Admin Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to fullscreen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create title panel, button panel, and content panel
        JPanel titlePanel = createTitlePanel();
        JPanel buttonPanel = createButtonPanel();
        contentPanel = new JPanel(); // Content panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        // Initialize content panels
        homePanel = createHomePanel();
        employeesPanel = createEmployeesPanel();
        departmentsPanel = createDepartmentsPanel();
        productsPanel = createProductsPanel();
        customersPanel = createCustomerPanel();
        vendorsPanel = createVendorsPanel();
        customerledgerPanel = createCustomerLedgerPanel();
        graphsPanel = createGraphsPanel();

        // Initialize more content panels for other types as needed...

        // Add content panels to the content panel with unique names
        contentPanel.add(homePanel, "home");
        contentPanel.add(employeesPanel, "employees");
        contentPanel.add(departmentsPanel, "departments");
        contentPanel.add(productsPanel, "products");
        contentPanel.add(customersPanel, "customers");
        contentPanel.add(vendorsPanel, "vendors");
        contentPanel.add(customerledgerPanel, "customer ledger");
        contentPanel.add(graphsPanel, "graphs");


        // Add title panel, button panel, and content panel to the frame
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    // Method to create the title panel
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(DARK_BROWN);
        titlePanel.setBorder(BorderFactory.createLineBorder(DARK_BROWN, BORDER_THICKNESS));

        // Create logo and text label
        ImageIcon imageIcon = new ImageIcon(ASSETS_PATH + "logoSmall.png");
        JLabel imageLabel = new JLabel(imageIcon);
        JLabel textLabel = new JLabel("Admin Dashboard");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        textLabel.setForeground(LIGHT_BROWN);

        // Create left panel with logo and text
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(DARK_BROWN);
        leftPanel.add(imageLabel);
        leftPanel.add(textLabel);

        // Add left panel to the left side of the title bar
        titlePanel.add(leftPanel, BorderLayout.WEST);

        // Create and add logout button to the right side of the title bar
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.decode("#EA564E"));
        logoutButton.setForeground(Color.white);
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(e -> {
            setVisible(false); // Hide the current frame
            new Login().setVisible(true); // Show the login frame
        });
        titlePanel.add(logoutButton, BorderLayout.EAST);

        return titlePanel;
    }

  // Method to create the button panel
private JPanel createButtonPanel() {
    JPanel buttonPanel = new JPanel(new GridLayout(8, 1, 10, -BORDER_THICKNESS));
    JButton[] buttons = new JButton[8]; // Array to store buttons

    // Array of button names and corresponding icon names
    String[] buttonNames = {
            "Home",
            "Employees",
            "Departments",
            "Products",
            "Customers",
            "Vendors",
            "Customer Ledger",
            "Graphs"
    };
    String[] iconNames = {
            "home.png",
            "employees.png",
            "departments.png",
            "products.png",
            "customers.png",
            "vendors.png",
            "ledger.png",
            "graphs.png"
    };

    // Create buttons and add them to the button panel
    for (int i = 0; i < buttonNames.length; i++) {
        JButton button = createButton(buttonNames[i], iconNames[i]);
        int index = i; // Capture the current index

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set TAN color for the clicked button
                button.setBackground(TAN);

                // Reset other buttons to LIGHT_BROWN
                for (int j = 0; j < buttons.length; j++) {
                    if (j != index) {
                        buttons[j].setBackground(LIGHT_BROWN);
                    }
                }
                
                // Call switchToPanel method to switch to the corresponding content panel
                switchToPanel(buttonNames[index].toLowerCase()); // Switch to panel based on button name
            }
        });

        buttons[i] = button; // Store the button in the array
        buttonPanel.add(button);
    }

    return buttonPanel;
}

        // Method to create the home panel
        private JPanel createHomePanel() {
            // Create and customize the home panel
            JPanel panel = new JPanel();
            panel.setBackground(Color.BLACK); // Example background color
            // Add components specific to the home panel
            return panel;
        }
    
        // Method to create the employees panel
        private JPanel createEmployeesPanel() {
            // Create and customize the employees panel
            JPanel panel = new JPanel();
            panel.setBackground(Color.BLUE); // Example background color
            // Add components specific to the employees panel
            return panel;
        }

        // Method to create the departments panel
        private JPanel createDepartmentsPanel() {
            // Create and customize the departments panel
            JPanel panel = new JPanel();
            panel.setBackground(Color.RED); // Example background color
            // Add components specific to the departments panel
            return panel;
        }

        // Method to create the Products panel
        private JPanel createProductsPanel() {
            // Create and customize the home panel
            JPanel panel = new JPanel();
            panel.setBackground(Color.GREEN); // Example background color
            // Add components specific to the home panel
            return panel;
        }

        // Method to create the Customer panel
        private JPanel createCustomerPanel() {
            // Create and customize the home panel
            JPanel panel = new JPanel();
            panel.setBackground(Color.ORANGE); // Example background color
            // Add components specific to the home panel
            return panel;
        }

        // Method to create the Vendors panel
        private JPanel createVendorsPanel() {
            // Create and customize the home panel
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE); // Example background color
            // Add components specific to the home panel
            return panel;
        }

        // Method to create the customerledger panel
        private JPanel createCustomerLedgerPanel() {
            // Create and customize the home panel
            JPanel panel = new JPanel();
            panel.setBackground(Color.GRAY); // Example background color
            // Add components specific to the home panel
            return panel;
        }        

        // Method to create the graphs panel
        private JPanel createGraphsPanel() {
            // Create and customize the home panel
            JPanel panel = new JPanel();
            panel.setBackground(Color.YELLOW); // Example background color
            // Add components specific to the home panel
            return panel;
        }

        // Method to switch to the specified content panel
        private void switchToPanel(String panelName) {
            cardLayout.show(contentPanel, panelName);
        }
      
      
    // Method to create a button with specified name and icon
    private JButton createButton(String name, String iconName) {
        ImageIcon icon = new ImageIcon(ASSETS_PATH + iconName);
        JButton button = new JButton(name, icon);
        Dimension buttonSize = new Dimension(300, 75);
        button.setPreferredSize(buttonSize);
        button.setBackground(LIGHT_BROWN);
        button.setForeground(DARK_BROWN);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(DARK_BROWN, BORDER_THICKNESS));
        return button;
    }
    

    // Method to create the main content panel
    private JPanel createContentPanel(JPanel titlePanel, JPanel buttonPanel) {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(titlePanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.WEST);
        //contentPanel.add(new JPanel(), BorderLayout.CENTER); // Placeholder panel for future content
        
        
        //This changes the content of the middle
        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(Color.BLACK); // Example background color
        // Add your content components to the middle panel here
        
        contentPanel.add(middlePanel, BorderLayout.CENTER);
        //

        return contentPanel;
    }

    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminDashboard().setVisible(true); // Create and show the admin dashboard frame
        });
    }
}
