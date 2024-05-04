package Forms;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.Objects;

public class VendorForm extends JFrame implements ActionListener {
    
	 private static final Color BROWN = Color.decode("#8B4513");
	// Labels
    JLabel nameLabel = new JLabel("Name*:");
    JLabel addressLabel = new JLabel("Address*:");
    JLabel addressLine2Label = new JLabel("Address Line 2:");
    JLabel cityLabel = new JLabel("City*:");
    JLabel stateLabel = new JLabel("State*:");
    JLabel zipLabel = new JLabel("ZIP*:");
    JLabel emailLabel = new JLabel("Email*:");
    JLabel phoneLabel = new JLabel("Phone*:");
    JLabel contractStatusLabel = new JLabel(""); // Label to display contract file name

    // Text fields
    JTextField nameField = new JTextField(20);
    JTextField addressField = new JTextField(20);
    JTextField addressLine2Field = new JTextField(20);
    JTextField cityField = new JTextField(20);
    JComboBox<String> stateComboBox;
    JTextField zipField = new JTextField(10);
    JTextField emailField = new JTextField(20);
    JFormattedTextField phoneField;

    // Buttons
    JButton contractButton = new JButton("Upload Contract");
    JButton submitButton = new JButton("Submit");

    public VendorForm() {
        // Setting up the frame
        setTitle("Vendor Form");
        setSize(400, 450); // Increased height to accommodate contract status label
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nameLabel.setForeground(BROWN);
        addressLabel.setForeground(BROWN);
        addressLine2Label.setForeground(BROWN);
        cityLabel.setForeground(BROWN);
        zipLabel.setForeground(BROWN);
        stateLabel.setForeground(BROWN);
        emailLabel.setForeground(BROWN);
        phoneLabel.setForeground(BROWN);
        contractStatusLabel.setForeground(BROWN);
        
        
        
        nameField.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        
        nameField.setForeground(Color.GRAY);
        nameField.setBackground(Color.WHITE);
        
        nameField.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(Color.LIGHT_GRAY),
        		BorderFactory.createEmptyBorder(5,10,5,10)));
        
        addressField.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        addressField.setForeground(Color.GRAY);
        addressField.setBackground(Color.WHITE);
        
        addressField.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(Color.LIGHT_GRAY),
        		BorderFactory.createEmptyBorder(5,10,5,10)));
        
        addressLine2Field.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        addressLine2Field.setForeground(Color.GRAY);
        addressLine2Field.setBackground(Color.WHITE);
        
        addressLine2Field.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(Color.LIGHT_GRAY),
        		BorderFactory.createEmptyBorder(5,10,5,10)));
        
        cityField.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        cityField.setForeground(Color.GRAY);
        cityField.setBackground(Color.WHITE);
        
        cityField.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(Color.LIGHT_GRAY),
        		BorderFactory.createEmptyBorder(5,10,5,10)));
        
        zipField.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        zipField.setForeground(Color.GRAY);
        zipField.setBackground(Color.WHITE);
        
        zipField.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(Color.LIGHT_GRAY),
        		BorderFactory.createEmptyBorder(5,10,5,10)));
       
        emailField.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        emailField.setForeground(Color.GRAY);
        emailField.setBackground(Color.WHITE);
        
        emailField.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(Color.LIGHT_GRAY),
        		BorderFactory.createEmptyBorder(5,10,5,10)));
       
     
       
        
        
       
        
       
        
       
        
     
        // Set icon image
        ImageIcon icon = new ImageIcon("src/assets/logoSmall.png");
        setIconImage(icon.getImage());

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
       
        inputPanel.setBackground(Color.WHITE); // Set
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add states to the combo box
        String[] states = {"",
                "AL", "AK", "AZ", "AR", "CA",
                "CO", "CT", "DE", "FL", "GA",
                "HI", "ID", "IL", "IN", "IA",
                "KS", "KY", "LA", "ME", "MD",
                "MA", "MI", "MN", "MS", "MO",
                "MT", "NE", "NV", "NH", "NJ",
                "NM", "NY", "NC", "ND", "OH",
                "OK", "OR", "PA", "RI", "SC",
                "SD", "TN", "TX", "UT", "VT",
                "VA", "WA", "WV", "WI", "WY"};
        stateComboBox = new JComboBox<>(states);

        try {
            MaskFormatter phoneFormatter = new MaskFormatter("(###) ###-####");
            phoneField = new JFormattedTextField(phoneFormatter);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        // Adjust the width of the phone field
        phoneField.setColumns(9); // Increase the number of columns

        // Add components to input panel
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(addressLine2Label, gbc);
        gbc.gridx = 1;
        inputPanel.add(addressLine2Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(cityLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(cityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(stateLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(stateComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(zipLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(zipField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        inputPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(phoneField, gbc);

        // Add contract status label
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        inputPanel.add(contractStatusLabel, gbc);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(contractButton);
        buttonPanel.add(submitButton);

        // Add panels to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Adding action listeners to the buttons
        contractButton.addActionListener(this);
        contractButton.setBackground(Color.RED);
        contractButton.setForeground(Color.RED);
        submitButton.addActionListener(this);
        submitButton.setBackground(Color.RED);
        submitButton.setForeground(Color.RED);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == contractButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("PDF files", "pdf"));
            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                contractStatusLabel.setText("File selected: " + selectedFile.getName());
            }
        } else if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String address = addressField.getText();
            String addressLine2 = addressLine2Field.getText();
            String city = cityField.getText();
            String state = (String) stateComboBox.getSelectedItem();
            String zip = zipField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText().replaceAll("[()\\s-]+", ""); // Remove non-digit characters

            // Check if any required field is empty
            if (name.isEmpty() || address.isEmpty() || city.isEmpty() || Objects.requireNonNull(state).isEmpty() || zip.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please complete required fields.");
                return;
            }

            // Validate email
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(this, "Invalid email format.");
                return;
            }

            // Validate email
            if (!isValidZIP(zip)) {
                JOptionPane.showMessageDialog(this, "Invalid zipcode format.");
                return;
            }

            // Validate phone number
            if (!isValidPhoneNumber(phone)) {
                JOptionPane.showMessageDialog(this, "Invalid phone number format.");
                return;
            }

            // Submission successful
            StringBuilder message = new StringBuilder();
            message.append("Vendor information submitted successfully!\n\n");
            message.append("Name: ").append(name).append("\n");
            message.append("Address: ").append(address).append("\n");
            if (!addressLine2.isEmpty()) {
                message.append("Address Line 2: ").append(addressLine2).append("\n");
            }
            message.append("City: ").append(city).append("\n");
            message.append("State: ").append(state).append("\n");
            message.append("ZIP: ").append(zip).append("\n");
            message.append("Email: ").append(email).append("\n");
            message.append("Phone: ").append(phone).append("\n");
            JOptionPane.showMessageDialog(this, message.toString());

            // Clear fields
            clearFields();
        }
    }

    // Method to validate email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    // Method to validate ZIP code
    private boolean isValidZIP(String zip) {
        String zipRegex = "^\\d{5}$"; // Assumes 5-digit ZIP code format
        return zip.matches(zipRegex);
    }

    // Method to validate phone number
    private boolean isValidPhoneNumber(String phone) {
        String phoneRegex = "^\\d{10}$";
        return phone.matches(phoneRegex);
    }

    public void clearFields() {
        nameField.setText("");
        addressField.setText("");
        addressLine2Field.setText("");
        cityField.setText("");
        stateComboBox.setSelectedIndex(0);
        zipField.setText("");
        emailField.setText("");
        phoneField.setValue(null);
        contractStatusLabel.setText(""); // Clear contract status label
    }

    public static void main(String[] args) {
        // Create and show the vendor form
        new VendorForm();
    }
}
