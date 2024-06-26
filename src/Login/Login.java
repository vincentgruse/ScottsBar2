package Login;

import Dashboard.AdminDashboard;
import Helper.CommonHelper;
import Services.Authorization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static Helper.DatabaseHelper.setup;

public class Login extends JFrame implements ActionListener{
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    private Authorization authorization;

    public Login() {
        setup();
        authorization = new Authorization();
        setTitle("Login Form");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set icon image
        ImageIcon icon = new ImageIcon("src/assets/logoSmall.png");
        setIconImage(icon.getImage());

        // Create panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add username label and field
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        // Add password label and field
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        // Add submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        // Add panel to JFrame
        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getText());

        // For demonstration, just print the username and password
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        //Admin user case
        if (username.equals("admin") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            setVisible(false);
            CommonHelper.username = "admin";
            AdminDashboard adminDashboard = new AdminDashboard();
            adminDashboard.setVisible(true);
        }
        else {
            if (authorization.login(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                setVisible(false);
                CommonHelper.username = username;

                AdminDashboard adminDashboard = new AdminDashboard();
                adminDashboard.setVisible(true);
            }
            else
                JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
