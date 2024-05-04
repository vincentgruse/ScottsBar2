package Forms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DepartmentForm extends JFrame implements ActionListener {
	
	private static final String ASSETS_PATH = "src/assets/";
    // Labels
    JLabel nameLabel = new JLabel("Department*:");
   
    JLabel supervisorLabel = new JLabel("Supervisor*:");
  
    	

    
    
    // Text fields
    JTextField nameField = new JTextField(20);
    JTextField supervisorField = new JTextField(20);

    // Button
    JButton submitButton = new JButton("Submit");

    public DepartmentForm() {
        // Setting up the frame
        setTitle("Department Form");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nameLabel.setForeground(Color.RED); 
        supervisorLabel.setForeground(Color.RED); 

        // Set icon image
        ImageIcon icon = new ImageIcon(ASSETS_PATH + "logoSmall.png");
        setIconImage(icon.getImage());
        Color lightOrange = new Color(255, 128, 0);

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(lightOrange);
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
        inputPanel.add(supervisorField, gbc);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(lightOrange);       
       
        
        
        
        
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
        }
    }

    // Method to clear text fields
    public void clearFields() {
        nameField.setText("");
        supervisorField.setText("");
    }

    public static void main(String[] args) {
        // Create and show the department form
        new DepartmentForm();
    }
}