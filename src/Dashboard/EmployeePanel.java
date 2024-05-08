package Dashboard;

import Entities.Employee;
import Forms.EmployeeForm;

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
        var employeeList = employee.getAllEmployees();
        for(Employee emp: employeeList) {
            data.add(new Vector(Arrays.asList(emp.employeeSSN, emp.firstName + " "+emp.lastName, emp.email, emp.username, emp.startDate, emp.endDate, "", "", "Edit")));
        }
        // Sample data entries
//        data.add(new Vector<>(Arrays.asList("1", "John Doe", "john@example.com", "johndoe",  "$50,000", "2022-01-01", "2023-01-01", "Engineering", "Jane Smith", "Edit")));
//        data.add(new Vector<>(Arrays.asList("2", "Jane Smith", "jane@example.com", "janesmith", "password2", "$60,000", "2022-02-01", "2023-02-01", "Marketing", "John Doe", "Edit")));
//        data.add(new Vector<>(Arrays.asList("3", "Alice Johnson", "alice@example.com", "alicejohnson", "password3", "$55,000", "2022-03-01", "2023-03-01", "HR", "Bob Williams", "Edit")));
//        data.add(new Vector<>(Arrays.asList("4", "Bob Williams", "bob@example.com", "bobwilliams", "password4", "$65,000", "2022-04-01", "2023-04-01", "Finance", "Alice Johnson", "Edit")));
//        data.add(new Vector<>(Arrays.asList("5", "Charlie Brown", "charlie@example.com", "charliebrown", "password5", "$45,000", "2022-05-01", "2023-05-01", "Operations", "Emily Davis", "Edit")));
//        data.add(new Vector<>(Arrays.asList("6", "Emily Davis", "emily@example.com", "emilydavis", "password6", "$75,000", "2022-06-01", "2023-06-01", "Sales", "Charlie Brown", "Edit")));
//        data.add(new Vector<>(Arrays.asList("7", "David Clark", "david@example.com", "davidclark", "password7", "$70,000", "2022-07-01", "2023-07-01", "Engineering", "Samantha Green", "Edit")));
//        data.add(new Vector<>(Arrays.asList("8", "Samantha Green", "samantha@example.com", "samanthagreen", "password8", "$80,000", "2022-08-01", "2023-08-01", "Marketing", "David Clark", "Edit")));
//        data.add(new Vector<>(Arrays.asList("9", "Michael Lee", "michael@example.com", "michaellee", "password9", "$65,000", "2022-09-01", "2023-09-01", "HR", "Jessica Scott", "Edit")));
//        data.add(new Vector<>(Arrays.asList("10", "Jessica Scott", "jessica@example.com", "jessicascott", "password10", "$55,000", "2022-10-01", "2023-10-01", "Finance", "Michael Lee", "Edit")));

        return new DefaultTableModel(data, columns);
    }

}
