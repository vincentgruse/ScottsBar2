package Login;

import Dashboard.AdminDashboard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static Helper.DatabaseHelper.setup;

public class Login extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    // private Authorization authorization;
    private static final Color DARK_BROWN = Color.decode("#733B38");
    private static final Color WHITE = Color.decode("#FFFFFF");
    private JButton submitButton;
    private static final String ASSETS_PATH = "src/assets/";

    public Login() {
        setup();
        // authorization = new Authorization();
        setTitle("Login Form");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Create panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add Employee login title
        JLabel loginTitle = new JLabel("Employee Login");
        loginTitle.setForeground(DARK_BROWN); // Set brown color
        loginTitle.setFont(new Font("Arial", Font.BOLD, 20)); // Set font and size
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns
        panel.add(loginTitle, gbc);

        // Add username label and field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.RED);
        usernameField = new JTextField(15);
        usernameField.setText("USERNAME");
        usernameField.setForeground(Color.GRAY);

        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        usernameField.setForeground(Color.GRAY);
        usernameField.setBackground(Color.WHITE);
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        // Set Text Color to Gray
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("USERNAME")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("USERNAME");
                    usernameField.setForeground(Color.GRAY);
                }

            }

        });

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(usernameField, gbc);

        // Add password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 2;

        passwordField = new JPasswordField(15);
        passwordField.setText("PASSWORD");
        passwordField.setForeground(Color.GRAY);
        passwordField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("PASSWORD")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("PASSWORD");
                    passwordField.setForeground(Color.GRAY);
                }

            }
        });
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        passwordField.setForeground(Color.GRAY);
        passwordField.setBackground(Color.WHITE);

        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        // Add submit button
        submitButton = new JButton("LOGIN");
        submitButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);
        panel.setBackground(WHITE);

        // Create Panel for Picture
        JPanel picturePanel = new JPanel(new BorderLayout());
        JLabel pictureLabel = new JLabel(new ImageIcon(ASSETS_PATH + "front.png"));
        picturePanel.add(pictureLabel, BorderLayout.CENTER);
        Color lightOrange = new Color(255, 128, 0);
        picturePanel.setBackground(lightOrange);

        // Create Main Panel for BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(picturePanel, BorderLayout.WEST);
        mainPanel.setBackground(lightOrange);

        add(mainPanel);
        pack();

        setLocationRelativeTo(null);

    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        if (username.equals("admin") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            setVisible(false);

            AdminDashboard adminDashboard = new AdminDashboard();
            adminDashboard.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
