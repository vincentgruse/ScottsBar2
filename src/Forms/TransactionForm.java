package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.sql.*;
import java.util.List;

import Entities.Customer;
import Entities.TransactionProducts;
import Entities.Transactions;
import Entities.Employee;
import Entities.Product;

import java.math.RoundingMode;


public class TransactionForm extends JFrame implements ActionListener {
    // Labels
    JLabel timeLabel = new JLabel("Time*:");
    JLabel dateLabel = new JLabel("Date*:");
    JLabel paymentTypeLabel = new JLabel("Payment Type*:");
    JLabel memberNumberLabel = new JLabel("Member # (optional):");
    JLabel employeeLabel = new JLabel("Employee:");
    JLabel overallDiscountLabel = new JLabel("Overall Discount (%):");


    // Spinner for time
    JSpinner timeSpinner;

    // Text fields and combo box
    JTextField dateField = new JTextField(10);
    JComboBox<String> paymentTypeComboBox;
    JTextField memberNumberField = new JTextField(10);
    JTextField overallDiscountField = new JTextField(3);
    JComboBox<String> departmentEmployees = new JComboBox<>();






    // Button
    JButton submitButton = new JButton("Submit");
    JButton addMoreButton = new JButton("Add Row");
    JButton removeButton = new JButton("Remove Row");

    // SKU array
    ArrayList<JTextField> skuFields = new ArrayList<>();
    ArrayList<JSpinner> quantitySpinners = new ArrayList<>();

    // Must be here to properly reference for the remove button to work
    ArrayList<JLabel> skuLabels = new ArrayList<>();
    ArrayList<JLabel> quantityLabels = new ArrayList<>();


    // Moved here so SKU button can use also
    JPanel inputPanel = new JPanel(new GridBagLayout());

    // Used to get employee dropdown for whose doing the transaction
    // Needed to get the snn to store inside transactions table
    List<Employee> employeeList;


    public TransactionForm() {


        populateEmployees();


        // Setting up the frame
        setTitle("Transaction Form");
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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Spinner model for time
        SpinnerDateModel timeModel = new SpinnerDateModel();
        timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "hh:mm:ss a");
        timeSpinner.setEditor(timeEditor);

        // Autofill date field with current date
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String date = now.format(dateFormatter);
        dateField.setText(date);

        // Add payment types to the combo box
        String[] paymentTypes = {"", "Cash", "Check", "Credit", "Debit"};
        paymentTypeComboBox = new JComboBox<>(paymentTypes);

