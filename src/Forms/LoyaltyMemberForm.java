package Forms;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class LoyaltyMemberForm extends JFrame implements ActionListener {
    // Labels
    JLabel firstNameLabel = new JLabel("First Name*:");
    JLabel lastNameLabel = new JLabel("Last Name*:");
    JLabel phoneLabel = new JLabel("Phone Number*:");
    JLabel emailLabel = new JLabel("Email*:");

    // Text fields and formatted text field for phone number
    JTextField firstNameField = new JTextField(20);
    JTextField lastNameField = new JTextField(20);
    JFormattedTextField phoneField;
    JTextField emailField = new JTextField(20);

    // Button
    JButton submitButton = new JButton("Submit");

    public LoyaltyMemberForm() {
        // Setting up the frame
        setTitle("New Loyalty Member Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstNameLabel.setForeground(Color.RED); 
        lastNameLabel.setForeground(Color.RED);
        phoneLabel.setForeground(Color.RED);
        emailLabel.setForeground(Color.RED); 

        // Set icon image
        ImageIcon icon = new ImageIcon("src/assets/logoSmall.png");
        setIconImage(icon.getImage());

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        Color lightOrange = new Color(255, 128, 0);
        inputPanel.setBackground(lightOrange); // Set background color to orange
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        try {
            MaskFormatter phoneFormatter = new MaskFormatter("(###) ###-####");
            phoneField = new JFormattedTextField(phoneFormatter);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        // Adjust the width of the phone field
        phoneField.setColumns(9); // Increase the number of columns

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
        inputPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(emailField, gbc);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(lightOrange);
        buttonPanel.add(submitButton);

        // Add panels to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Adding action listener to the button
        submitButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Retrieve values from fields
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String phoneNumber = phoneField.getText().replaceAll("[()\\s-]+", ""); // Remove non-digit characters
            String email = emailField.getText();

            // Check for empty fields
            if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please complete all required fields.");
                return;
            }

            // Validate phone number
            if (!isValidPhoneNumber(phoneNumber)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid phone number.");
                return;
            }

            // Validate email
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
                return;
            }

            // Submission successful
            String message = "New loyalty member submitted successfully!\n\n" +
                    "First Name: " + firstName + "\n" +
                    "Last Name: " + lastName + "\n" +
                    "Phone Number: " + phoneNumber + "\n" +
                    "Email: " + email + "\n";
            JOptionPane.showMessageDialog(this, message);

            // Clear fields
            clearFields();
        }
    }



    private boolean isValidPhoneNumber(String phoneNumber) {
        // Simple phone number validation: check if the length is 10 digits
        return phoneNumber.matches("\\d{10}");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setValue(null);
        emailField.setText("");
    }

    public static void main(String[] args) {
        // Create and show the new loyalty member form
        new LoyaltyMemberForm();
    }
}
