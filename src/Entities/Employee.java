package Entities;

import Helper.DatabaseHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Helper.CommonHelper.hashPassword;

public class Employee {
    public Integer employeeSSN;
    public String firstName;
    public String lastName;
    public String username;
    public String passwd;
    public Date startDate;
    public Date endDate;
    public String email;
    public String phoneNumber;
    public String empAddress;
    public Integer supervisorSSN;
    public Long deptID;

    // Getter methods
    public Integer getEmployeeSSN() {
        return employeeSSN;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswd() {
        return passwd;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public Integer getSupervisorSSN() {
        return supervisorSSN;
    }

    public Long getDeptID() {
        return deptID;
    }

    // Setter methods
    public void setEmployeeSSN(Integer employeeSSN) {
        this.employeeSSN = employeeSSN;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public void setSupervisorSSN(Integer supervisorSSN) {
        this.supervisorSSN = supervisorSSN;
    }

    public void setDeptID(Long deptID) {
        this.deptID = deptID;
    }



    public void insertEmployee(Employee employee) {
        String query = "INSERT INTO Employee (EmployeeSSN, FirstName, LastName, Username, Passwd, StartDate, EndDate, Email, PhoneNumber, EmpAddress, SupervisorSSN, DeptID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseHelper.connection.prepareStatement(query)) {
            statement.setInt(1, employee.getEmployeeSSN());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getUsername());
            statement.setString(5, hashPassword(employee.getPasswd()));
            statement.setDate(6, new java.sql.Date(new Date().getTime()));
            statement.setDate(7, employee.getEndDate() != null ? new java.sql.Date(employee.getEndDate().getTime()) : null);
            statement.setString(8, employee.getEmail());
            statement.setString(9, employee.getPhoneNumber());
            statement.setString(10, employee.getEmpAddress());
            if (employee.supervisorSSN != null)
                statement.setInt(11, employee.getSupervisorSSN());
            else
                statement.setString(11, null);
            statement.setLong(12, employee.getDeptID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int employeeSSN) {
        String query = "DELETE FROM Employee WHERE EmployeeSSN = ?";
        try (PreparedStatement statement = DatabaseHelper.connection.prepareStatement(query)) {
            statement.setInt(1, employeeSSN);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeBySSN(int employeeSSN) {
        String query = "SELECT * FROM Employee WHERE EmployeeSSN = ?";
        try (PreparedStatement statement = DatabaseHelper.connection.prepareStatement(query)) {
            statement.setInt(1, employeeSSN);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeSSN(resultSet.getInt("EmployeeSSN"));
                employee.setFirstName(resultSet.getString("FirstName"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setUsername(resultSet.getString("Username"));
                employee.setStartDate(resultSet.getDate("StartDate"));
                employee.setEndDate(resultSet.getDate("EndDate"));
                employee.setEmail(resultSet.getString("Email"));
                employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
                employee.setEmpAddress(resultSet.getString("EmpAddress"));
                employee.setSupervisorSSN(resultSet.getInt("SupervisorSSN"));
                employee.setDeptID(resultSet.getLong("DeptID"));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employee getEmployeeByUsername(String username) {
        String query = "SELECT * FROM Employee WHERE Username = ?";
        try (PreparedStatement statement = DatabaseHelper.connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeSSN(resultSet.getInt("EmployeeSSN"));
                employee.setFirstName(resultSet.getString("FirstName"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setUsername(resultSet.getString("Username"));
                employee.setStartDate(resultSet.getDate("StartDate"));
                employee.setEndDate(resultSet.getDate("EndDate"));
                employee.setEmail(resultSet.getString("Email"));
                employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
                employee.setEmpAddress(resultSet.getString("EmpAddress"));
                employee.setSupervisorSSN(resultSet.getInt("SupervisorSSN"));
                employee.setDeptID(resultSet.getLong("DeptID"));
                employee.setPasswd(resultSet.getString("Passwd"));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee";
        try (PreparedStatement statement = DatabaseHelper.connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeSSN(resultSet.getInt("EmployeeSSN"));
                employee.setFirstName(resultSet.getString("FirstName"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setUsername(resultSet.getString("Username"));
                employee.setStartDate(resultSet.getDate("StartDate"));
                employee.setEndDate(resultSet.getDate("EndDate"));
                employee.setEmail(resultSet.getString("Email"));
                employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
                employee.setEmpAddress(resultSet.getString("EmpAddress"));
                employee.setSupervisorSSN(resultSet.getInt("SupervisorSSN"));
                employee.setDeptID(resultSet.getLong("DeptID"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public List<Employee> getAllEmployeesJoined() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT e.*,d.*,se.FirstName as SupervisorFName, se.LastName as SupervisorLName FROM Employee e JOIN Department d on e.DeptId = d.DepartmentID LEFT JOIN Employee se ON e.EmployeeSSN = se.SupervisorSSN";
        try (PreparedStatement statement = DatabaseHelper.connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeSSN(resultSet.getInt("EmployeeSSN"));
                employee.setFirstName(resultSet.getString("FirstName"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setUsername(resultSet.getString("Username"));
                employee.setStartDate(resultSet.getDate("StartDate"));
                employee.setEndDate(resultSet.getDate("EndDate"));
                employee.setEmail(resultSet.getString("Email"));
                employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
                employee.setEmpAddress(resultSet.getString("EmpAddress"));
                employee.setSupervisorSSN(resultSet.getInt("SupervisorSSN"));
                employee.setDeptID(resultSet.getLong("DeptID"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public void updateEmployee(Employee employee) {
        String query = "UPDATE Employee SET FirstName = ?, LastName = ?, Username = ?, Passwd = ?, StartDate = ?, EndDate = ?, Email = ?, PhoneNumber = ?, EmpAddress = ?, SupervisorSSN = ?, DeptID = ? WHERE EmployeeSSN = ?";
        try (PreparedStatement statement = DatabaseHelper.connection.prepareStatement(query)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getUsername());
            statement.setString(4, hashPassword(employee.getPasswd()));
            statement.setDate(5, new java.sql.Date(employee.getStartDate().getTime()));
            statement.setDate(6, employee.getEndDate() != null ? new java.sql.Date(employee.getEndDate().getTime()) : null);
            statement.setString(7, employee.getEmail());
            statement.setString(8, employee.getPhoneNumber());
            statement.setString(9, employee.getEmpAddress());
            statement.setInt(10, employee.getSupervisorSSN());
            statement.setLong(11, employee.getDeptID());
            statement.setInt(12, employee.getEmployeeSSN());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
