package Forms;

import Entities.*;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Objects;

public class ProductForm extends JFrame implements ActionListener {

    List<Vendor> vendorList;
    List<Category> categoryList;

    // Labels
    JLabel nameLabel = new JLabel("Name*:");
    JLabel descriptionLabel = new JLabel("Description*:");
    JLabel categoryLabel = new JLabel("Category*:");
    JLabel costLabel = new JLabel("Cost*: $");
    JLabel vendorLabel = new JLabel("Vendor*:");
    JLabel quantityLabel = new JLabel("Quantity*:");
    JLabel skuLabel = new JLabel("SKU*:");
    JLabel discountLabel = new JLabel("Discount*:");

    // Text fields, combo box, and spinner
    JTextField nameField = new JTextField(20);
    JTextField skuField = new JTextField(20);
    JComboBox<String> categoryComboBox = new JComboBox<>();
    JTextArea descriptionTextArea = new JTextArea(5, 20); // Larger text area for description
    JSpinner costSpinner;
    JComboBox<String> vendorComboBox = new JComboBox<>();
    JSpinner quantitySpinner;
    JSpinner discountSpinner;

    // Button
    JButton submitButton = new JButton("Submit");

    public ProductForm() {
        populateCategories();
        populateVendors();
        // Setting up the frame
        setTitle("New Product Form");
        setSize(400, 400);
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

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Spinner for quantity
        SpinnerModel quantityModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
        quantitySpinner = new JSpinner(quantityModel);
        JFormattedTextField quantityTextField = ((JSpinner.DefaultEditor) quantitySpinner.getEditor()).getTextField();
        quantityTextField.setColumns(5); // Adjust the width as needed

        SpinnerModel discountModel = new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.01);
        discountSpinner = new JSpinner(discountModel);
        JFormattedTextField discountTextField = ((JSpinner.DefaultEditor) discountSpinner.getEditor()).getTextField();
        discountTextField.setColumns(5); // Adjust the width as needed

        // Create currency formatter
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        NumberFormatter currencyFormatter = new NumberFormatter(currencyFormat);
        currencyFormatter.setAllowsInvalid(false);

        // Spinner for cost
        SpinnerModel costModel = new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.01);
        costSpinner = new JSpinner(costModel);
        JFormattedTextField costTextField = ((JSpinner.DefaultEditor) costSpinner.getEditor()).getTextField();
        costTextField.setColumns(5); // Adjust the width as needed

        // Add components to input panel
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(descriptionLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(new JScrollPane(descriptionTextArea), gbc); // Use JScrollPane for larger text area

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(skuLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(skuField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(categoryLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(categoryComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(costLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(costSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(discountLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(discountSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(quantityLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(quantitySpinner, gbc);


        gbc.gridx = 0;
        gbc.gridy = 7;
        inputPanel.add(vendorLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(vendorComboBox, gbc);

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
            String productName = nameField.getText();
            String sku = skuField.getText();
            int category = categoryComboBox.getSelectedIndex();
            int vendor = vendorComboBox.getSelectedIndex();
            String description = descriptionTextArea.getText();
            BigDecimal cost = BigDecimal.valueOf((Double)costSpinner.getValue());
            BigDecimal discount = BigDecimal.valueOf((Double)discountSpinner.getValue());
            int quantity = (int) quantitySpinner.getValue();

            // Check for empty fields
            if (productName.isEmpty() || description.isEmpty() || sku.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please complete required fields.");
                return;
            }

            // Validate numeric fields
            if (cost.compareTo(BigDecimal.valueOf(0)) != 1 || quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Cost and Stock must be positive numbers.");
                return;
            }

            // Format cost and SRP to two decimal places
            DecimalFormat df = new DecimalFormat("0.00");
            String formattedCost = df.format(cost);

            Product tempProduct = new Product();
            tempProduct.productName = productName;
            tempProduct.productDescription = description;
            tempProduct.unitPrice = cost;
            tempProduct.stock = quantity;
            tempProduct.discount = discount;
            tempProduct.setSKU(sku);
            tempProduct.categoryID = categoryList.get(categoryComboBox.getSelectedIndex()).categoryID;
            tempProduct.vendorID = vendorList.get(vendorComboBox.getSelectedIndex()).vendorID;
            tempProduct.insertProduct(tempProduct);
            // Build submission details string
            String submissionDetails = "Brand: " + productName + "\n" +
                    "Category: " + category + "\n" +
                    "Description: " + description + "\n" +
                    "Cost: $" + formattedCost + "\n" +
                    "Vendor: " + vendor + "\n" +
                    "Quantity: " + quantity + "\n";

            // Submission successful with details
            JOptionPane.showMessageDialog(this, "New product submitted successfully!\n\n" + submissionDetails);

            // Clear fields
            clearFields();
        }
    }


    private void clearFields() {
        nameField.setText("");
        categoryComboBox.setSelectedIndex(0);
        descriptionTextArea.setText("");
        costSpinner.setValue(0.0);
        vendorComboBox.setSelectedIndex(0);
        quantitySpinner.setValue(1);
    }

    private void populateCategories() {
        Category category = new Category();
        categoryList = category.getAllCategories();
        for (Category cat: categoryList) {
            categoryComboBox.addItem(cat.categoryTitle);
        }
    }

    private void populateVendors() {
        Vendor vendor = new Vendor();
        vendorList = vendor.getAllVendors();
        for (Vendor vend: vendorList) {
            vendorComboBox.addItem(vend.vendorName);
        }
    }

    public static void main(String[] args) {
        // Create and show the new product form
        new ProductForm();
    }
}
