package Forms;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ProductForm extends JFrame implements ActionListener {
    // Labels
    JLabel brandLabel = new JLabel("Brand*:");
    JLabel categoryLabel = new JLabel("Category*:");
    JLabel descriptionLabel = new JLabel("Description*:");
    JLabel costLabel = new JLabel("Cost*: $");
    JLabel srpLabel = new JLabel("SRP*: $");
    JLabel vendorLabel = new JLabel("Vendor*:");
    JLabel quantityLabel = new JLabel("Quantity*:");

    // Text fields, combo box, and spinner
    JTextField brandField = new JTextField(20);
    JComboBox<String> categoryComboBox;
    JTextArea descriptionTextArea = new JTextArea(5, 20); // Larger text area for description
    JSpinner costSpinner;
    JSpinner srpSpinner;
    JTextField vendorField = new JTextField(20);
    JSpinner quantitySpinner;

    // Button
    JButton submitButton = new JButton("Submit");

    public ProductForm() {
        // Setting up the frame
        setTitle("New Product Form");
        setSize(400, 400);
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

        // Add categories to the combo box
        String[] categories = {"", "Food", "Alcohol", "Misc."};
        categoryComboBox = new JComboBox<>(categories);

        // Spinner for quantity
        SpinnerModel quantityModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
        quantitySpinner = new JSpinner(quantityModel);
        JFormattedTextField quantityTextField = ((JSpinner.DefaultEditor) quantitySpinner.getEditor()).getTextField();
        quantityTextField.setColumns(5); // Adjust the width as needed

        // Create currency formatter
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        NumberFormatter currencyFormatter = new NumberFormatter(currencyFormat);
        currencyFormatter.setAllowsInvalid(false);

        // Spinner for cost
        SpinnerModel costModel = new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.01);
        costSpinner = new JSpinner(costModel);
        JFormattedTextField costTextField = ((JSpinner.DefaultEditor) costSpinner.getEditor()).getTextField();
        costTextField.setColumns(5); // Adjust the width as needed

        // Spinner for SRP
        SpinnerModel srpModel = new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.01);
        srpSpinner = new JSpinner(srpModel);
        JFormattedTextField srpTextField = ((JSpinner.DefaultEditor) srpSpinner.getEditor()).getTextField();
        srpTextField.setColumns(5); // Adjust the width as needed

        // Add components to input panel
        inputPanel.add(brandLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(brandField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(categoryLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(categoryComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(descriptionLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(new JScrollPane(descriptionTextArea), gbc); // Use JScrollPane for larger text area

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(costLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(costSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(srpLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(srpSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(vendorLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(vendorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(quantityLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(quantitySpinner, gbc);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
            String brand = brandField.getText();
            String category = (String) categoryComboBox.getSelectedItem();
            String description = descriptionTextArea.getText();
            double cost = (double) costSpinner.getValue();
            double srp = (double) srpSpinner.getValue();
            String vendor = vendorField.getText();
            int quantity = (int) quantitySpinner.getValue();

            // Check for empty fields
            if (brand.isEmpty() || category.isEmpty() || description.isEmpty() || vendor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please complete required fields.");
                return;
            }

            // Validate numeric fields
            if (cost <= 0 || srp <= 0) {
                JOptionPane.showMessageDialog(this, "Cost and SRP must be positive numbers.");
                return;
            }

            // Format cost and SRP to two decimal places
            DecimalFormat df = new DecimalFormat("0.00");
            String formattedCost = df.format(cost);
            String formattedSRP = df.format(srp);

            // Build submission details string
            String submissionDetails = "Brand: " + brand + "\n" +
                    "Category: " + category + "\n" +
                    "Description: " + description + "\n" +
                    "Cost: $" + formattedCost + "\n" +
                    "SRP: $" + formattedSRP + "\n" +
                    "Vendor: " + vendor + "\n" +
                    "Quantity: " + quantity + "\n";

            // Submission successful with details
            JOptionPane.showMessageDialog(this, "New product submitted successfully!\n\n" + submissionDetails);

            // Clear fields
            clearFields();
        }
    }


    private void clearFields() {
        brandField.setText("");
        categoryComboBox.setSelectedIndex(0);
        descriptionTextArea.setText("");
        costSpinner.setValue(0.0);
        srpSpinner.setValue(0.0);
        vendorField.setText("");
        quantitySpinner.setValue(1);
    }

    public static void main(String[] args) {
        // Create and show the new product form
        new ProductForm();
    }
}
