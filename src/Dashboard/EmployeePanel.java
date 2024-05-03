package Dashboard;

import javax.swing.*;
import java.awt.*;

public class EmployeePanel {
    // Method to create the Home panel
    public static JPanel createEmployeePanel() {
        JPanel employeePanel = new JPanel(new BorderLayout());

        // Creating the title label
        JLabel titleLabel = new JLabel("Employees");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Adding components to the home panel
        employeePanel.add(titleLabel, BorderLayout.NORTH);
        return employeePanel;
    }
}