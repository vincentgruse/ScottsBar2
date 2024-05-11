package Forms;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

import Entities.LoyaltyMember;
import Entities.Customer;

public class LoyaltyMemberForm extends JFrame implements ActionListener {
    // Labels
    private static final Color BROWN = Color.decode("#8B4513");
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
        // Set default close operation to dispose on close
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        firstNameLabel.setForeground(BROWN);
        lastNameLabel.setForeground(BROWN);
        phoneLabel.setForeground(BROWN);
        emailLabel.setForeground(BROWN);

        // Set icon image
        ImageIcon icon = new ImageIcon("src/assets/logoSmall.png");
        setIconImage(icon.getImage());

        firstNameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        firstNameField.setForeground(Color.GRAY);
        firstNameField.setBackground(Color.WHITE);

        firstNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        lastNameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        lastNameField.setForeground(Color.GRAY);
        lastNameField.setBackground(Color.WHITE);

        lastNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        // Calculate the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
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
        submitButton.setBackground(Color.RED);
        submitButton.setForeground(Color.RED);
        buttonPanel.add(submitButton);
        // buttonPanel.setBackground(Color.WHITE);

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

            Customer customer = new Customer();
            long customerId = customer.insertCustomer(customer);

            LoyaltyMember loyaltyMember = new LoyaltyMember();
            loyaltyMember.customerID = customerId;
            loyaltyMember.firstName = firstName;
            loyaltyMember.lastName = lastName;
            loyaltyMember.email = email;
            loyaltyMember.phoneNumber = phoneNumber;
            loyaltyMember.insertLoyaltyMember(loyaltyMember);

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