package Entities;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.Helper.DatabaseHelper.connection;

public class Product {
    public String SKU;
    public String productName;
    public Integer stock;
    public String productDescription;
    public BigDecimal unitPrice;
    public BigDecimal discount;
    public Long vendorID;
    public Long categoryID;

    public String getSKU() {
        return SKU;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getStock() {
        return stock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public Long getVendorID() {
        return vendorID;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    // Setter methods
    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setVendorID(Long vendorID) {
        this.vendorID = vendorID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public void insertProduct(Product product) {
        String query = "INSERT INTO Product (SKU, ProductName, Stock, ProductDescription, UnitPrice, Discount, VendorID, CategoryID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getSKU());
            statement.setString(2, product.getProductName());
            statement.setInt(3, product.getStock());
            statement.setString(4, product.getProductDescription());
            statement.setBigDecimal(5, product.getUnitPrice());
            statement.setBigDecimal(6, product.getDiscount());
            statement.setLong(7, product.getVendorID());
            statement.setLong(8, product.getCategoryID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(String SKU) {
        String query = "DELETE FROM Product WHERE SKU = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, SKU);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProductBySKU(String SKU) {
        String query = "SELECT * FROM Product WHERE SKU = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, SKU);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product();
                product.setSKU(resultSet.getString("SKU"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setStock(resultSet.getInt("Stock"));
                product.setProductDescription(resultSet.getString("ProductDescription"));
                product.setUnitPrice(resultSet.getBigDecimal("UnitPrice"));
                product.setDiscount(resultSet.getBigDecimal("Discount"));
                product.setVendorID(resultSet.getLong("VendorID"));
                product.setCategoryID(resultSet.getLong("CategoryID"));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setSKU(resultSet.getString("SKU"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setStock(resultSet.getInt("Stock"));
                product.setProductDescription(resultSet.getString("ProductDescription"));
                product.setUnitPrice(resultSet.getBigDecimal("UnitPrice"));
                product.setDiscount(resultSet.getBigDecimal("Discount"));
                product.setVendorID(resultSet.getLong("VendorID"));
                product.setCategoryID(resultSet.getLong("CategoryID"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void updateProduct(Product product) {
        String query = "UPDATE Product SET ProductName = ?, Stock = ?, ProductDescription = ?, UnitPrice = ?, Discount = ?, VendorID = ?, CategoryID = ? WHERE SKU = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getStock());
            statement.setString(3, product.getProductDescription());
            statement.setBigDecimal(4, product.getUnitPrice());
            statement.setBigDecimal(5, product.getDiscount());
            statement.setLong(6, product.getVendorID());
            statement.setLong(7, product.getCategoryID());
            statement.setString(8, product.getSKU());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
