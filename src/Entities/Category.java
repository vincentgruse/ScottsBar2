package Entities;

import Helper.DatabaseHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Category {
    public Long categoryID;
    public String categoryTitle;

    public void insertCategory(Category category) {
        String query = "INSERT INTO Category (CategoryTitle) VALUES (?)";
        try (PreparedStatement statement = DatabaseHelper.connection.prepareStatement(query)) {
            statement.setString(2, category.categoryTitle);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(long categoryID) {
        String query = "DELETE FROM Category WHERE CategoryID = ?";
        try (PreparedStatement statement = DatabaseHelper.connection.prepareStatement(query)) {
            statement.setLong(1, categoryID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category getCategoryById(long categoryID) {
        String query = "SELECT * FROM Category WHERE CategoryID = ?";
        try (PreparedStatement statement = DatabaseHelper.connection.prepareStatement(query)) {
            statement.setLong(1, categoryID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Category category = new Category();
                category.categoryID = resultSet.getLong("CategoryID");
                category.categoryTitle = resultSet.getString("CategoryTitle");
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Category";
        try (PreparedStatement statement = DatabaseHelper.connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.categoryID = resultSet.getLong("CategoryID");
                category.categoryTitle = resultSet.getString("CategoryTitle");
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public void updateCategory(Category category) {
        String query = "UPDATE Category SET CategoryTitle = ? WHERE CategoryID = ?";
        try (PreparedStatement statement = DatabaseHelper.connection.prepareStatement(query)) {
            statement.setString(1, category.categoryTitle);
            statement.setLong(5, category.categoryID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
