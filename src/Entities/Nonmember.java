package src.Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.Helper.DatabaseHelper.connection;

public class Nonmember {
    public long customerId;

    public void setCustomerID(Long customerID) {
        this.customerId = customerID;
    }

    public void insertNonmember(Nonmember nonmember) {
        String query = "INSERT INTO Nonmember (CustomerID) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, nonmember.customerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNonmember(long customerID) {
        String query = "DELETE FROM Nonmember WHERE CustomerID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, customerID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Nonmember getNonmemberByID(long customerID) {
        String query = "SELECT * FROM Nonmember WHERE CustomerID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, customerID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Nonmember nonmember = new Nonmember();
                nonmember.setCustomerID(resultSet.getLong("CustomerID"));
                return nonmember;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Nonmember> getAllNonmembers() {
        List<Nonmember> nonmembers = new ArrayList<>();
        String query = "SELECT * FROM Nonmember";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Nonmember nonmember = new Nonmember();
                nonmember.setCustomerID(resultSet.getLong("CustomerID"));
                nonmembers.add(nonmember);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nonmembers;
    }
}
