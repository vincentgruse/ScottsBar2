package Dashboard;

import Forms.DepartmentForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class DepartmentPanel {
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
        JTable departmentTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(departmentTable);

        // Adding scroll pane to the panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        departmentPanel.add(scrollPane, gbc);

        // Adding "+Add Department" button
        JButton addButton = new JButton("+Add Department");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addButton.addActionListener(e -> {
            // Open the DepartmentForm to add a new department
            DepartmentForm departmentForm = new DepartmentForm();
            departmentForm.setVisible(true);
        });

        // Adding button to the panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Reset gridwidth
        departmentPanel.add(addButton, gbc);

        // Populate table with sample department data
        DefaultTableModel model = getDefaultTableModel();
        departmentTable.setModel(model);

        // Enable sorting for each column
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        departmentTable.setRowSorter(sorter);

        // Display sort arrows in the table header
        departmentTable.getTableHeader().setReorderingAllowed(false);
        departmentTable.setAutoCreateRowSorter(true);

        return departmentPanel;
    }

    private static DefaultTableModel getDefaultTableModel() {
        Vector<String> columns = new Vector<>(Arrays.asList(
                "Department ID", "Name", "Number of Employees", "Supervisor", "Action"
        ));

        Vector<Vector<String>> data = new Vector<>();
        // Sample data entries
        data.add(new Vector<>(Arrays.asList("1", "Engineering", "5", "John Doe", "Edit")));
        data.add(new Vector<>(Arrays.asList("2", "Marketing", "7", "Jane Smith", "Edit")));
        data.add(new Vector<>(Arrays.asList("3", "HR", "3", "Alice Johnson", "Edit")));
        data.add(new Vector<>(Arrays.asList("4", "Finance", "4", "Bob Williams", "Edit")));
        data.add(new Vector<>(Arrays.asList("5", "Operations", "6", "Charlie Brown", "Edit")));
        data.add(new Vector<>(Arrays.asList("6", "Sales", "8", "Emily Davis", "Edit")));
        data.add(new Vector<>(Arrays.asList("7", "IT", "5", "David Clark", "Edit")));
        data.add(new Vector<>(Arrays.asList("8", "Customer Service", "4", "Samantha Green", "Edit")));
        data.add(new Vector<>(Arrays.asList("9", "Research and Development", "3", "Michael Lee", "Edit")));
        data.add(new Vector<>(Arrays.asList("10", "Quality Assurance", "5", "Jessica Scott", "Edit")));

        return new DefaultTableModel(data, columns);
    }
}
