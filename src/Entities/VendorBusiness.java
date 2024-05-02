package Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.Helper.DatabaseHelper.connection;

public class VendorBusiness {
    public Long vendorID;
    public Integer contractNumber;

    public void setVendorID(Long vendorID) {
        this.vendorID = vendorID;
    }

    public void setContractNumber(Integer contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Long getVendorID() {
        return vendorID;
    }

    public Integer getContractNumber() {
        return contractNumber;
    }

    public void insertVendorBusiness(VendorBusiness vendorBusiness) {
        String query = "INSERT INTO VendorBusiness (VendorID, ContractNumber) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, vendorBusiness.getVendorID());
            statement.setInt(2, vendorBusiness.getContractNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVendorBusiness(long vendorID) {
        String query = "DELETE FROM VendorBusiness WHERE VendorID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, vendorID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public VendorBusiness getVendorBusinessByVendorID(long vendorID) {
        String query = "SELECT * FROM VendorBusiness WHERE VendorID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, vendorID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                VendorBusiness vendorBusiness = new VendorBusiness();
                vendorBusiness.setVendorID(resultSet.getLong("VendorID"));
                vendorBusiness.setContractNumber(resultSet.getInt("ContractNumber"));
                return vendorBusiness;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<VendorBusiness> getAllVendorBusinesses() {
        List<VendorBusiness> vendorBusinesses = new ArrayList<>();
        String query = "SELECT * FROM VendorBusiness";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                VendorBusiness vendorBusiness = new VendorBusiness();
                vendorBusiness.setVendorID(resultSet.getLong("VendorID"));
                vendorBusiness.setContractNumber(resultSet.getInt("ContractNumber"));
                vendorBusinesses.add(vendorBusiness);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendorBusinesses;
    }

    public void updateVendorBusiness(VendorBusiness vendorBusiness) {
        String query = "UPDATE VendorBusiness SET ContractNumber = ? WHERE VendorID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vendorBusiness.getContractNumber());
            statement.setLong(2, vendorBusiness.getVendorID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
