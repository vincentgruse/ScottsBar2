package Dashboard;

import Forms.VendorForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class VendorPanel {
    // Method to create the Vendor panel
    public static JPanel createVendorPanel() {
        JPanel vendorPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        gbc.weightx = 1.0; // Make the components resize horizontally
        gbc.weighty = 1.0; // Make the components resize vertically
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components

        // Creating the title label
        JLabel titleLabel = new JLabel("Vendors");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Adding title label to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        vendorPanel.add(titleLabel, gbc);

        // Adding table to display vendor information
        JTable vendorTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(vendorTable);

        // Adding scroll pane to the panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        vendorPanel.add(scrollPane, gbc);

        // Adding "+Add Vendor" button
        JButton addButton = new JButton("+Add Vendor");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addButton.addActionListener(e -> {
            // Open the VendorForm to add a new vendor
            VendorForm vendorForm = new VendorForm();
            vendorForm.setVisible(true);
        });

        // Adding button to the panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Reset gridwidth
        vendorPanel.add(addButton, gbc);

        // Populate table with sample vendor data
        DefaultTableModel model = getDefaultTableModel();
        vendorTable.setModel(model);

        // Enable sorting for each column
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        vendorTable.setRowSorter(sorter);

        // Display sort arrows in the table header
        vendorTable.getTableHeader().setReorderingAllowed(false);
        vendorTable.setAutoCreateRowSorter(true);

        return vendorPanel;
    }

    private static DefaultTableModel getDefaultTableModel() {
        Vector<String> columns = new Vector<>(Arrays.asList(
                "Vendor ID", "Name", "Address", "Phone", "Email", "Contract", "Action"
        ));

        Vector<Vector<String>> data = new Vector<>();
        // Sample data entries
        data.add(new Vector<>(Arrays.asList("1", "Vendor A", "123 Street, City A", "123-456-7890", "vendorA@example.com", "Contract A", "Edit")));
        data.add(new Vector<>(Arrays.asList("2", "Vendor B", "456 Street, City B", "456-789-0123", "vendorB@example.com", "Contract B", "Edit")));
        data.add(new Vector<>(Arrays.asList("3", "Vendor C", "789 Street, City C", "789-012-3456", "vendorC@example.com", "Contract C", "Edit")));
        data.add(new Vector<>(Arrays.asList("4", "Vendor D", "012 Street, City D", "012-345-6789", "vendorD@example.com", "Contract D", "Edit")));
        data.add(new Vector<>(Arrays.asList("5", "Vendor E", "345 Street, City E", "345-678-9012", "vendorE@example.com", "Contract E", "Edit")));
        data.add(new Vector<>(Arrays.asList("6", "Vendor F", "678 Street, City F", "678-901-2345", "vendorF@example.com", "Contract F", "Edit")));
        data.add(new Vector<>(Arrays.asList("7", "Vendor G", "901 Street, City G", "901-234-5678", "vendorG@example.com", "Contract G", "Edit")));
        data.add(new Vector<>(Arrays.asList("8", "Vendor H", "234 Street, City H", "234-567-8901", "vendorH@example.com", "Contract H", "Edit")));
        data.add(new Vector<>(Arrays.asList("9", "Vendor I", "567 Street, City I", "567-890-1234", "vendorI@example.com", "Contract I", "Edit")));
        data.add(new Vector<>(Arrays.asList("10", "Vendor J", "890 Street, City J", "890-123-4567", "vendorJ@example.com", "Contract J", "Edit")));

        return new DefaultTableModel(data, columns);
    }
}