package Forms;

import Entities.Department;
import Entities.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.List;

import static Helper.DatabaseHelper.setup;

public class EmployeeForm extends JFrame implements ActionListener {
    // Labels
    JLabel firstNameLabel = new JLabel("First Name*:");
    JLabel lastNameLabel = new JLabel("Last Name*:");
    JLabel passwordLabel = new JLabel("Password*:");
    JLabel ssnLabel = new JLabel("SSN*:");
    JLabel phoneLabel = new JLabel("Phone Number*:");
    JLabel addressLabel = new JLabel("Address*:");
    JLabel departmentLabel = new JLabel("Department*:");
    JLabel supervisorLabel = new JLabel("Supervisor*:");

    // Text fields
    JTextField firstNameField = new JTextField(20);
    JTextField lastNameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JPasswordField ssnField = new JPasswordField(20);
    JTextField phoneField = new JTextField(20);
    JTextField addressField = new JTextField(20);
    JComboBox<String> departmentDropdown = new JComboBox<>();
    JComboBox<String> departmentEmployees = new JComboBox<>();

    // Button
    JButton signUpButton = new JButton("Submit");

    List<Department> departmentList;
    List<Employee> employeeList;

    public EmployeeForm() {

        populateDepartments();
        populateEmployees();
        // Setting up the frame
        setTitle("Employee Sign Up Form");
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
        inputPanel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(ssnLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(ssnField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(departmentLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(departmentDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        inputPanel.add(supervisorLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(departmentEmployees, gbc);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(signUpButton);

        // Add panels to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Adding action listener to the button
        signUpButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == signUpButton) {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String password = new String(passwordField.getPassword());
            var ssn = new String(ssnField.getPassword());
            Integer department = departmentDropdown.getSelectedIndex();
            Integer supervisor = departmentEmployees.getSelectedIndex();

            // Check if any required field is empty
            if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || ssn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            // Validate password
            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(this, "Password must be 8-32 characters long and include at least one number, one capital letter, and one symbol.");
                return;
            }

            // Generate username
            String username = generateUsername(firstName, lastName);
            // Generate email
            String email = username + "@scottsbar2.com";

            Employee employee = new Employee();
            employee.employeeSSN = Integer.valueOf(ssn);
            employee.phoneNumber = phoneField.getText();
            employee.firstName = firstName;
            employee.lastName = lastName;
            employee.username = username;
            employee.empAddress = addressField.getText();
            employee.email = email;
            employee.passwd = password;
            var deptNum = departmentList.get(departmentDropdown.getSelectedIndex()).departmentID;
            employee.deptID = deptNum;
            Integer superSSN = null;
            if (departmentEmployees.getSelectedIndex() != -1
                    && departmentEmployees.getSelectedIndex() != 0)
                 superSSN = employeeList.get(departmentEmployees.getSelectedIndex()).employeeSSN;
            employee.supervisorSSN = superSSN;

            employee.insertEmployee(employee);
            // Displaying the entered details, generated username, and email
            String message = "Username: " + username + "\n" +
                    "First Name: " + firstName + "\n" +
                    "Last Name: " + lastName + "\n" +
                    "Email: " + email + "\n" +
                    "Department: " + department + "\n" +
                    "Supervisor: " + supervisor + "\n";

            JOptionPane.showMessageDialog(this, message);

            // Clearing the fields
            clearFields();
            setVisible(false);
        }
    }

    // Method to generate username
    private String generateUsername(String firstName, String lastName) {
        String username;
        if (lastName.length() >= 5) {
            username = lastName.substring(0, 5) + firstName.charAt(0);
        } else {
            username = lastName + firstName.charAt(0);
        }
        return username.toLowerCase();
    }

    private void populateDepartments() {
        Department department = new Department();
        departmentList = department.getAllDepartments();
        for (Department dept: departmentList) {
            departmentDropdown.addItem(dept.departmentName);
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

    // Method to validate password
    private boolean isValidPassword(String password) {
        // Password must be 8-32 characters long and include at least one number, one capital letter, and one of the specified symbols
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()])(?=\\S+$).{8,32}$";
        return password.matches(passwordRegex);
    }

    // Method to clear fields
    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        passwordField.setText("");
        departmentDropdown.setSelectedIndex(0);
        departmentEmployees.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        // Create and show the sign-up form
        //setup();
        new EmployeeForm();

    }
}
