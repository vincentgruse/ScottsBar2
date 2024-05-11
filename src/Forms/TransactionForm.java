package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

import Entities.Customer;
import Entities.TransactionProducts;
import Entities.Transactions;
import Entities.Employee;

public class TransactionForm extends JFrame implements ActionListener {

    private static final Color BROWN = Color.decode("#8B4513");
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

        // populateEmployees();
        // Setting up the frame
        setTitle("Transaction Form");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        employeeLabel.setForeground(BROWN);
        overallDiscountLabel.setForeground(BROWN);
        timeLabel.setForeground(BROWN);
        dateLabel.setForeground(BROWN);
        paymentTypeLabel.setForeground(BROWN);
        memberNumberLabel.setForeground(BROWN);

        // Set icon image (replace "path_to_your_icon_image.png" with the actual path to
        // your icon image)
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
        String[] paymentTypes = { "", "Cash", "Check", "Credit", "Debit" };
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
            // addSkuQuantityPair();
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
        // buttonPanel.setBackground(Color.WHITE);
        submitButton.setBackground(Color.RED);
        submitButton.setForeground(Color.RED);
        addMoreButton.setBackground(Color.RED);
        addMoreButton.setForeground(Color.RED);
        removeButton.setBackground(Color.RED);
        removeButton.setForeground(Color.RED);

        // Add panels to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Adding action listener to the button
        submitButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Check if payment type and SKU fields are filled
            String paymentType = (String) paymentTypeComboBox.getSelectedItem();
            String skuText = skuTextArea.getText().trim();
            assert paymentType != null;
            if (paymentType.isEmpty() || skuText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please complete required fields.");
                return;
            }

            // Split SKU text by commas and validate each SKU
            String[] skus = skuText.split(",");
            StringBuilder invalidSkus = new StringBuilder();
            for (String sku : skus) {
                String trimmedSku = sku.trim();
                if (trimmedSku.length() < 8 || trimmedSku.length() > 12 || !trimmedSku.matches("[a-zA-Z0-9]+")) {
                    if (!invalidSkus.isEmpty()) {
                        invalidSkus.append(", ");
                    }
                    invalidSkus.append(trimmedSku);
                }
            }

            // Display invalid SKUs, if any
            if (!invalidSkus.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid SKUs, please revise: " + invalidSkus);
                return;
            }

            // Retrieve values from fields
            String time = getTimeString();
            String date = dateField.getText();
            String memberNumber = memberNumberField.getText();

            // Split SKU text by commas and add to SKU list
            for (String sku : skus) {
                skuList.add(sku.trim()); // Trim to remove leading/trailing whitespaces
            }

            // Display transaction details
            StringBuilder message = new StringBuilder();
            message.append("Time: ").append(time).append("\n");
            message.append("Date: ").append(date).append("\n");
            message.append("Payment Type: ").append(paymentType).append("\n");
            message.append("Member #: ").append(memberNumber).append("\n");
            message.append("SKU(s):\n");
            for (String sku : skuList) {
                message.append("- ").append(sku).append("\n");
            }
            JOptionPane.showMessageDialog(this, message.toString());

            // Clear SKU list and text area
            skuList.clear();
            skuTextArea.setText("");
        }
    }

    // Method to get time string from the spinner
    private String getTimeString() {
        Object value = timeSpinner.getValue();
        if (value instanceof java.util.Date time) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            return formatter.format(time.toInstant());
        }
        return "";
    }

    public static void main(String[] args) {
        // Create and show the transaction form
        new TransactionForm();
    }
}
