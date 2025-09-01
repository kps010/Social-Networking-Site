package Login;

import java.io.*;
import java.sql.*;

public class Post {
    String post_id;
    String text;
    File f;
    static int likes;
    static int comments;

    public Post(String post_id, String text, File f, int likes, int comments) {
        this.post_id = post_id;
        this.text = text;
        this.f = f;
        this.likes = likes;
        this.comments = comments;
    }

    static int getLikes() {
        return likes;
    }

    static int getComments() {
        return comments;
    }

    static int totalPosts(String username) {
        Connection con = ConnectionClass.getConnection();
        int count = 0;
        try {
            String sql = "select count(*) from post where username = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            } else {
                count = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
