package Entities;

import Models.DepartmentManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Helper.DatabaseHelper.connection;

public class Department {
    public Long departmentID;
    public String departmentName;
    public Date managerStartDate;
    public Date managerEndDate;
    public Integer managerSSN;

    // Getter methods
    public Long getDepartmentID() {
        return departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Date getManagerStartDate() {
        return managerStartDate;
    }

    public Date getManagerEndDate() {
        return managerEndDate;
    }

    public Integer getManagerSSN() {
        return managerSSN;
    }

    // Setter methods
    public void setDepartmentID(Long departmentID) {
        this.departmentID = departmentID;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setManagerStartDate(Date managerStartDate) {
        this.managerStartDate = managerStartDate;
    }

    public void setManagerEndDate(Date managerEndDate) {
        this.managerEndDate = managerEndDate;
    }

    public void setManagerSSN(Integer managerSSN) {
        this.managerSSN = managerSSN;
    }

    public void insertDepartment(Department department) {
        String query = "INSERT INTO Department (DeptartmentName, ManagerStartDate, ManagerEndDate, ManagerSSN) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, department.getDepartmentName());
            if (department.getManagerStartDate() != null)
                statement.setDate(2, new java.sql.Date(department.getManagerStartDate().getTime()));
            else
                statement.setDate(2, null);
            if (department.getManagerEndDate() != null)
                statement.setDate(3, department.getManagerEndDate() != null ? new java.sql.Date(department.getManagerEndDate().getTime()) : null);
            else
                statement.setDate(3, null);
            if (department.getManagerSSN() != null)
                statement.setInt(4, department.getManagerSSN());
            else
                statement.setString(4, null);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartment(long departmentID) {
        String query = "DELETE FROM Department WHERE DepartmentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, departmentID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Department getDepartmentById(long departmentID) {
        String query = "SELECT * FROM Department WHERE DepartmentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, departmentID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentID(resultSet.getLong("DepartmentID"));
                department.setDepartmentName(resultSet.getString("DeptartmentName"));
                department.setManagerStartDate(resultSet.getDate("ManagerStartDate"));
                department.setManagerEndDate(resultSet.getDate("ManagerEndDate"));
                department.setManagerSSN(resultSet.getInt("ManagerSSN"));
                return department;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM Department";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentID(resultSet.getLong("DepartmentID"));
                department.setDepartmentName(resultSet.getString("DeptartmentName"));
                department.setManagerStartDate(resultSet.getDate("ManagerStartDate"));
                department.setManagerEndDate(resultSet.getDate("ManagerEndDate"));
                department.setManagerSSN(resultSet.getInt("ManagerSSN"));
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public List<DepartmentManager> getAllDepartmentsJoined() {
        List<DepartmentManager> departments = new ArrayList<>();
        String query = "select d.DepartmentID, d.DeptartmentName, d.ManagerStartDate, d.ManagerEndDate, d.ManagerSSN, e.FirstName as ManagerFName, e.LastName as ManagerLName, COUNT(e2.EmployeeSSN) as EmployeeCount from Department d LEFT JOIN Employee e on d.ManagerSSN = e.EmployeeSSN LEFT JOIN Employee e2 ON d.DepartmentID = e2.DeptId GROUP BY d.DepartmentID, d.DeptartmentName, d.ManagerStartDate, d.ManagerEndDate, d.ManagerSSN, e.FirstName, e.LastName";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DepartmentManager department = new DepartmentManager();
                department.setDepartmentID(resultSet.getLong("DepartmentID"));
                department.setDepartmentName(resultSet.getString("DeptartmentName"));
                department.setManagerStartDate(resultSet.getDate("ManagerStartDate"));
                department.setManagerEndDate(resultSet.getDate("ManagerEndDate"));
                department.setManagerSSN(resultSet.getInt("ManagerSSN"));
                var managerFirstName = resultSet.getString("ManagerFName") != null ? resultSet.getString("ManagerFName"): "";
                var managerLastName = resultSet.getString("ManagerLName") != null ? resultSet.getString("ManagerLName"): "";
                department.ManagerName = managerFirstName + " " + managerLastName;
                department.EmployeeCount = resultSet.getInt("EmployeeCount");
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public void updateDepartment(Department department) {
        String query = "UPDATE Department SET DeptartmentName = ?, ManagerStartDate = ?, ManagerEndDate = ?, ManagerSSN = ? WHERE DepartmentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, department.getDepartmentName());
            statement.setDate(2, new java.sql.Date(department.getManagerStartDate().getTime()));
            statement.setDate(3, department.getManagerEndDate() != null ? new java.sql.Date(department.getManagerEndDate().getTime()) : null);
            statement.setInt(4, department.getManagerSSN());
            statement.setLong(5, department.getDepartmentID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
