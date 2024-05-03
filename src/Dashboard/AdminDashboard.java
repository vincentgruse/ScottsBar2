package Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Login.Login;

import static Dashboard.HomePanel.createHomePanel;

public class AdminDashboard extends JFrame {
    // Constants for colors, border thickness, and assets path
    public static final Color LIGHT_BROWN = Color.decode("#F2E9E2");
    public static final Color DARK_BROWN = Color.decode("#733B38");
    public static final Color ORANGE = Color.decode("#F0C28E");
    public static final int BORDER_THICKNESS = 3;
    public static final String ASSETS_PATH = "src/assets/";

    private JPanel buttonPanel;
    private JButton selectedButton;
    private JPanel activePanel;
    private JLabel titleLabel;

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
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set icon image
        ImageIcon icon = new ImageIcon("src/assets/logoSmall.png");
        setIconImage(icon.getImage());

        // Create title panel and button panel
        // Panels and buttons
        JPanel titlePanel = createTitlePanel();
        buttonPanel = createButtonPanel();

        // Create split pane to divide the frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonPanel, createHomePanel());
        splitPane.setDividerLocation(300); // Set initial width of the button panel

        // Add title panel and split pane to the frame
        getContentPane().add(titlePanel, BorderLayout.NORTH);
        getContentPane().add(splitPane, BorderLayout.CENTER);

        // Set the Home tab as active by default
        setDefaultButton();
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
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    System.out.println("Button clicked: " + clickedButton.getText()); // Log the clicked button text
                    if (selectedButton != null) {
                        selectedButton.setBackground(LIGHT_BROWN);
                    }
                    clickedButton.setBackground(ORANGE);
                    selectedButton = clickedButton;
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

    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminDashboard().setVisible(true); // Create and show the admin dashboard frame
        });
    }
}
