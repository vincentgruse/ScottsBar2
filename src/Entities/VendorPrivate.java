package src.Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.Helper.DatabaseHelper.connection;

public class VendorPrivate {
    public Long vendorID;
    public Integer personSSN;

    public Long getVendorID() {
        return vendorID;
    }

    public Integer getPersonSSN() {
        return personSSN;
    }

    // Setter methods
    public void setVendorID(Long vendorID) {
        this.vendorID = vendorID;
    }

    public void setPersonSSN(Integer personSSN) {
        this.personSSN = personSSN;
    }

    public void insertVendorPrivate(VendorPrivate vendorPrivate) {
        String query = "INSERT INTO VendorPrivate (VendorID, PersonSSN) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, vendorPrivate.getVendorID());
            statement.setInt(2, vendorPrivate.getPersonSSN());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVendorPrivate(long vendorID) {
        String query = "DELETE FROM VendorPrivate WHERE VendorID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, vendorID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public VendorPrivate getVendorPrivateByVendorID(long vendorID) {
        String query = "SELECT * FROM VendorPrivate WHERE VendorID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, vendorID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                VendorPrivate vendorPrivate = new VendorPrivate();
                vendorPrivate.setVendorID(resultSet.getLong("VendorID"));
                vendorPrivate.setPersonSSN(resultSet.getInt("PersonSSN"));
                return vendorPrivate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<VendorPrivate> getAllVendorPrivates() {
        List<VendorPrivate> vendorPrivates = new ArrayList<>();
        String query = "SELECT * FROM VendorPrivate";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                VendorPrivate vendorPrivate = new VendorPrivate();
                vendorPrivate.setVendorID(resultSet.getLong("VendorID"));
                vendorPrivate.setPersonSSN(resultSet.getInt("PersonSSN"));
                vendorPrivates.add(vendorPrivate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendorPrivates;
    }

    public void updateVendorPrivate(VendorPrivate vendorPrivate) {
        String query = "UPDATE VendorPrivate SET PersonSSN = ? WHERE VendorID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vendorPrivate.getPersonSSN());
            statement.setLong(2, vendorPrivate.getVendorID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
