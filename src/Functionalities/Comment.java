package Functionalities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
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
import javax.swing.JTextArea;

import Login.ConnectionClass;
import Login.User;

public class Comment extends JFrame {

    JFrame loginFrame;
    JPanel headPanel;
    JPanel oldPanel;
    JPanel commPanel;
    JScrollPane commScrollBar;
    User user;
    Connection con;
    Statement st;
    String query;
    PreparedStatement pst;
    CallableStatement cst;
    ResultSet rs;
    int r;

    public Comment(JFrame of, JPanel mainPanel, JPanel sPanel, User user, String post_id) {
        con = ConnectionClass.getConnection();
        loginFrame = of;
        headPanel = mainPanel;
        oldPanel = sPanel;
        this.user = user;

        commPanel = new JPanel();
        commPanel.setLayout(new BoxLayout(commPanel, BoxLayout.Y_AXIS));
        // commPanel.setBounds(0, 100, 1266, 768);
        commPanel.setBounds(0, 0, 400, 600);
        commPanel.setVisible(true);

        commScrollBar = new JScrollPane();
        commScrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        commScrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // commScrollBar.setBounds(0, 0, 1366, 748);
        commScrollBar.setBounds(0, 0, 400, 600);
        commScrollBar.setVisible(true);
        commScrollBar.setViewportView(commPanel);

        JButton cross = new JButton(new ImageIcon("cross.png"));
        cross.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cross.setFocusable(false);
        cross.setOpaque(false);
        cross.setContentAreaFilled(false);
        cross.setBorderPainted(false);
        cross.setVisible(false);
        cross.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // commPanel.removeAll();
                oldPanel.setVisible(true);
            }
        });
        cross.setAlignmentX(Component.TOP_ALIGNMENT);

        JButton add = new JButton(new ImageIcon("plus.png"));
        add.setAlignmentX(Component.BOTTOM_ALIGNMENT);
        add.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.GRAY));
        add.setFont(new Font("Poppins", Font.PLAIN, 18));
        add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add.setFocusable(false);
        add.setBackground(Color.GRAY);
        add.setVisible(true);

        System.out.println("before action listner");

        boolean flag = false;

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == add) {
                    try {
                        Date d = new Date();
                        java.sql.Date date = new java.sql.Date(d.getTime());
                        String com = JOptionPane.showInputDialog(null,
                                "Enter Comment", "Comment",
                                JOptionPane.OK_CANCEL_OPTION);
                        query = "insert into comments values(?,?,?,?)";
                        pst = con.prepareStatement(query);
                        pst.setString(1, post_id);
                        pst.setString(2, User.getName());
                        pst.setString(3, com);
                        pst.setDate(4, date);
                        r = pst.executeUpdate();

                        // commPanel.removeAll();
                        commScrollBar.setVisible(false);
                        new Comment(loginFrame, headPanel, oldPanel, user, post_id);
                    } catch (Exception e1) {
                        // TODO: handle exception
                        e1.getStackTrace();
                    }
                }
            }

        });

        commPanel.add(add);

        try {
            query = "{call getCommCount(?,?)}";
            cst = con.prepareCall(query);
            cst.setString(1, post_id);
            cst.execute();
            if (cst.getInt(2) > 0) {

                query = "select * from comments where post_id=?";
                pst = con.prepareStatement(query);
                pst.setString(1, post_id);
                rs = pst.executeQuery();

                while (rs.next()) {

                    System.out.println("while");

                    JPanel postPanel = new JPanel();
                    postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
                    postPanel.setBorder(
                            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
                    // postPanel.setBackground(Color.WHITE);
                    postPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    postPanel.setMaximumSize(
                            new Dimension(commPanel.getWidth() - 40, Integer.MAX_VALUE));

                    System.out.println("while1");

                    JLabel usernameLabel = new JLabel(rs.getString("username"));
                    usernameLabel.setFont(new Font("Arial", Font.BOLD, 22));
                    usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    postPanel.add(usernameLabel);
                    postPanel.add(Box.createRigidArea(new Dimension(0, 5)));

                    System.out.println("while2");

                    // JLabel text = new JLabel(rs.getString("text"));
                    JLabel text = new JLabel(rs.getString("comment"));
                    text.setFont(new Font("Arial", Font.PLAIN, 20));
                    text.setAlignmentX(Component.LEFT_ALIGNMENT);
                    postPanel.add(text);
                    postPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                    JLabel date = new JLabel(rs.getDate("date").toString());
                    date.setMaximumSize(new Dimension(Integer.MAX_VALUE, 16));
                    date.setFont(new Font("Arial", Font.ITALIC, 15));
                    date.setForeground(Color.GRAY);
                    date.setAlignmentX(Component.LEFT_ALIGNMENT);
                    postPanel.add(Box.createRigidArea(new Dimension(0, 5)));
                    postPanel.add(date);

                    commPanel.add(cross);

                    postPanel.setVisible(true);
                    postPanel.revalidate();
                    postPanel.repaint();

                    commPanel.add(postPanel);
                    commPanel.add(Box.createRigidArea(new Dimension(0, 20)));

                    commPanel.revalidate();
                    commPanel.repaint();

                    System.out.println("endd");
                }
            } else {
                System.out.println("inside else");
                JOptionPane.showMessageDialog(null, "NO comments", "Comments", JOptionPane.PLAIN_MESSAGE);
                // commPanel.removeAll();
                // commScrollBar.setVisible(false);
                // new Comment(loginFrame, headPanel, oldPanel, user, post_id);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        // headPanel.add(commScrollBar);
        // headPanel.revalidate();
        // headPanel.repaint();
        this.setBounds(400, 100, 400, 600);
        this.getContentPane().add(commScrollBar);
        this.setTitle("Social Networking Site");
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(new Dimension(400, 600));
    }

}
