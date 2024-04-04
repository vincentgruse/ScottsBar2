import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Need an import from the MainWindow class

public class Login extends JFrame implements ActionListener{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submitButton;



    public Login() {
        setTitle("Login Form");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 800;
        int height = 500;

        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the JFrame on the screen

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
        submitButton = new JButton("Submit");
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
        String password = new String(passwordField.getPassword());

        // For demonstration, just print the username and password
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // Here, you can perform the authentication logic
        // For simplicity, let's assume correct username is "admin" and password is "password"
        if (username.equals("admin") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            setVisible(false);

            // This will run the actual program
            // MainWindow mainWinder = new MainWindow();
            // mainWindow.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}
