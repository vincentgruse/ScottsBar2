package src;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    // Constants for colors, border thickness, and assets path
    private static final Color LIGHT_BROWN = Color.decode("#F2E9E2");
    private static final Color DARK_BROWN = Color.decode("#733B38");
    private static final int BORDER_THICKNESS = 3;
    private static final String ASSETS_PATH = "src/assets/";

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
        JPanel contentPanel = createContentPanel(titlePanel, buttonPanel);

        // Add content panel to the frame
        add(contentPanel);
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
            buttonPanel.add(button);
        }

        return buttonPanel;
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
        contentPanel.add(new JPanel(), BorderLayout.CENTER); // Placeholder panel for future content
        return contentPanel;
    }

    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminDashboard().setVisible(true); // Create and show the admin dashboard frame
        });
    }
}
