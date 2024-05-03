package Dashboard;

import Forms.ProductForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class ProductPanel {
    // Method to create the Products panel
    public static JPanel createProductPanel() {
        JPanel productsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        gbc.weightx = 1.0; // Make the components resize horizontally
        gbc.weighty = 1.0; // Make the components resize vertically
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components

        // Creating the title label
        JLabel titleLabel = new JLabel("Products");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Adding title label to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        productsPanel.add(titleLabel, gbc);

        // Adding table to display product information
        JTable productTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(productTable);

        // Adding scroll pane to the panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        productsPanel.add(scrollPane, gbc);

        // Adding "+Add Product" button
        JButton addButton = new JButton("+Add Product");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addButton.addActionListener(e -> {
            // Open the ProductForm to add a new product
            ProductForm productForm = new ProductForm();
            productForm.setVisible(true);
        });

        // Adding button to the panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Reset gridwidth
        productsPanel.add(addButton, gbc);

        // Populate table with sample product data
        DefaultTableModel model = getDefaultTableModel();
        productTable.setModel(model);

        // Enable sorting for each column
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        productTable.setRowSorter(sorter);

        // Display sort arrows in the table header
        productTable.getTableHeader().setReorderingAllowed(false);
        productTable.setAutoCreateRowSorter(true);

        return productsPanel;
    }

    private static DefaultTableModel getDefaultTableModel() {
        Vector<String> columns = new Vector<>(Arrays.asList(
                "SKU", "Brand", "Description", "Category", "Cost",
                "SRP", "Vendor", "Stock", "Discount", "Action"
        ));

        Vector<Vector<String>> data = new Vector<>();
        // Sample data entries
        data.add(new Vector<>(Arrays.asList("SKU001", "Brand A", "Product A", "Category X", "$10.00", "$20.00", "Vendor X", "100", "5%", "Edit")));
        data.add(new Vector<>(Arrays.asList("SKU002", "Brand B", "Product B", "Category Y", "$15.00", "$25.00", "Vendor Y", "150", "10%", "Edit")));
        data.add(new Vector<>(Arrays.asList("SKU003", "Brand C", "Product C", "Category Z", "$20.00", "$30.00", "Vendor Z", "200", "15%", "Edit")));
        data.add(new Vector<>(Arrays.asList("SKU004", "Brand D", "Product D", "Category X", "$25.00", "$35.00", "Vendor X", "120", "8%", "Edit")));
        data.add(new Vector<>(Arrays.asList("SKU005", "Brand E", "Product E", "Category Y", "$30.00", "$40.00", "Vendor Y", "180", "12%", "Edit")));
        data.add(new Vector<>(Arrays.asList("SKU006", "Brand F", "Product F", "Category Z", "$35.00", "$45.00", "Vendor Z", "220", "20%", "Edit")));
        data.add(new Vector<>(Arrays.asList("SKU007", "Brand G", "Product G", "Category X", "$40.00", "$50.00", "Vendor X", "90", "6%", "Edit")));
        data.add(new Vector<>(Arrays.asList("SKU008", "Brand H", "Product H", "Category Y", "$45.00", "$55.00", "Vendor Y", "140", "11%", "Edit")));
        data.add(new Vector<>(Arrays.asList("SKU009", "Brand I", "Product I", "Category Z", "$50.00", "$60.00", "Vendor Z", "160", "18%", "Edit")));
        data.add(new Vector<>(Arrays.asList("SKU010", "Brand J", "Product J", "Category X", "$55.00", "$65.00", "Vendor X", "130", "9%", "Edit")));

        return new DefaultTableModel(data, columns);
    }
}
