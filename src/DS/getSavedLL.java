package DS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Login.ConnectionClass;

public class getSavedLL {
    String query;
    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    int r;
    static LL save = new LL();

    public getSavedLL(String id) {
        con = ConnectionClass.getConnection();

        try {
            query = "select username from saved where post_id=?";
            pst = con.prepareStatement(query);
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                save.insert(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            e.getStackTrace();
        }

    }

    public static LL getLL() {
        return save;
    }
}
