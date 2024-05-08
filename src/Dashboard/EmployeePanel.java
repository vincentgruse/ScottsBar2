package Dashboard;

import Entities.Employee;
import Forms.EmployeeForm;
import Models.EmployeeDepartment;

import java.awt.*;
import java.util.Arrays;
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
        JButton addButton = new JButton("+Add Employee");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addButton.addActionListener(e -> {
            // Open the EmployeeForm to add a new employee
            EmployeeForm employeeForm = new EmployeeForm();
            employeeForm.setVisible(true);
        });

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

        return employeePanel;
    }

    private static DefaultTableModel getDefaultTableModel() {
        Vector<String> columns = new Vector<>(Arrays.asList(
                "Employee ID", "Name", "Email", "Username", "Start Date", "End Date",
                "Department", "Supervisor", "Action"
        ));

        Vector<Vector<String>> data = new Vector<>();
        var employeeList = employee.getAllEmployeesJoined();
        for(EmployeeDepartment emp: employeeList) {
            data.add(new Vector(Arrays.asList(emp.employeeSSN, emp.firstName + " "+emp.lastName, emp.email, emp.username, emp.startDate, emp.endDate, emp.DepartmentName, emp.SupervisorName, "Edit")));
        }
        return new DefaultTableModel(data, columns);
    }

}
