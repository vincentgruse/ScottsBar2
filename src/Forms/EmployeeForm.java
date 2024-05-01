package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeForm extends JFrame implements ActionListener {
    // Labels
    JLabel firstNameLabel = new JLabel("First Name:");
    JLabel lastNameLabel = new JLabel("Last Name:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel departmentLabel = new JLabel("Department:");
    JLabel supervisorLabel = new JLabel("Supervisor:");

    // Text fields
    JTextField firstNameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField departmentField = new JTextField();
    JTextField supervisorField = new JTextField();

    // Button
    JButton signUpButton = new JButton("Submit");

    public EmployeeForm() {
        // Setting up the frame
        setTitle("Employee Sign Up Form");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 3));

        // Adding components to the frame
        add(firstNameLabel);
        add(firstNameField);
        add(lastNameLabel);
        add(lastNameField);
        add(passwordLabel);
        add(passwordField);
        add(departmentLabel);
        add(departmentField);
        add(supervisorLabel);
        add(supervisorField);
        add(new JLabel()); // Empty space
        add(signUpButton);

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

    public static void main(String[] args) {
        // Create and show the sign-up form
        new EmployeeForm();
    }
}
