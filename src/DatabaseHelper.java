import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    // JDBC URL, username, and password of MySQL server
    String url = "jdbc:mysql://localhost:3306/1111111111111111111";
    String user = "root";
    String password = "Rv00198269!@";

    // JDBC variables for opening, managing and closing connection
    Connection connection = null;
    public void setup() {
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
}
