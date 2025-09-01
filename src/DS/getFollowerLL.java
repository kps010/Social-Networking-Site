package DS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Login.ConnectionClass;

public class getFollowerLL {
    String query;
    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    int r;
    static LL following = new LL();

    public getFollowerLL(String user) {
        con = ConnectionClass.getConnection();

        // user_follows => who clicks follow button
        // user_main => whose id or post displayed

        try {
            query = "select user_follows from following where user_main=?";
            pst = con.prepareStatement(query);
            pst.setString(1, user);
            rs = pst.executeQuery();
            while (rs.next()) {
                following.insert(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            e.getStackTrace();
        }
    }

    public static LL getLL() {
        return following;
    }
}
