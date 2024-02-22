import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/weatherApp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "4321";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
