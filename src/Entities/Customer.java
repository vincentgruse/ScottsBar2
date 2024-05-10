package Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static Helper.DatabaseHelper.connection;

public class Customer {
    public Long customerID;

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    // The customerid is primary for all loyal members and non-members, so inserting a loyalty member
    // required obtaining a new customerid first, then feeding that back into the loyal member inserting

    public long insertCustomer(Customer customer) {
        String query = "INSERT INTO Customer () VALUES ()";
        long customerId = 0;

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            // Execute the insert operation
            statement.executeUpdate();

            // Retrieve the generated keys
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Get the first column of the ResultSet which contains the auto-incremented ID
                    customerId = generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the auto-generated customer ID
        return customerId;
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
