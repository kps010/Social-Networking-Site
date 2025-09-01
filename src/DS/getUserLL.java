package DS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Login.ConnectionClass;

public class getUserLL {
    String query;
    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    int r;
    static UserLL user = new UserLL();

    public getUserLL() {
        con = ConnectionClass.getConnection();

        try {
            query = "select username,password from user";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                user.insert(rs.getString(1), rs.getString(2));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            e.getStackTrace();
        }
    }

    public static UserLL getLL() {
        return user;
    }
}
