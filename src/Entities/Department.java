package Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static src.Helper.DatabaseHelper.connection;

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
            statement.setDate(2, new java.sql.Date(department.getManagerStartDate().getTime()));
            statement.setDate(3, department.getManagerEndDate() != null ? new java.sql.Date(department.getManagerEndDate().getTime()) : null);
            statement.setInt(4, department.getManagerSSN());
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
