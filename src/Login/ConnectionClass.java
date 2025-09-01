package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    public static Connection con;

    public static Connection getConnection() {
        if (con == null) {
            String dbUrl = "jdbc:mysql://localhost:3306/social_networking_site";
            String dbUser = "root";
            String dbPass = "";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return con;
        } else {
            return con;
        }
    }
}
