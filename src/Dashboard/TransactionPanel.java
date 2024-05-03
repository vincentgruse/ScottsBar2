package Dashboard;

import Forms.TransactionForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class TransactionPanel {
    // Method to create the Transaction panel
    public static JPanel createTransactionPanel() {
        JPanel transactionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        gbc.weightx = 1.0; // Make the components resize horizontally
        gbc.weighty = 1.0; // Make the components resize vertically
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components

        // Creating the title label
        JLabel titleLabel = new JLabel("Transactions");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Adding title label to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        transactionPanel.add(titleLabel, gbc);

        // Adding table to display transaction information
        JTable transactionTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(transactionTable);

        // Adding scroll pane to the panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        transactionPanel.add(scrollPane, gbc);

        // Adding "+Add Transaction" button
        JButton addButton = new JButton("+Add Transaction");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addButton.addActionListener(e -> {
            // Open the TransactionForm to add a new transaction
            TransactionForm transactionForm = new TransactionForm();
            transactionForm.setVisible(true);
        });

        // Adding button to the panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Reset gridwidth
        transactionPanel.add(addButton, gbc);

        // Populate table with sample transaction data
        DefaultTableModel model = getDefaultTableModel();
        transactionTable.setModel(model);

        // Enable sorting for each column
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        transactionTable.setRowSorter(sorter);

        // Display sort arrows in the table header
        transactionTable.getTableHeader().setReorderingAllowed(false);
        transactionTable.setAutoCreateRowSorter(true);

        return transactionPanel;
    }

    private static DefaultTableModel getDefaultTableModel() {
        Vector<String> columns = new Vector<>(Arrays.asList(
                "Transaction ID", "Time", "Date", "Payment", "Total", "Member ID", "Items", "Action"
        ));

        Vector<Vector<String>> data = new Vector<>();
        // Sample data entries
        data.add(new Vector<>(Arrays.asList("1", "10:00 AM", "2024-05-01", "Credit Card", "$100.00", "1", "5", "Edit")));
        data.add(new Vector<>(Arrays.asList("2", "11:30 AM", "2024-05-02", "Cash", "$75.00", "2", "3", "Edit")));
        data.add(new Vector<>(Arrays.asList("3", "12:45 PM", "2024-05-03", "Debit Card", "$150.00", "3", "7", "Edit")));
        data.add(new Vector<>(Arrays.asList("4", "2:00 PM", "2024-05-04", "Credit Card", "$200.00", "4", "9", "Edit")));
        data.add(new Vector<>(Arrays.asList("5", "3:30 PM", "2024-05-05", "Cash", "$120.00", "5", "4", "Edit")));
        data.add(new Vector<>(Arrays.asList("6", "4:45 PM", "2024-05-06", "Credit Card", "$180.00", "6", "6", "Edit")));
        data.add(new Vector<>(Arrays.asList("7", "6:00 PM", "2024-05-07", "Cash", "$90.00", "7", "8", "Edit")));
        data.add(new Vector<>(Arrays.asList("8", "7:15 PM", "2024-05-08", "Debit Card", "$220.00", "8", "10", "Edit")));
        data.add(new Vector<>(Arrays.asList("9", "8:30 PM", "2024-05-09", "Credit Card", "$130.00", "9", "12", "Edit")));
        data.add(new Vector<>(Arrays.asList("10", "9:45 PM", "2024-05-10", "Cash", "$170.00", "10", "14", "Edit")));

        return new DefaultTableModel(data, columns);
    }
}
