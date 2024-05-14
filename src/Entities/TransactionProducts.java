package Entities;

import Models.TransactionProductDetail;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Helper.DatabaseHelper.connection;

public class TransactionProducts {
    public Long transactionID;
    public String SKU;
    public Integer quantity;
    public BigDecimal overallDiscount;

    public Long getTransactionID() {
        return transactionID;
    }

    public String getSKU() {
        return SKU;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getOverallDiscount() {
        return overallDiscount;
    }

    // Setter methods
    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setOverallDiscount(BigDecimal overallDiscount) {
        this.overallDiscount = overallDiscount;
    }

    public void insertTransactionProduct(TransactionProducts transactionProduct) {
        String query = "INSERT INTO TransactionProducts (TransactionID, SKU, Quantity, OverallDiscount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, transactionProduct.getTransactionID());
            statement.setString(2, transactionProduct.getSKU());
            statement.setInt(3, transactionProduct.getQuantity());
            statement.setBigDecimal(4, transactionProduct.getOverallDiscount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTransactionProduct(long transactionID, String SKU) {
        String query = "DELETE FROM TransactionProducts WHERE TransactionID = ? AND SKU = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, transactionID);
            statement.setString(2, SKU);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TransactionProducts getTransactionProductById(long transactionID, String SKU) {
        String query = "SELECT * FROM TransactionProducts WHERE TransactionID = ? AND SKU = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, transactionID);
            statement.setString(2, SKU);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TransactionProducts transactionProduct = new TransactionProducts();
                transactionProduct.setTransactionID(resultSet.getLong("TransactionID"));
                transactionProduct.setSKU(resultSet.getString("SKU"));
                transactionProduct.setQuantity(resultSet.getInt("Quantity"));
                transactionProduct.setOverallDiscount(resultSet.getBigDecimal("OverallDiscount"));
                return transactionProduct;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TransactionProductDetail> getTransactionProductsByTransactionId(long transactionID) {
        List<TransactionProductDetail> transactionProducts = new ArrayList<>();
        String query = "SELECT tp.*, p.ProductName, p.UnitPrice, p.Discount FROM TransactionProducts tp JOIN Product p ON tp.SKU = p.SKU WHERE TransactionID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, transactionID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TransactionProductDetail transactionProduct = new TransactionProductDetail();
                transactionProduct.setTransactionID(resultSet.getLong("TransactionID"));
                transactionProduct.setSKU(resultSet.getString("SKU"));
                transactionProduct.setQuantity(resultSet.getInt("Quantity"));
                transactionProduct.setOverallDiscount(resultSet.getBigDecimal("OverallDiscount"));
                transactionProduct.productName = resultSet.getString("ProductName").toString();
                transactionProduct.unitPrice = resultSet.getBigDecimal("UnitPrice");
                transactionProduct.discount = resultSet.getBigDecimal("Discount");
                transactionProducts.add(transactionProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionProducts;
    }

    public void updateTransactionProduct(TransactionProducts transactionProduct) {
        String query = "UPDATE TransactionProducts SET Quantity = ?, OverallDiscount = ? WHERE TransactionID = ? AND SKU = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, transactionProduct.getQuantity());
            statement.setBigDecimal(2, transactionProduct.getOverallDiscount());
            statement.setLong(3, transactionProduct.getTransactionID());
            statement.setString(4, transactionProduct.getSKU());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
