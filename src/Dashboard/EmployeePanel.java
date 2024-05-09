package Dashboard;

import Entities.Employee;
import Forms.EmployeeForm;
import Models.EmployeeDepartment;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class EmployeePanel {
    private static Employee employee = new Employee();
    // Method to create the Employee panel
    public static JPanel createEmployeePanel() {
        JPanel employeePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        gbc.weightx = 1.0; // Make the components resize horizontally
        gbc.weighty = 1.0; // Make the components resize vertically
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components

        // Creating the title label
        JLabel titleLabel = new JLabel("Employees");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Adding title label to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        employeePanel.add(titleLabel, gbc);

        // Adding table to display employee information
        JTable employeeTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        // Adding scroll pane to the panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        employeePanel.add(scrollPane, gbc);

        // Adding "+Add Employee" button
        JButton addButton = new JButton("Edit Employee");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));


        // Adding button to the panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Reset gridwidth
        employeePanel.add(addButton, gbc);

        // Populate table with sample employee data
        DefaultTableModel model = getDefaultTableModel();
        employeeTable.setModel(model);

        // Enable sorting for each column
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        employeeTable.setRowSorter(sorter);

        // Display sort arrows in the table header
        employeeTable.getTableHeader().setReorderingAllowed(false);
        employeeTable.setAutoCreateRowSorter(true);

        addButton.addActionListener(e -> {
            DefaultTableModel datamodel = (DefaultTableModel) employeeTable.getModel();
            int rowCount = datamodel.getRowCount();
            int columnCount = datamodel.getColumnCount();

            for (int row = 0; row < rowCount; row++) {
                Object ssnVal = datamodel.getValueAt(row, 0);
                Object fnameVal = datamodel.getValueAt(row, 1);
                Object lnameVal = datamodel.getValueAt(row, 2);
                Object emailVal = datamodel.getValueAt(row, 3);
                Object usernameVal = datamodel.getValueAt(row, 4);
                Object startDateVal = datamodel.getValueAt(row, 5);
                Object endDateVal = datamodel.getValueAt(row, 6);
                Object phoneVal = datamodel.getValueAt(row, 7);
                Object deptVal = datamodel.getValueAt(row, 8);
                Object superVal = datamodel.getValueAt(row, 9);
                Employee empData = new Employee();
                empData.employeeSSN = Integer.valueOf(ssnVal.toString());
                empData.firstName = fnameVal.toString();
                empData.lastName = lnameVal.toString();
                empData.email = emailVal.toString();
                empData.username = usernameVal.toString();
                empData.phoneNumber = phoneVal.toString();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localStartDate = LocalDate.parse(startDateVal.toString(), formatter);
                    empData.startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    if (endDateVal != null && endDateVal.toString() != "") {
                        LocalDate localEndDate = LocalDate.parse(endDateVal.toString(), formatter);
                        empData.endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                var deptID = deptVal.toString().replace("("," ").replace(")","").split(" ");
                empData.deptID = Long.valueOf(deptID[1]);
                var superSSN = superVal.toString().replace("("," ").replace(")","").split(" ");
                if (!superSSN[superSSN.length-1].equals("0"))
                    empData.supervisorSSN = Integer.valueOf(superSSN[superSSN.length-1]);
                else
                    empData.supervisorSSN = null;
                employee.updateEmployee(empData);
            }
        });

        return employeePanel;
    }

    private static DefaultTableModel getDefaultTableModel() {
        Vector<String> columns = new Vector<>(Arrays.asList(
                "Employee ID", "First Name", "Last Name", "Email", "Username", "Start Date", "End Date",
                "Phone#", "Department", "Supervisor"
        ));


        Vector<Vector<String>> data = new Vector<>();
        var employeeList = employee.getAllEmployeesJoined();
        for(EmployeeDepartment emp: employeeList) {
            data.add(new Vector(Arrays.asList(emp.employeeSSN, emp.firstName, emp.lastName, emp.email, emp.username, emp.startDate, emp.endDate, emp.phoneNumber, emp.DepartmentName+"("+emp.deptID+")", emp.SupervisorName+"("+emp.supervisorSSN+")")));
        }
        return new DefaultTableModel(data, columns);
    }

}
