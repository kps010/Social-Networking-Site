package DS;

import java.sql.*;

import javax.swing.JOptionPane;

import Login.*;
import Functionalities.*;

public class getLikeLL {

    String query;
    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    int r;
    static LL likes = new LL();

    public getLikeLL(String id) {
        con = ConnectionClass.getConnection();

        try {
            query = "select username from likes where post_id=?";
            pst = con.prepareStatement(query);
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                likes.insert(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            e.getStackTrace();
        }
    }

    public static LL getLL() {
        return likes;
    }
}
