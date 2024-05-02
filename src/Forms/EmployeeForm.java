package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeForm extends JFrame implements ActionListener {
    // Labels
    JLabel firstNameLabel = new JLabel("First Name*:");
    JLabel lastNameLabel = new JLabel("Last Name*:");
    JLabel passwordLabel = new JLabel("Password*:");
    JLabel departmentLabel = new JLabel("Department*:");
    JLabel supervisorLabel = new JLabel("Supervisor*:");

    // Text fields
    JTextField firstNameField = new JTextField(20);
    JTextField lastNameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JTextField departmentField = new JTextField(20);
    JTextField supervisorField = new JTextField(20);

    // Button
    JButton signUpButton = new JButton("Submit");

    public EmployeeForm() {
        // Setting up the frame
        setTitle("Employee Sign Up Form");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set icon image
        ImageIcon icon = new ImageIcon("src/assets/logoSmall.png");
        setIconImage(icon.getImage());

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to input panel
        inputPanel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(departmentLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(departmentField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(supervisorLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(supervisorField, gbc);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(signUpButton);

        // Add panels to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Adding action listener to the button
        signUpButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String password = new String(passwordField.getPassword());
            String department = departmentField.getText();
            String supervisor = supervisorField.getText();

            // Check if any required field is empty
            if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || department.isEmpty() || supervisor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            // Validate password
            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(this, "Password must be 8-32 characters long and include at least one number, one capital letter, and one symbol.");
                return;
            }

            // Generate username
            String username = generateUsername(firstName, lastName);

            // Generate email
            String email = username + "@scottsbar2.com";

            // Displaying the entered details, generated username, and email
            String message = "Username: " + username + "\n" +
                    "First Name: " + firstName + "\n" +
                    "Last Name: " + lastName + "\n" +
                    "Email: " + email + "\n" +
                    "Department: " + department + "\n" +
                    "Supervisor: " + supervisor + "\n";

            JOptionPane.showMessageDialog(this, message);

            // Clearing the fields
            clearFields();
        }
    }

    // Method to generate username
    private String generateUsername(String firstName, String lastName) {
        String username;
        if (lastName.length() >= 5) {
            username = lastName.substring(0, 5) + firstName.charAt(0);
        } else {
            username = lastName + firstName.charAt(0);
        }
        return username.toLowerCase();
    }

    // Method to validate password
    private boolean isValidPassword(String password) {
        // Password must be 8-32 characters long and include at least one number, one capital letter, and one of the specified symbols
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()])(?=\\S+$).{8,32}$";
        return password.matches(passwordRegex);
    }

    // Method to clear fields
    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        passwordField.setText("");
        departmentField.setText("");
        supervisorField.setText("");
    }

    public static void main(String[] args) {
        // Create and show the sign-up form
        new EmployeeForm();
    }
}