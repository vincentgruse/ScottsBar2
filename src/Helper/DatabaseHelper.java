package Helper;

import java.sql.*;

public class DatabaseHelper {

    // JDBC URL, username, and password of MySQL server
    private static final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=America/New_York#/mrajput1db";
    private static final String user = "mrajput1";
    private static final String password = "COSC*8gaw0";

    // JDBC variables for opening, managing and closing connection
    public static Connection connection = null;
    public static void setup() {
        try {
            // Connecting to the MySQL database
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database");
                // Perform database operations here
            } else {
                System.out.println("Failed to make connection!");
            }
        }
        catch (SQLException e) {
            System.err.println("Failed to connect to the database!");
            e.printStackTrace();
        }
        finally {
        }
    }

    public void destoryConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
