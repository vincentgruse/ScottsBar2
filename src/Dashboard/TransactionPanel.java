package Dashboard;

import Entities.LoyaltyMember;
import Entities.TransactionProducts;
import Entities.Transactions;
import Forms.TransactionForm;
import Models.TransactionCustProd;
import Models.TransactionProductDetail;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Vector;

public class TransactionPanel {
    private static Transactions transactions = new Transactions();
    private static TransactionProducts transactionProducts = new TransactionProducts();

    static JTable transactionTable = new JTable();
    static JScrollPane scrollPane = new JScrollPane(transactionTable);
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

        // Adding scroll pane to the panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        transactionPanel.add(scrollPane, gbc);


        transactionTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Check for double-click
                    // Get the selected row
                    int row = transactionTable.getSelectedRow();

                    long tranID = Long.parseLong(transactionTable.getValueAt(row, 0).toString());
                    String message = "";
                    var customerTransactions = transactionProducts.getTransactionProductsByTransactionId(tranID);
                    for (TransactionProductDetail tran: customerTransactions) {
                        message += "Transaction ID: "+tran.transactionID+" | ProductName: "+tran.productName+ " | Unit Price: "+tran.unitPrice+" | Discount: "+tran.discount+"\n";
                    }
                    // Perform desired action here, for example, show a message dialog
                    JOptionPane.showMessageDialog(transactionTable, !(message.isBlank() && message.isEmpty()) ? message : "No Transactions Found");
                }
            }
        });


        // Populate table with sample transaction data

        RefreshTables();
        return transactionPanel;
    }

    private static DefaultTableModel getDefaultTableModel() {
        Vector<String> columns = new Vector<>(Arrays.asList(
                "Transaction ID", "Date", "Time", "Payment", "Total", "Member ID", "Items"
        ));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Vector<Vector<String>> data = new Vector<>();
        var transactionsList = transactions.getAllTransactionsJoined();
        for(TransactionCustProd tran: transactionsList) {
            String dateTime = tran.occurredAt.toString();
            data.add(new Vector(Arrays.asList(tran.transactionID, dateTime.split(" ")[0], dateTime.split(" ")[1], tran.paymentMethod, tran.total, tran.customerName, tran.totalProducts)));
        }

        return new DefaultTableModel(data, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public static void RefreshTables() {
        DefaultTableModel model = getDefaultTableModel();
        transactionTable.setModel(model);

        // Enable sorting for each column
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        transactionTable.setRowSorter(sorter);

        // Display sort arrows in the table header
        transactionTable.getTableHeader().setReorderingAllowed(false);
        transactionTable.setAutoCreateRowSorter(true);
    }
}
