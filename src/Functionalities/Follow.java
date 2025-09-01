package Functionalities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DS.LL;
import DS.getFollowerLL;
import DS.getFollowingLL;
import Login.ConnectionClass;
import Login.User;

public class Follow extends JFrame {
    JFrame loginFrame;
    JPanel headPanel;
    JPanel oldPanel;
    JPanel followPanel;
    JScrollPane followScrollBar;
    User user;
    Connection con;
    Statement st;
    String query;
    PreparedStatement pst;
    CallableStatement cst;
    ResultSet rs;
    int r;

    public Follow(JFrame of, JPanel mainPanel, JPanel sPanel, User user, String username, String type) {
        con = ConnectionClass.getConnection();
        loginFrame = of;
        headPanel = mainPanel;
        oldPanel = sPanel;
        this.user = user;

        followPanel = new JPanel();
        followPanel.setLayout(new BoxLayout(followPanel, BoxLayout.Y_AXIS));
        // followPanel.setBounds(0, 100, 1266, 768);
        followPanel.setBounds(0, 0, 400, 600);
        followPanel.setVisible(true);

        followScrollBar = new JScrollPane();
        followScrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        followScrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // followScrollBar.setBounds(0, 0, 1366, 748);
        followScrollBar.setBounds(0, 0, 400, 600);
        followScrollBar.setVisible(true);
        followScrollBar.setViewportView(followPanel);

        followPanel.removeAll();
        if (type.equalsIgnoreCase("following")) {
            try {
                query = "select user_main from following where user_follows=?";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                rs = pst.executeQuery();
                System.out.println("following");
                while (rs.next()) {
                    JLabel nameLabel = new JLabel(rs.getString(1));
                    nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    followPanel.add(nameLabel);
                    followPanel.add(Box.createRigidArea(new Dimension(0, 5)));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                e.getStackTrace();
            }
            followPanel.repaint();
            followPanel.revalidate();
        } else if (type.equalsIgnoreCase("followers")) {
            try {
                query = "select user_follows from following where user_main=?";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                rs = pst.executeQuery();
                System.out.println("following");
                while (rs.next()) {
                    JLabel nameLabel = new JLabel(rs.getString(1));
                    nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    followPanel.add(nameLabel);
                    followPanel.add(Box.createRigidArea(new Dimension(0, 5)));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                e.getStackTrace();
            }
            followPanel.repaint();
            followPanel.revalidate();
        }

        // headPanel.add(followScrollBar);
        // headPanel.revalidate();
        // headPanel.repaint();
        this.setBounds(400, 100, 400, 600);
        this.getContentPane().add(followScrollBar);
        this.setTitle(type);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(new Dimension(400, 600));
    }
}