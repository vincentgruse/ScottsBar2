package Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Helper.DatabaseHelper.connection;

public class Customer {
    public Long customerID;

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public void insertCustomer(Customer customer) {
        String query = "INSERT INTO Customer () VALUES ()";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(long customerID) {
        String query = "DELETE FROM Customer WHERE CustomerID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, customerID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomerByID(long customerID) {
        String query = "SELECT * FROM Customer WHERE CustomerID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, customerID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(resultSet.getLong("CustomerID"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customer";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(resultSet.getLong("CustomerID"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

}