        // Add components to input panel
        inputPanel.add(timeLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(timeSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(dateLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(employeeLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(departmentEmployees, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(paymentTypeLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(paymentTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(memberNumberLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(memberNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(overallDiscountLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(overallDiscountField, gbc);
        overallDiscountField.setText("0");

        int defaultSKUBoxes = 1;
        for (int i = 0; i < defaultSKUBoxes; i++) {
            addSkuQuantityPair();
        }

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        inputPanel.add(addMoreButton, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        inputPanel.add(removeButton, gbc);


        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);

        // Add panels to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Adding action listener to the button
        // The repaint is needed to have new inputs show
        submitButton.addActionListener(this);
        addMoreButton.addActionListener(e -> addSkuQuantityPair());
        removeButton.addActionListener(e -> removeSkuQuantityPair());


        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {



            // Not directly used for database
            String time = getTimeString();
            String date = dateField.getText();
            String overallDiscount = overallDiscountField.getText();
            int overallDiscountInt = Integer.parseInt(overallDiscount);
            int employee = departmentEmployees.getSelectedIndex();
            String employeeName = employeeList.get(employee).firstName + " " + employeeList.get(employee).lastName;


            // Check if payment type and SKU fields are filled
            String paymentType = (String) paymentTypeComboBox.getSelectedItem();

            assert paymentType != null;
            if (paymentType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a payment type.");
                return;
            }

            // Validate discount percentage

            if (overallDiscountInt > 100 || overallDiscountInt < 0){
                JOptionPane.showMessageDialog(this, "Discount must be between 0 and 100.");
                return;
            }

            // Validate SKUs with database call
            // The method isn't static so can use product object to fill it with data in roundabout way
            Product productHelper = new Product();
            StringBuilder invalidSkus = new StringBuilder("Invalid SKU(s):");
            boolean allSkusValid = true;

            // Check each SKU to see if it exists in the database
            for (JTextField skuField : skuFields) {
                String trimmedSku = skuField.getText().trim();
                if (trimmedSku.isEmpty()) {
                    // Directly append to invalid SKUs if empty
                    allSkusValid = false;
                    invalidSkus.append("\n").append("SKU is cannot be empty");
                } else {
                    Product product = productHelper.getProductBySKU(trimmedSku);
                    if (product == null) {
                        allSkusValid = false;
                        invalidSkus.append("\n").append(trimmedSku);
                    }
                }
            }

            // If any SKU is invalid, show an error message and do not proceed
            if (!allSkusValid) {
                JOptionPane.showMessageDialog(this, invalidSkus.toString());
                return;
            }


            // For database Transaction
            Timestamp dateTime = getTimeStamp();
            String memberNumber = memberNumberField.getText();

            long customerId = 0;

            if (memberNumber.isEmpty()){
                Customer customer = new Customer();
                customerId = customer.insertCustomer(customer);
            } else {
                customerId = Long.parseLong(memberNumber);
            }
            int employeeSSN = 0;
            if (departmentEmployees.getSelectedIndex() != -1 && departmentEmployees.getSelectedIndex() != 0) {
                employeeSSN = employeeList.get(departmentEmployees.getSelectedIndex()).employeeSSN;
            }

            BigDecimal total = calculateTotalPrice();

            // For database TransactionProducts
            // If the customer is not a member, then we must insert another
            // blank value for a new customerID, then use that for the transaction table


            // Display transaction details
            StringBuilder message = new StringBuilder();
            message.append("Time: ").append(time).append("\n");
            message.append("Date: ").append(date).append("\n");
            message.append("Employee: ").append(employeeName).append("\n");
            message.append("Payment Type: ").append(paymentType).append("\n");
            message.append("Member #: ").append(memberNumber).append("\n");
            message.append("Overall Discount: ").append(overallDiscount).append("\n");
            message.append("Total: $ ").append(total).append("\n");
            message.append("SKU(s):\n");

            for (JTextField skuField : skuFields) {
                message.append("- ").append(skuField.getText().trim()).append("\n");
            }

            JOptionPane.showMessageDialog(this, message.toString());



            // Instantiate and fill transaction data
            Transactions transaction = new Transactions();
            transaction.occurredAt = dateTime;
            transaction.paymentMethod = paymentType;
            transaction.employeeSSN = employeeSSN;
            transaction.customerID = customerId;
            transaction.total = total;
            long transactionID = transaction.insertTransaction(transaction);


            // Instantiate and fill TransactionProducts
            ArrayList<TransactionProducts> transactionProductsList = new ArrayList<>();

            for (int i = 0; i < skuFields.size(); i++) {
                JTextField skuField = skuFields.get(i);
                JSpinner quantitySpinner = quantitySpinners.get(i);

                String sku = skuField.getText().trim();
                int quantity = (Integer) quantitySpinner.getValue();

                if (!sku.isEmpty() && quantity > 0) {
                    TransactionProducts transactionProduct = new TransactionProducts();
                    transactionProduct.SKU = sku;
                    transactionProduct.quantity = quantity;
                    transactionProduct.overallDiscount = BigDecimal.valueOf(overallDiscountInt);
                    transactionProduct.transactionID = transactionID;

                    transactionProductsList.add(transactionProduct);
                }
            }

            // Clear SKU list
            clearFields();
            setVisible(false);
        }
    }


    // Method to get current time string in MySQL DATETIME format
    private Timestamp getTimeStamp() {
        return Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    private String getTimeString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        return now.format(formatter);
    }


    private void addSkuQuantityPair() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField skuField = new JTextField(10);
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JLabel skuLabel = new JLabel("SKU:");
        JLabel quantityLabel = new JLabel("Quantity:");

        int pairIndex = skuFields.size();
        gbc.gridx = 0;
        gbc.gridy = 7 + pairIndex;
        inputPanel.add(skuLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(skuField, gbc);

        gbc.gridx = 2;
        inputPanel.add(quantityLabel, gbc);

        gbc.gridx = 3;
        inputPanel.add(quantitySpinner, gbc);

        // Add fields and spinners to lists
        skuFields.add(skuField);
        quantitySpinners.add(quantitySpinner);
        skuLabels.add(skuLabel);
        quantityLabels.add(quantityLabel);

        // Repaint to update the panel
        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void removeSkuQuantityPair() {
        // Ensure at least one pair remains
        if (skuFields.size() > 1) {
            int lastIndex = skuFields.size() - 1; // Get the index of the last element

            // Remove the components from the panel
            inputPanel.remove(skuFields.get(lastIndex));
            inputPanel.remove(quantitySpinners.get(lastIndex));
            inputPanel.remove(skuLabels.get(lastIndex));
            inputPanel.remove(quantityLabels.get(lastIndex));

            // Remove subsequent elements
            skuFields.remove(lastIndex);
            quantitySpinners.remove(lastIndex);
            skuLabels.remove(lastIndex);
            quantityLabels.remove(lastIndex);

            // Repaint to update the panel
            inputPanel.revalidate();
            inputPanel.repaint();
        }
    }

    private void populateEmployees() {
        Employee employee = new Employee();
        employeeList = employee.getAllEmployees();
        employeeList.add(null);
        for (Employee emp: employeeList) {
            if (emp != null)
                departmentEmployees.addItem(emp.firstName + " " + emp.lastName);
            else
                departmentEmployees.addItem("");
        }
    }

    private void clearFields() {
        memberNumberField.setText("");
        departmentEmployees.setSelectedIndex(0);
        skuFields.clear();
    }

    public BigDecimal calculateTotalPrice() {
        // BigDecmial has its own special methods to work with numbers
        BigDecimal total = BigDecimal.ZERO;
        Product productHelper = new Product();

        for (int i = 0; i < skuFields.size(); i++) {
            JTextField skuField = skuFields.get(i);
            JSpinner quantitySpinner = quantitySpinners.get(i);

            String sku = skuField.getText().trim();
            int quantity = (Integer) quantitySpinner.getValue();

            // The method isn't static so can use product object to "fill itself" with data
            Product product = productHelper.getProductBySKU(sku);
            if (product != null && product.getUnitPrice() != null) {
                BigDecimal price = product.getUnitPrice();
                total = total.add(price.multiply(BigDecimal.valueOf(quantity)));
            }
        }

        // If it's discounted
        String discountStr = overallDiscountField.getText();
        if (!discountStr.isEmpty()) {
            try {
                int discountPercent = Integer.parseInt(discountStr);
                BigDecimal discountMultiplier = BigDecimal.valueOf(discountPercent)
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
                BigDecimal discount = total.multiply(discountMultiplier);
                total = total.subtract(discount);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid discount value.");
            }
        }

        return total;
    }


    public static void main(String[] args) {
        // Create and show the transaction form
        new TransactionForm();
    }
}


