package src.Entities;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.Helper.DatabaseHelper.connection;

public class Grocery {
    public Long categoryID;
    public BigDecimal salesTax;

    public Long getCategoryID() {
        return categoryID;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    // Setter methods
    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    public void insertGrocery(Grocery grocery) {
        String query = "INSERT INTO Grocery (SalesTax) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBigDecimal(1, grocery.getSalesTax());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGrocery(long categoryID) {
        String query = "DELETE FROM Grocery WHERE CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, categoryID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Grocery getGroceryByCategoryID(long categoryID) {
        String query = "SELECT * FROM Grocery WHERE CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, categoryID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Grocery grocery = new Grocery();
                grocery.setCategoryID(resultSet.getLong("CategoryID"));
                grocery.setSalesTax(resultSet.getBigDecimal("SalesTax"));
                return grocery;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Grocery> getAllGroceries() {
        List<Grocery> groceries = new ArrayList<>();
        String query = "SELECT * FROM Grocery";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Grocery grocery = new Grocery();
                grocery.setCategoryID(resultSet.getLong("CategoryID"));
                grocery.setSalesTax(resultSet.getBigDecimal("SalesTax"));
                groceries.add(grocery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groceries;
    }

    public void updateGrocery(Grocery grocery) {
        String query = "UPDATE Grocery SET SalesTax = ? WHERE CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBigDecimal(1, grocery.getSalesTax());
            statement.setLong(2, grocery.getCategoryID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
