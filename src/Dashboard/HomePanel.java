package Dashboard;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import static Dashboard.AdminDashboard.userStatus;

public class HomePanel {
    // Constants for colors, border thickness, and assets path
    public static final Color ORANGE = Color.decode("#F0C28E");
    public static final Color DARK_BROWN = Color.decode("#733B38");
    public static final int BORDER_THICKNESS = 3;

    // Method to create the Home buttons panel
    public static JPanel createHomeButtonsPanel() {
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
                "transactions.png"
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
            if (userStatus == 1) {
                JButton button = createHomeButton(homeButtonNames[i], homeButtonIcons[i], formClasses[i]);
                homeButtonsPanel.add(button);
            }
            else {
                if (!(homeButtonNames[i].contains("Employee") || homeButtonNames[i].contains("Department"))) {
                    JButton button = createHomeButton(homeButtonNames[i], homeButtonIcons[i], formClasses[i]);
                    homeButtonsPanel.add(button);
                }
            }
        }

        return homeButtonsPanel;
    }

    // Method to create a Home button with specified name, icon, and form class name
    private static JButton createHomeButton(String name, String iconName, String formClassName) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(HomePanel.class.getResource("/assets/" + iconName)));
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

    // Method to create the Home panel
    public static JPanel createHomePanel() {
        JPanel homePanel = new JPanel(new BorderLayout());

        // Creating the title label
        JLabel titleLabel = new JLabel("Home");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Creating the buttons panel
        JPanel buttonsPanel = createHomeButtonsPanel();

        // Adding components to the home panel
        homePanel.add(titleLabel, BorderLayout.NORTH);
        homePanel.add(buttonsPanel, BorderLayout.CENTER);

        return homePanel;
    }
}
