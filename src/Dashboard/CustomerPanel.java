package Dashboard;

import Entities.Customer;
import Entities.LoyaltyMember;
import Entities.Transactions;
import Forms.LoyaltyMemberForm;
import Models.EmployeeDepartment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Vector;

public class CustomerPanel {
    private static LoyaltyMember customer = new LoyaltyMember();
    private static Transactions transactions = new Transactions();

    static JTable customerTable = new JTable();
    static JScrollPane scrollPane = new JScrollPane(customerTable);

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

        // Adding scroll pane to the panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        customerPanel.add(scrollPane, gbc);

        // Adding "+Add Customer" button
        JButton addButton = new JButton("+Edit Loyalty Member");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addButton.addActionListener(e -> {
            // Open the CustomerForm to add a new customer
            LoyaltyMemberForm customerForm = new LoyaltyMemberForm();
            customerForm.setVisible(true);
        });

        customerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Check for double-click
                    // Get the selected row
                    int row = customerTable.getSelectedRow();

                    long customerId = Long.parseLong(customerTable.getValueAt(row, 0).toString());
                    String message = "";
                    var customerTransactions = transactions.getTransactionByCustomerId(customerId);
                    for (Transactions tran: customerTransactions) {
                        message += "Transaction ID: "+tran.transactionID+" | Transaction Time: "+tran.occurredAt+ " \n";
                    }
                    // Perform desired action here, for example, show a message dialog
                    JOptionPane.showMessageDialog(customerTable, !(message.isBlank() && message.isEmpty()) ? message : "No Transactions Found");
                }
            }
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
                "Member ID", "First Name", "Last Name", "Phone", "Email"
        ));

        Vector<Vector<String>> data = new Vector<>();

        var customerList = customer.getAllLoyaltyMembers();
        for(LoyaltyMember cust: customerList) {
            data.add(new Vector(Arrays.asList(cust.customerID, cust.firstName, cust.lastName, cust.phoneNumber, cust.email)));
        }

        return new DefaultTableModel(data, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                switch (column) {
                    case 0:
                        return false;
                    default:
                        return true;
                }
            }
        };
    }
}
