package Forms;

import Entities.Department;
import Entities.Vendor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.Objects;

public class VendorForm extends JFrame implements ActionListener {
    // Labels
    JLabel nameLabel = new JLabel("Name*:");
    JLabel addressLabel = new JLabel("Address*:");
    JLabel typeLabel = new JLabel("Type*:");
    JLabel emailLabel = new JLabel("Email*:");
    JLabel phoneLabel = new JLabel("Phone*:");
    JLabel privateSSNLabel = new JLabel("Private SSN*:");
    JLabel contractStatusLabel = new JLabel(""); // Label to display contract file name
    int contractNumber = -1;

    // Text fields
    JTextField nameField = new JTextField(20);
    JTextField addressField = new JTextField(20);
    JTextField emailField = new JTextField(20);
    JComboBox<String> typeDropdown = new JComboBox<>();
    JTextField phoneField = new JTextField(20);
    JTextField privateSSNField = new JTextField(20);

    // Buttons
    JButton contractButton = new JButton("Upload Contract");
    JButton submitButton = new JButton("Submit");

    public VendorForm() {
        populateTypeDropdown();
        // Setting up the frame
        setTitle("Vendor Form");
        setSize(400, 450); // Increased height to accommodate contract status label
        // Set default close operation to dispose on close
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set icon image
        ImageIcon icon = new ImageIcon("src/assets/logoSmall.png");
        setIconImage(icon.getImage());

        // Calculate the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        typeDropdown.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) e.getItem();
                // Perform actions based on the selected item
                System.out.println("Selected item: " + selectedItem);
                switch (selectedItem) {
                    case "Private":
                        contractButton.setVisible(false);
                        privateSSNLabel.setVisible(true);
                        privateSSNField.setVisible(true);
                        break;
                    case "Business":
                        contractButton.setVisible(true);
                        privateSSNLabel.setVisible(false);
                        privateSSNField.setVisible(false);
                }
            }
        });

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

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
        inputPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(typeLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(typeDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(privateSSNLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(privateSSNField, gbc);

        // Add contract status label
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        inputPanel.add(contractStatusLabel, gbc);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(contractButton);
        buttonPanel.add(submitButton);

        // Add panels to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Adding action listeners to the buttons
        contractButton.addActionListener(this);
        submitButton.addActionListener(this);

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
                long currentTimeMillis = System.currentTimeMillis();
                int currentTimeSeconds = (int) (currentTimeMillis / 1000);
                contractNumber = currentTimeSeconds;
            }
        } else if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String address = addressField.getText();
            String type = typeDropdown.getSelectedItem().toString() ;
            String email = emailField.getText();
            String phone = phoneField.getText().replaceAll("[()\\s-]+", ""); // Remove non-digit characters

            // Check if any required field is empty
            if (name.isEmpty() || address.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please complete required fields.");
                return;
            }

            // Validate email
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(this, "Invalid email format.");
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
            message.append("Email: ").append(email).append("\n");
            message.append("Phone: ").append(phone).append("\n");
            message.append("Type: ").append(type).append("\n");

            Vendor vendor = new Vendor();
            vendor.vendorName = name;
            vendor.vendorEmail = email;
            vendor.vendorPhoneNumber = phone;
            vendor.vendorAddress = address;
            switch (type) {
                case "Private":
                    vendor.type = "Private";
                    vendor.typeID = Integer.valueOf(privateSSNField.getText());
                    break;
                case "Business":
                    vendor.type = "Business";
                    vendor.typeID = contractNumber;
            }
            vendor.insertVendor(vendor);

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

    // Method to validate phone number
    private boolean isValidPhoneNumber(String phone) {
        String phoneRegex = "^\\d{10}$";
        return phone.matches(phoneRegex);
    }

    public void clearFields() {
        nameField.setText("");
        addressField.setText("");
        emailField.setText("");
        phoneField.setText("");
        privateSSNField.setText("");
        contractStatusLabel.setText(""); // Clear contract status label
    }

    public void populateTypeDropdown() {
        typeDropdown.addItem("Private");
        typeDropdown.addItem("Business");
    }

    public static void main(String[] args) {
        // Create and show the vendor form
        new VendorForm();
    }
}
