package Dashboard;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

import Login.Login;

public class AdminDashboard extends JFrame {
    // Constants for colors, border thickness, and assets path
    public static final Color LIGHT_BROWN = Color.decode("#F2E9E2");
    public static final Color DARK_BROWN = Color.decode("#733B38");
    public static final Color ORANGE = Color.decode("#F0C28E");
    public static final int BORDER_THICKNESS = 3;
    public static final int TAB_WIDTH = 250; // Fixed width of the tabs
    public static final int TAB_HEIGHT = 100; // Fixed height of the tabs
    public static final String ASSETS_PATH = "src/assets/";

    // Array of tab names and corresponding icon names
    private final String[] tabNames = {
            "Home",
            "Employees",
            "Departments",
            "Products",
            "Customers",
            "Vendors",
            "Transactions",
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

        // Create title panel
        JPanel titlePanel = createTitlePanel();

        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setBackground(LIGHT_BROWN);

        // Set custom UI delegate for tabbed pane to customize tab appearance
        tabbedPane.setUI(new BasicTabbedPaneUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                tabInsets = new Insets(10, 10, 10, 10); // Adjust tab insets
            }

            @Override
            protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
                g.setColor(DARK_BROWN); // Set border color
                g.drawRect(x, y, w, h); // Draw border
            }

            @Override
            protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
                return TAB_WIDTH; // Fixed tab width
            }

            @Override
            protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
                return TAB_HEIGHT; // Fixed tab height
            }
        });

        // Create tabs and add them to the tabbed pane
        for (String tabName : tabNames) {
            ImageIcon tabIcon = new ImageIcon(ASSETS_PATH + tabName.toLowerCase() + ".png");
            tabbedPane.addTab(tabName, tabIcon, createPanel(tabName));
        }

        // Add title panel and tabbed pane to the frame
        getContentPane().add(titlePanel, BorderLayout.NORTH);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Set the Home tab as active by default
        tabbedPane.setSelectedIndex(0);
    }

    // Method to create a panel corresponding to each tab
    private JPanel createPanel(String tabName) {
        return switch (tabName) {
            case "Home" -> HomePanel.createHomePanel();
            case "Employees" -> EmployeePanel.createEmployeePanel();
            case "Departments" -> DepartmentPanel.createDepartmentPanel();
            case "Products" -> ProductPanel.createProductPanel();
            case "Customers" -> CustomerPanel.createCustomerPanel();
            case "Vendors" -> VendorPanel.createVendorPanel();
            case "Transactions" -> TransactionPanel.createTransactionPanel();
            default -> new JPanel(); // Create empty panel for other tabs for now
        };
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

    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminDashboard().setVisible(true); // Create and show the admin dashboard frame
        });
    }
}