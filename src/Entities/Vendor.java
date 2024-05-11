package Entities;

import Models.VendorTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static Helper.DatabaseHelper.connection;

public class Vendor {
    public Long vendorID;
    public String vendorName;
    public String vendorAddress;
    public String vendorPhoneNumber;
    public String vendorEmail;
    public String type;
    public Integer typeID;

    public Long getVendorID() {return this.vendorID;}
    public String getVendorName() {return this.vendorName;}
    public String getVendorAddress() {return this.vendorAddress;}
    public String getVendorPhoneNumber() {return this.vendorPhoneNumber;}
    public String getVendorEmail() {return this.vendorEmail;}

    public void setVendorID(long vendorID) {this.vendorID = vendorID;}
    public void setVendorName(String vendorName) {this.vendorName = vendorName;}
    public void setVendorAddress(String vendorAddress) {this.vendorAddress = vendorAddress;}
    public void setVendorPhoneNumber(String vendorPhoneNumber) {this.vendorPhoneNumber = vendorPhoneNumber;}
    public void setVendorEmail(String vendorEmail) {this.vendorEmail = vendorEmail;}

    public void insertVendor(Vendor vendor) {
        String query = "INSERT INTO Vendor (VendorName, VendorAddress, VendorPhoneNumber, VendorEmail) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, vendor.getVendorName());
            statement.setString(2, vendor.getVendorAddress());
            statement.setString(3, vendor.getVendorPhoneNumber());
            statement.setString(4, vendor.getVendorEmail());
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Long generatedId = generatedKeys.getLong(1);
                        System.out.println("Generated ID: " + generatedId);
                        switch (type) {
                            case "Private":
                                VendorPrivate vendorPrivate = new VendorPrivate();
                                vendorPrivate.vendorID = generatedId;
                                vendorPrivate.personSSN = vendor.typeID;
                                vendorPrivate.insertVendorPrivate(vendorPrivate);
                                break;
                            case "Business":
                                VendorBusiness vendorBusiness = new VendorBusiness();
                                vendorBusiness.vendorID = generatedId;
                                vendorBusiness.contractNumber = vendor.typeID;
                                vendorBusiness.insertVendorBusiness(vendorBusiness);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVendor(long vendorID) {
        String query = "DELETE FROM Vendor WHERE VendorID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, vendorID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vendor getVendorById(long vendorID) {
        String query = "SELECT * FROM Vendor WHERE VendorID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, vendorID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Vendor vendor = new Vendor();
                vendor.setVendorID(resultSet.getLong("VendorID"));
                vendor.setVendorName(resultSet.getString("VendorName"));
                vendor.setVendorAddress(resultSet.getString("VendorAddress"));
                vendor.setVendorPhoneNumber(resultSet.getString("VendorPhoneNumber"));
                vendor.setVendorEmail(resultSet.getString("VendorEmail"));
                return vendor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Vendor> getAllVendors() {
        List<Vendor> vendors = new ArrayList<>();
        String query = "SELECT * FROM Vendor";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Vendor vendor = new Vendor();
                vendor.setVendorID(resultSet.getLong("VendorID"));
                vendor.setVendorName(resultSet.getString("VendorName"));
                vendor.setVendorAddress(resultSet.getString("VendorAddress"));
                vendor.setVendorPhoneNumber(resultSet.getString("VendorPhoneNumber"));
                vendor.setVendorEmail(resultSet.getString("VendorEmail"));
                vendors.add(vendor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendors;
    }

    public List<VendorTypes> getAllVendorsJoined() {
        List<VendorTypes> vendors = new ArrayList<>();
        String query = "SELECT v.*, 1 as VendorType, vp.PersonSSN as TypeID from Vendor v, VendorPrivate vp where v.VendorID = vp.VendorID " +
                        "UNION " +
                        "SELECT v.*, 2 as VendorType, vb.ContractNumber as TypeID from Vendor v, VendorBusiness vb where v.VendorID = vb.VendorID;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                VendorTypes vendor = new VendorTypes();
                vendor.setVendorID(resultSet.getLong("VendorID"));
                vendor.setVendorName(resultSet.getString("VendorName"));
                vendor.setVendorAddress(resultSet.getString("VendorAddress"));
                vendor.setVendorPhoneNumber(resultSet.getString("VendorPhoneNumber"));
                vendor.setVendorEmail(resultSet.getString("VendorEmail"));
                var type = resultSet.getInt("VendorType");
                if (type == 1) {
                    vendor.Type = "Private";
                }
                else vendor.Type = "Business";
                vendor.TypeIdentity = resultSet.getInt("TypeID");
                vendors.add(vendor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendors;
    }

    public void updateVendor(Vendor vendor) {
        String query = "UPDATE Vendor SET VendorName = ?, VendorAddress = ?, VendorPhoneNumber = ?, VendorEmail = ? WHERE VendorID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, vendor.getVendorName());
            statement.setString(2, vendor.getVendorAddress());
            statement.setString(3, vendor.getVendorPhoneNumber());
            statement.setString(4, vendor.getVendorEmail());
            statement.setLong(5, vendor.getVendorID());
            statement.executeUpdate();
            switch (vendor.type) {
                case "Private":
                    VendorPrivate vendorPrivate = new VendorPrivate();
                    vendorPrivate.vendorID = vendor.getVendorID();
                    vendorPrivate.personSSN = vendor.typeID;
                    vendorPrivate.updateVendorPrivate(vendorPrivate);
                    break;
                case "Business":
                    VendorBusiness vendorBusiness = new VendorBusiness();
                    vendorBusiness.vendorID = vendor.getVendorID();
                    vendorBusiness.contractNumber = vendor.typeID;
                    vendorBusiness.updateVendorBusiness(vendorBusiness);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
