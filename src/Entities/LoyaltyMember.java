package Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.Helper.DatabaseHelper.connection;

public class LoyaltyMember {
    public Long customerID;
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;

    public Long getCustomerID() {
        return customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setter methods
    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void insertLoyaltyMember(LoyaltyMember loyaltyMember) {
        String query = "INSERT INTO LoyaltyMember (CustomerID, FirstName, LastName, Email, PhoneNumber) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, loyaltyMember.getCustomerID());
            statement.setString(2, loyaltyMember.getFirstName());
            statement.setString(3, loyaltyMember.getLastName());
            statement.setString(4, loyaltyMember.getEmail());
            statement.setString(5, loyaltyMember.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLoyaltyMember(long customerID) {
        String query = "DELETE FROM LoyaltyMember WHERE CustomerID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, customerID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LoyaltyMember getLoyaltyMemberByCustomerID(long customerID) {
        String query = "SELECT * FROM LoyaltyMember WHERE CustomerID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, customerID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                LoyaltyMember loyaltyMember = new LoyaltyMember();
                loyaltyMember.setCustomerID(resultSet.getLong("CustomerID"));
                loyaltyMember.setFirstName(resultSet.getString("FirstName"));
                loyaltyMember.setLastName(resultSet.getString("LastName"));
                loyaltyMember.setEmail(resultSet.getString("Email"));
                loyaltyMember.setPhoneNumber(resultSet.getString("PhoneNumber"));
                return loyaltyMember;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LoyaltyMember> getAllLoyaltyMembers() {
        List<LoyaltyMember> loyaltyMembers = new ArrayList<>();
        String query = "SELECT * FROM LoyaltyMember";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LoyaltyMember loyaltyMember = new LoyaltyMember();
                loyaltyMember.setCustomerID(resultSet.getLong("CustomerID"));
                loyaltyMember.setFirstName(resultSet.getString("FirstName"));
                loyaltyMember.setLastName(resultSet.getString("LastName"));
                loyaltyMember.setEmail(resultSet.getString("Email"));
                loyaltyMember.setPhoneNumber(resultSet.getString("PhoneNumber"));
                loyaltyMembers.add(loyaltyMember);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loyaltyMembers;
    }

    public void updateLoyaltyMember(LoyaltyMember loyaltyMember) {
        String query = "UPDATE LoyaltyMember SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ? WHERE CustomerID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, loyaltyMember.getFirstName());
            statement.setString(2, loyaltyMember.getLastName());
            statement.setString(3, loyaltyMember.getEmail());
            statement.setString(4, loyaltyMember.getPhoneNumber());
            statement.setLong(5, loyaltyMember.getCustomerID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
