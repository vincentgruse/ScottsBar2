package Forms;

import Entities.Department;
import Entities.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import static Helper.DatabaseHelper.setup;

public class DepartmentForm extends JFrame implements ActionListener {
    // Labels
    JLabel nameLabel = new JLabel("Department*:");
    JLabel supervisorLabel = new JLabel("Supervisor*:");

    // Text fields
    JTextField nameField = new JTextField(20);
    JComboBox<String> departmentEmployees = new JComboBox<>();

    // Button
    JButton submitButton = new JButton("Submit");

    List<Employee> employeeList;

    public DepartmentForm() {
        populateEmployees();
        // Setting up the frame
        setTitle("Department Form");
        setSize(400, 250);
        // Set default close operation to dispose on close
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set icon image
        ImageIcon icon = new ImageIcon("src/assets/logoSmall.png");
        setIconImage(icon.getImage());

        // Calculate the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to input panel
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(supervisorLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(departmentEmployees, gbc);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);

        // Add panels to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Adding action listener to the button
        submitButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String department = nameField.getText();
            Integer supervisor = departmentEmployees.getSelectedIndex();

            // Check if any required field is empty
            if (department.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            Department departmentModel = new Department();
            departmentModel.departmentName = department;
            Integer ssn = null;
            if (departmentEmployees.getSelectedIndex() != -1
                    && departmentEmployees.getSelectedIndex() != 0)
                ssn = employeeList.get(departmentEmployees.getSelectedIndex()).employeeSSN;
            departmentModel.managerSSN = ssn;
            departmentModel.insertDepartment(departmentModel);
            // Displaying the entered details
            String message = "Department: " + department + "\n" +
                    "Supervisor: " + supervisor + "\n";

            JOptionPane.showMessageDialog(this, message);

            // Clearing the fields
            clearFields();
            setVisible(false);
        }
    }

    private void populateEmployees() {
        Employee employee = new Employee();
        employeeList = employee.getAllEmployees();
        employeeList.add(0, null);
        for (Employee emp: employeeList) {
            if (emp != null)
                departmentEmployees.addItem(emp.firstName + " " + emp.lastName);
            else
                departmentEmployees.addItem("");
        }
    }

    // Method to clear text fields
    public void clearFields() {
        nameField.setText("");
    }

    public static void main(String[] args) {
        // Create and show the department form
        new DepartmentForm();
    }
}
