package Dashboard;

import Entities.Department;
import Entities.Employee;
import Entities.Product;
import Forms.ProductForm;
import Models.EmployeeDepartment;
import Models.ProductCategoryVendor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

public class ProductPanel {
    private static Product product = new Product();
    static JTable productTable = new JTable();
    static JScrollPane scrollPane = new JScrollPane(productTable);

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

        // Adding scroll pane to the panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        productsPanel.add(scrollPane, gbc);

        // Adding "+Add Product" button
        JButton addButton = new JButton("+Edit Product");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addButton.addActionListener(e -> {
            DefaultTableModel datamodel = (DefaultTableModel) productTable.getModel();
            int rowCount = datamodel.getRowCount();
            for (int row = 0; row < rowCount; row++) {
                Object skuVal = datamodel.getValueAt(row, 0);
                Object pnameVal = datamodel.getValueAt(row, 1);
                Object pdescVal = datamodel.getValueAt(row, 2);
                Object costVal = datamodel.getValueAt(row, 3);
                Object stockVal = datamodel.getValueAt(row, 4);
                Object discountVal = datamodel.getValueAt(row, 5);
                Object categoryVal = datamodel.getValueAt(row, 6);
                Object vendorVal = datamodel.getValueAt(row, 8);
                Product data = new Product();
                data.setSKU(skuVal.toString());
                data.productName = pnameVal.toString();
                data.productDescription = pdescVal.toString();
                data.unitPrice = BigDecimal.valueOf(Double.parseDouble(costVal.toString()));
                data.discount = BigDecimal.valueOf(Double.parseDouble(discountVal.toString()));
                data.stock = Integer.valueOf(stockVal.toString());
                data.categoryID = Long.valueOf(categoryVal.toString());
                data.vendorID = Long.valueOf(vendorVal.toString());
                //deptData.managerSSN = superVal.toString() != null && superVal.toString() != "" ? Integer.valueOf(superVal.toString()) : null;
                product.updateProduct(data);
            }
            RefreshTables();
        });

        JButton deleteButton = new JButton("Delete Product");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 20));
        deleteButton.setBackground(Color.RED);

        deleteButton.addActionListener(e -> {
            DefaultTableModel datamodel = (DefaultTableModel) productTable.getModel();
            int deleteIdx = productTable.getSelectedRow();
            var skuVal = datamodel.getValueAt(deleteIdx, 0);
            product.deleteProduct(skuVal.toString());
            datamodel.removeRow(deleteIdx);
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Reset gridwidth
        productsPanel.add(deleteButton, gbc);

        // Adding button to the panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Reset gridwidth
        productsPanel.add(addButton, gbc);

        // Populate table with sample product data
        RefreshTables();
        return productsPanel;
    }

    private static DefaultTableModel getDefaultTableModel() {
        Vector<String> columns = new Vector<>(Arrays.asList(
                "SKU", "Name", "Description", "Cost", "Stock", "Discount",
                "Category ID", "Category", "Vendor ID", "Vendor"
        ));

        Vector<Vector<String>> data = new Vector<>();
        var productList = product.getAllProductsJoined();
        for(ProductCategoryVendor prod: productList) {
            data.add(new Vector(Arrays.asList(prod.getSKU(), prod.getProductName(), prod.getProductDescription(),
                    prod.unitPrice, prod.stock, prod.discount, prod.categoryID, prod.CategoryTitle, prod.vendorID, prod.VendorName)));
        }
        return new DefaultTableModel(data, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                switch (column) {
                    case 0:
                    case 7:
                    case 9:
                        return false;
                    default:
                        return true;
                }
            }
        };
    }

    public static void RefreshTables() {
        DefaultTableModel model = getDefaultTableModel();
        productTable.setModel(model);

        // Enable sorting for each column
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        productTable.setRowSorter(sorter);

        // Display sort arrows in the table header
        productTable.getTableHeader().setReorderingAllowed(false);
        productTable.setAutoCreateRowSorter(true);
    }
}
