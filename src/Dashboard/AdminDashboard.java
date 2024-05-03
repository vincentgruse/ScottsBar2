package Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import Forms.*;
import Login.Login;

public class AdminDashboard extends JFrame {
    // Constants for colors, border thickness, and assets path
    public static final Color LIGHT_BROWN = Color.decode("#F2E9E2");
    public static final Color DARK_BROWN = Color.decode("#733B38");
    public static final Color ORANGE = Color.decode("#F0C28E");
    public static final int BORDER_THICKNESS = 3;
    public static final String ASSETS_PATH = "src/assets/";

    // Panels and buttons
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JButton selectedButton;

    // Array of button names and corresponding icon names
    private final String[] buttonNames = {
            "Home",
            "Employees",
            "Departments",
            "Products",
            "Customers",
            "Vendors",
            "Customer Ledger",
            "Graphs"
    };

    // Constructor to initialize the UI
    public AdminDashboard() {
        initializeUI();
    }

    // Method to initialize the UI components
    private void initializeUI() {
        setTitle("Admin Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to fullscreen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set icon image
        ImageIcon icon = new ImageIcon("src/assets/logoSmall.png");
        setIconImage(icon.getImage());

        // Create title panel, button panel, and content panel
        titlePanel = createTitlePanel();
        buttonPanel = createButtonPanel();
        JPanel contentPanel = createContentPanel();

        // Add content panel to the frame
        add(contentPanel);

        // Set the Home tab as active by default
        setDefaultButton();
    }

    // Method to set a default button as active
    private void setDefaultButton() {
        // Find the index of the default button
        int defaultButtonIndex = -1;
        for (int i = 0; i < buttonNames.length; i++) {
            if (buttonNames[i].equals("Home")) {
                defaultButtonIndex = i;
                break;
            }
        }

        // If the default button is found, set it as active
        if (defaultButtonIndex != -1) {
            JButton defaultButton = (JButton) buttonPanel.getComponent(defaultButtonIndex);
            defaultButton.setBackground(Color.decode("#F0C28E"));
            selectedButton = defaultButton;
        }
    }

    // Method to create the title panel
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(DARK_BROWN);
        titlePanel.setBorder(BorderFactory.createLineBorder(DARK_BROWN, BORDER_THICKNESS));

        // Create logo and text label
        JPanel leftPanel = getjPanel();

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

    private static JPanel getjPanel() {
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
        return leftPanel;
    }

    // Method to create the button panel
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(8, 1, 10, -BORDER_THICKNESS));

        // Array of corresponding icon names
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
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Change content panel content here according to the clicked button
                    // For now, let's just print the button name
                    System.out.println("Clicked: " + buttonNames[finalI]);

                    // Change the appearance of the clicked button
                    if (selectedButton != null) {
                        selectedButton.setBackground(LIGHT_BROWN);
                    }
                    button.setBackground(ORANGE);
                    selectedButton = button;
                }
            });
            buttonPanel.add(button);
        }

        return buttonPanel;
    }

    // Method to create a button with specified name and icon
    private JButton createButton(String name, String iconName) {
        ImageIcon icon = new ImageIcon(ASSETS_PATH + iconName);
        JButton button = new JButton(name, icon);
        Dimension buttonSize = new Dimension(250, 250);
        button.setPreferredSize(buttonSize);
        button.setBackground(LIGHT_BROWN);
        button.setForeground(DARK_BROWN);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Remove default border
        return button;
    }

    // Method to create the main content panel
    public JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(titlePanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.WEST);

        // Placeholder panel for dynamic content
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Home");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50)); // Setting title font
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centering the title label
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel homeButtonsPanel = createHomeButtonsPanel(); // Creating the Home page buttons panel
        mainPanel.add(homeButtonsPanel, BorderLayout.CENTER);

        contentPanel.add(mainPanel, BorderLayout.CENTER);

        return contentPanel;
    }

    private JPanel createHomeButtonsPanel() {
        JPanel homeButtonsPanel = new JPanel(new GridLayout(2, 3, 10, 10)); // 2x3 grid layout for buttons
        homeButtonsPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100)); // Add padding around the buttons

        // Array of button names and corresponding icon names for the Home page
        String[] homeButtonNames = {
                "+Add Employee",
                "+Add Department",
                "+Add Product",
                "+Add Loyalty Member",
                "+Add Vendor",
                "+Add Transaction"
        };
        String[] homeButtonIcons = {
                "employees.png",
                "departments.png",
                "products.png",
                "customers.png",
                "vendors.png",
                "ledger.png"
        };
        String[] formClasses = {
                "EmployeeForm",
                "DepartmentForm",
                "ProductForm",
                "LoyaltyMemberForm",
                "VendorForm",
                "TransactionForm"
        };

        // Create buttons and add them to the home buttons panel
        for (int i = 0; i < homeButtonNames.length; i++) {
            JButton button = createHomeButton(homeButtonNames[i], homeButtonIcons[i], formClasses[i]);
            homeButtonsPanel.add(button);
        }

        return homeButtonsPanel;
    }


    private JButton createHomeButton(String name, String iconName, String formClassName) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/" + iconName)));
        JButton button = new JButton(name, icon);
        button.setFont(button.getFont().deriveFont(18f));
        Dimension buttonSize = new Dimension(250, 250);
        button.setPreferredSize(buttonSize);
        button.setBackground(ORANGE);
        button.setForeground(DARK_BROWN);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(DARK_BROWN, BORDER_THICKNESS));

        // Add action listener to open the corresponding form
        button.addActionListener(e -> {
            try {
                // Dynamically create an instance of the form class
                Class<?> formClass = Class.forName("Forms." + formClassName);
                Object formObject = formClass.getDeclaredConstructor().newInstance();
                if (formObject instanceof JFrame formFrame) {
                    formFrame.setVisible(true);
                    formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame on exit
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid form class", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error opening form", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return button;
    }

    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminDashboard().setVisible(true); // Create and show the admin dashboard frame
        });
    }
}
