package Dashboard;

import Forms.LoyaltyMemberForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class CustomerPanel {
    // Method to create the Customer panel
    public static JPanel createCustomerPanel() {
        JPanel customerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        gbc.weightx = 1.0; // Make the components resize horizontally
        gbc.weighty = 1.0; // Make the components resize vertically
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components

        // Creating the title label
        JLabel titleLabel = new JLabel("Customers");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Adding title label to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        customerPanel.add(titleLabel, gbc);

        // Adding table to display customer information
        JTable customerTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(customerTable);

        // Adding scroll pane to the panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        customerPanel.add(scrollPane, gbc);

        // Adding "+Add Customer" button
        JButton addButton = new JButton("+Add Loyalty Member");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addButton.addActionListener(e -> {
            // Open the CustomerForm to add a new customer
            LoyaltyMemberForm customerForm = new LoyaltyMemberForm();
            customerForm.setVisible(true);
        });

        // Adding button to the panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Reset gridwidth
        customerPanel.add(addButton, gbc);

        // Populate table with sample customer data
        DefaultTableModel model = getDefaultTableModel();
        customerTable.setModel(model);

        // Enable sorting for each column
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        customerTable.setRowSorter(sorter);

        // Display sort arrows in the table header
        customerTable.getTableHeader().setReorderingAllowed(false);
        customerTable.setAutoCreateRowSorter(true);

        return customerPanel;
    }

    private static DefaultTableModel getDefaultTableModel() {
        Vector<String> columns = new Vector<>(Arrays.asList(
                "Customer ID", "First Name", "Last Name", "Phone", "Email",
                "Member ID", "Order History", "Action"
        ));

        Vector<Vector<String>> data = new Vector<>();
        // Sample data entries
        data.add(new Vector<>(Arrays.asList("1", "John", "Doe", "123-456-7890", "john@example.com", "M123", "5", "Edit")));
        data.add(new Vector<>(Arrays.asList("2", "Jane", "Smith", "456-789-0123", "jane@example.com", "M456", "7", "Edit")));
        data.add(new Vector<>(Arrays.asList("3", "Alice", "Johnson", "789-012-3456", "alice@example.com", "M789", "3", "Edit")));
        data.add(new Vector<>(Arrays.asList("4", "Bob", "Williams", "012-345-6789", "bob@example.com", "M012", "4", "Edit")));
        data.add(new Vector<>(Arrays.asList("5", "Charlie", "Brown", "345-678-9012", "charlie@example.com", "M345", "6", "Edit")));
        data.add(new Vector<>(Arrays.asList("6", "Emily", "Davis", "678-901-2345", "emily@example.com", "M678", "8", "Edit")));
        data.add(new Vector<>(Arrays.asList("7", "David", "Clark", "901-234-5678", "david@example.com", "M901", "5", "Edit")));
        data.add(new Vector<>(Arrays.asList("8", "Samantha", "Green", "234-567-8901", "samantha@example.com", "M234", "4", "Edit")));
        data.add(new Vector<>(Arrays.asList("9", "Michael", "Lee", "567-890-1234", "michael@example.com", "M567", "3", "Edit")));
        data.add(new Vector<>(Arrays.asList("10", "Jessica", "Scott", "890-123-4567", "jessica@example.com", "M890", "5", "Edit")));

        return new DefaultTableModel(data, columns);
    }
}