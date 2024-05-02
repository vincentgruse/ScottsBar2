package Entities;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.Helper.DatabaseHelper.connection;

public class Liquor {
    public Long categoryID;
    public BigDecimal liquorTax;

    public Long getCategoryID() {
        return categoryID;
    }

    public BigDecimal getLiquorTax() {
        return liquorTax;
    }

    // Setter methods
    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public void setLiquorTax(BigDecimal liquorTax) {
        this.liquorTax = liquorTax;
    }

    public void insertLiquor(Liquor liquor) {
        String query = "INSERT INTO Liquor (LiquorTax) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBigDecimal(1, liquor.getLiquorTax());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLiquor(long categoryID) {
        String query = "DELETE FROM Liquor WHERE CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, categoryID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Liquor getLiquorByCategoryID(long categoryID) {
        String query = "SELECT * FROM Liquor WHERE CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, categoryID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Liquor liquor = new Liquor();
                liquor.setCategoryID(resultSet.getLong("CategoryID"));
                liquor.setLiquorTax(resultSet.getBigDecimal("LiquorTax"));
                return liquor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Liquor> getAllLiquors() {
        List<Liquor> liquors = new ArrayList<>();
        String query = "SELECT * FROM Liquor";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Liquor liquor = new Liquor();
                liquor.setCategoryID(resultSet.getLong("CategoryID"));
                liquor.setLiquorTax(resultSet.getBigDecimal("LiquorTax"));
                liquors.add(liquor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liquors;
    }

    public void updateLiquor(Liquor liquor) {
        String query = "UPDATE Liquor SET LiquorTax = ? WHERE CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBigDecimal(1, liquor.getLiquorTax());
            statement.setLong(2, liquor.getCategoryID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
