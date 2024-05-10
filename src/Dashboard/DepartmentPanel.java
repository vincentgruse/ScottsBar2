package Dashboard;

import Entities.Department;
import Entities.Employee;
import Forms.DepartmentForm;
import Models.DepartmentManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

public class DepartmentPanel {
    private static Department department = new Department();
    static JTable departmentTable = new JTable();
    static JScrollPane scrollPane = new JScrollPane(departmentTable);
    // Method to create the Department panel
    public static JPanel createDepartmentPanel() {
        JPanel departmentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        gbc.weightx = 1.0; // Make the components resize horizontally
        gbc.weighty = 1.0; // Make the components resize vertically
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components

        // Creating the title label
        JLabel titleLabel = new JLabel("Departments");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Adding title label to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        departmentPanel.add(titleLabel, gbc);

        // Adding table to display department information

        // Adding scroll pane to the panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        departmentPanel.add(scrollPane, gbc);

        JButton deleteButton = new JButton("Delete Department");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 20));
        deleteButton.setBackground(Color.RED);
        deleteButton.addActionListener(e -> {
            DefaultTableModel datamodel = (DefaultTableModel) departmentTable.getModel();
            int deleteIdx = departmentTable.getSelectedRow();
            var deptIdVal = datamodel.getValueAt(deleteIdx, 0);
            department.deleteDepartment(Integer.valueOf(deptIdVal.toString()));
            datamodel.removeRow(deleteIdx);
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Reset gridwidth
        departmentPanel.add(deleteButton, gbc);

        // Adding "+Add Department" button
        JButton addButton = new JButton("+Edit Departments");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addButton.addActionListener(e -> {
            // Open the DepartmentForm to add a new department
            DefaultTableModel datamodel = (DefaultTableModel) departmentTable.getModel();
            int rowCount = datamodel.getRowCount();

            for (int row = 0; row < rowCount; row++) {
                Object deptIdVal = datamodel.getValueAt(row, 0);
                Object dnameVal = datamodel.getValueAt(row, 1);
                Object startDateVal = datamodel.getValueAt(row, 5);
                Object endDateVal = datamodel.getValueAt(row, 6);
                Object superVal = datamodel.getValueAt(row, 3);
                Department deptData = new Department();
                deptData.departmentID = Long.valueOf(deptIdVal.toString());
                deptData.departmentName = dnameVal.toString();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    if (startDateVal != null && startDateVal.toString() != "") {
                        LocalDate localStartDate = LocalDate.parse(startDateVal.toString(), formatter);
                        deptData.managerStartDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    }
                    if (endDateVal != null && endDateVal.toString() != "") {
                        LocalDate localEndDate = LocalDate.parse(endDateVal.toString(), formatter);
                        deptData.managerEndDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                deptData.managerSSN = superVal.toString() != null && superVal.toString() != "" ? Integer.valueOf(superVal.toString()) : null;
                department.updateDepartment(deptData);
            }
            RefreshTables();
            EmployeePanel.RefreshTables();
        });

        // Adding button to the panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Reset gridwidth
        departmentPanel.add(addButton, gbc);

        // Populate table with sample department data
        RefreshTables();

        return departmentPanel;
    }

    private static DefaultTableModel getDefaultTableModel() {
        Vector<String> columns = new Vector<>(Arrays.asList(
                "Department ID", "Name", "Number of Employees", "Manager SSN", "Manager Name",
                "Manager Start Date", "Manager End Date"
        ));

        Vector<Vector<String>> data = new Vector<>();
        var deptList = department.getAllDepartmentsJoined();
        for (DepartmentManager dept: deptList) {
            data.add(new Vector(Arrays.asList(dept.departmentID, dept.departmentName, dept.EmployeeCount, dept.managerSSN, dept.ManagerName, dept.managerStartDate != null ? dept.managerStartDate:"", dept.managerEndDate != null ? dept.managerEndDate:"")));
        }
        return new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                switch (column) {
                    case 0:
                    case 2:
                    case 4:
                        return false;
                    default:
                        return true;
                }
            }
        };
    }

    public static void RefreshTables () {
        DefaultTableModel model = getDefaultTableModel();
        departmentTable.setModel(model);

        // Enable sorting for each column
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        departmentTable.setRowSorter(sorter);

        // Display sort arrows in the table header
        departmentTable.getTableHeader().setReorderingAllowed(false);
        departmentTable.setAutoCreateRowSorter(true);
    }
}
