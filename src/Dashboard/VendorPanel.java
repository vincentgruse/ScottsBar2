package Dashboard;

import Entities.Product;
import Entities.Vendor;
import Forms.VendorForm;
import Models.ProductCategoryVendor;
import Models.VendorTypes;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Vector;

public class VendorPanel {
    public static Vendor vendor = new Vendor();

    static JTable vendorTable = new JTable();
    static JScrollPane scrollPane = new JScrollPane(vendorTable);

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


        // Adding scroll pane to the panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        vendorPanel.add(scrollPane, gbc);

        // Adding "+Add Vendor" button
        JButton addButton = new JButton("+Edit Vendor");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addButton.addActionListener(e -> {
            DefaultTableModel datamodel = (DefaultTableModel) vendorTable.getModel();
            int rowCount = datamodel.getRowCount();
            for (int row = 0; row < rowCount; row++) {
                Object IdVal = datamodel.getValueAt(row, 0);
                Object vnameVal = datamodel.getValueAt(row, 1);
                Object vaddressVal = datamodel.getValueAt(row, 2);
                Object phoneVal = datamodel.getValueAt(row, 3);
                Object emailVal = datamodel.getValueAt(row, 4);
                Object typeVal = datamodel.getValueAt(row, 5);
                Object typeIdVal = datamodel.getValueAt(row, 6);
                Vendor data = new Vendor();
                data.vendorID = Long.parseLong(IdVal.toString());
                data.vendorName = vnameVal.toString();
                data.vendorAddress = vaddressVal.toString();
                data.vendorPhoneNumber = phoneVal.toString();
                data.vendorEmail = emailVal.toString();
                data.type = typeVal.toString();
                data.typeID = Integer.valueOf(typeIdVal.toString());
                vendor.updateVendor(data);
            }
            RefreshTables();
            ProductPanel.RefreshTables();
        });

        JButton deleteButton = new JButton("Delete Vendor");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 20));
        deleteButton.setBackground(Color.RED);

        deleteButton.addActionListener(e -> {
            DefaultTableModel datamodel = (DefaultTableModel) vendorTable.getModel();
            int deleteIdx = vendorTable.getSelectedRow();
            var vendorIDVal = datamodel.getValueAt(deleteIdx, 0);
            vendor.deleteVendor(Long.parseLong(vendorIDVal.toString()));
            datamodel.removeRow(deleteIdx);
        });

        JButton uploadButton = new JButton("Upload Contract");
        uploadButton.setFont(new Font("Arial", Font.PLAIN, 20));
        uploadButton.setBackground(Color.BLUE);

        uploadButton.addActionListener(e -> {
            DefaultTableModel datamodel = (DefaultTableModel) vendorTable.getModel();
            int selectedRow = vendorTable.getSelectedRow();
            int selectedColumn = vendorTable.getSelectedColumn();
            if (selectedColumn != 6) {
                JOptionPane.showMessageDialog(vendorTable, "The upload not valid for this column");
                return;
            }
            if (vendorTable.getValueAt(selectedRow, selectedColumn-1).toString().equals("Private")) {
                JOptionPane.showMessageDialog(vendorTable, "The upload not valid for this type");
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("PDF files", "pdf"));
            int returnValue = fileChooser.showOpenDialog(vendorTable);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                long currentTimeMillis = System.currentTimeMillis();
                int currentTimeSeconds = (int) (currentTimeMillis / 1000);
                vendorTable.setValueAt(currentTimeSeconds, selectedRow, selectedColumn);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Reset gridwidth
        vendorPanel.add(uploadButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Reset gridwidth
        vendorPanel.add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1; // Reset gridwidth
        vendorPanel.add(addButton, gbc);

        RefreshTables();
        return vendorPanel;
    }

    private static DefaultTableModel getDefaultTableModel() {
        Vector<String> columns = new Vector<>(Arrays.asList(
                "Vendor ID", "Name", "Address", "Phone", "Email", "Type", "Type Identity"
        ));

        Vector<Vector<String>> data = new Vector<>();
        var vendorList = vendor.getAllVendorsJoined();
        for(VendorTypes vd: vendorList) {
            data.add(new Vector(Arrays.asList(vd.vendorID, vd.vendorName, vd.vendorAddress, vd.vendorPhoneNumber,
                    vd.vendorEmail, vd.Type , vd.TypeIdentity)));
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

    public static void RefreshTables () {
        // Populate table with sample vendor data
        DefaultTableModel model = getDefaultTableModel();
        vendorTable.setModel(model);
        // Enable sorting for each column
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        vendorTable.setRowSorter(sorter);

        // Display sort arrows in the table header
        vendorTable.getTableHeader().setReorderingAllowed(false);
        vendorTable.setAutoCreateRowSorter(true);

    }
}
