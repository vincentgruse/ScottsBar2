
package Forms;

import Entities.Department;
import Entities.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.List;

import static Helper.DatabaseHelper.setup;

public class DepartmentForm extends JFrame implements ActionListener {

    private static final String ASSETS_PATH = "src/assets/";
    private static final Color BROWN = Color.decode("#8B4513");

    JLabel nameLabel = new JLabel("Name");
    JLabel supervisorLabel = new JLabel("Supervisor");
    // Text fields
    JTextField nameField = new JTextField(20);
    JTextField supervisorField = new JTextField(20);
    // Button
    JButton submitButton = new JButton("Submit");
    JButton backButton = new JButton("<Back");

    List<Employee> employeeList;

    public DepartmentForm() {
        // Setting up the frame
        setTitle("Department Form");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        nameLabel.setForeground(BROWN);
        supervisorLabel.setForeground(BROWN);

        nameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        nameField.setForeground(Color.GRAY);
        nameField.setBackground(Color.WHITE);

        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        supervisorField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        supervisorField.setForeground(Color.GRAY);
        supervisorField.setBackground(Color.WHITE);

        supervisorField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        // Set icon image
        ImageIcon icon = new ImageIcon(ASSETS_PATH + "logoSmall.png");
        setIconImage(icon.getImage());

        // Calculate the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(supervisorLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(supervisorField, gbc);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        submitButton.setBackground(Color.RED);
        submitButton.setForeground(Color.RED);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.RED);

        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);

        // Add panels to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Adding action listener to the button
        submitButton.addActionListener(this);
        backButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String department = nameField.getText();
            String supervisor = supervisorField.getText();

            // Check if any required field is empty
            if (department.isEmpty() || supervisor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

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
        for (Employee emp : employeeList) {
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