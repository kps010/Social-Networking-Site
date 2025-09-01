package Functionalities;

import java.util.*;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import DS.*;
import Login.*;

public class Home implements ActionListener {

    Connection con;
    User user;
    JFrame loginFrame;
    JPanel headPanel;
    JPanel homePanel;
    JPanel scrollPanel;
    static JButton home;
    static JButton create;
    static JButton notifications;
    static JButton setting;
    static JButton search;

    Statement st;
    String query;
    PreparedStatement pst;
    CallableStatement cst;
    ResultSet rs;
    int r;

    public Home(JFrame of, JPanel mainPanel, JPanel pPanel, User user) {
        con = ConnectionClass.getConnection();
        loginFrame = of;
        headPanel = mainPanel;
        homePanel = pPanel;
        this.user = user;

        home = HomePage.getHome();
        create = HomePage.getCreate();
        notifications = HomePage.getNotifications();
        setting = HomePage.getSetting();
        search = HomePage.getSearch();

        scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.setBounds(0, 100, 1216, 768);
        scrollPanel.setVisible(true);

        JScrollPane scrollBar = new JScrollPane();
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollBar.setBounds(0, 0, 1266, 748);
        scrollBar.setVisible(true);
        scrollBar.setViewportView(scrollPanel);

        // getFollowingLL l = new getFollowingLL(user.getName());
        // LL following = l.getLL();

        // LL.node temp = following.first;

        try {
            query = "select * from post where username in(select user_main from following where user_follows=?) order by date desc";
            pst = con.prepareStatement(query);
            pst.setString(1, user.getName());
            rs = pst.executeQuery();

            while (rs.next()) {
                String post_id = rs.getString("post_id");
                JPanel postPanel = new JPanel();
                postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
                postPanel.setBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
                // postPanel.setBackground(Color.WHITE);
                postPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                postPanel.setMaximumSize(
                        new Dimension(scrollPanel.getWidth() - 40, Integer.MAX_VALUE));

                JLabel usernameLabel = new JLabel(rs.getString("username"));
                usernameLabel.setFont(new Font("Arial", Font.BOLD, 22));
                usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                postPanel.add(usernameLabel);
                postPanel.add(Box.createRigidArea(new Dimension(0, 5)));

                // JLabel text = new JLabel(rs.getString("text"));
                JLabel text = new JLabel("<html><body style='width: 600px'>"
                        + rs.getString("text") + "</body></html>");
                text.setFont(new Font("Arial", Font.PLAIN, 20));
                text.setAlignmentX(Component.LEFT_ALIGNMENT);
                postPanel.add(text);
                postPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                String file_path = rs.getString("photo_name");
                if (!file_path.equals("default.png")) {
                    File f = new File(
                            "C:\\Users\\shail\\OneDrive\\Desktop\\Group Project\\Downloads\\"
                                    + file_path);
                    // BufferedImage bi = ImageIO.read(f);
                    Blob b = rs.getBlob("photo");
                    byte[] arr = b.getBytes(1, (int) b.length());
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(arr);
                    fos.close();
                    ImageIcon icon = new ImageIcon(f.getAbsolutePath());
                    Image i = icon.getImage().getScaledInstance(300, -1,
                            Image.SCALE_SMOOTH);
                    JLabel image = new JLabel(new ImageIcon(i));
                    // JLabel image = new JLabel(icon);
                    postPanel.add(image);
                    postPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                }

                JLabel date = new JLabel(rs.getDate("date").toString());
                date.setMaximumSize(new Dimension(Integer.MAX_VALUE, 16));
                date.setFont(new Font("Arial", Font.ITALIC, 15));
                date.setForeground(Color.GRAY);
                date.setAlignmentX(Component.LEFT_ALIGNMENT);
                postPanel.add(Box.createRigidArea(new Dimension(0, 5)));
                postPanel.add(date);

                JLabel likedLabel = new JLabel(new ImageIcon("heartX.png"));
                JLabel unLikedLabel = new JLabel(new ImageIcon("heart.png"));
                ImageIcon commIcon = new ImageIcon("chat.png");
                JLabel savedLabel = new JLabel(new ImageIcon("save(1).png"));
                JLabel unSavedLabel = new JLabel(new ImageIcon("save.png"));
                likedLabel.setVisible(false);
                unLikedLabel.setVisible(true);
                savedLabel.setVisible(false);
                unSavedLabel.setVisible(true);

                JPanel fun = new JPanel(new FlowLayout());
                fun.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
                JButton like = new JButton();
                like.setOpaque(false);
                like.setContentAreaFilled(false);
                like.setAlignmentX(FlowLayout.LEFT);
                like.setBorderPainted(false);
                like.setCursor(new Cursor(Cursor.HAND_CURSOR));
                like.add(likedLabel);
                like.add(unLikedLabel);

                getLikeLL l = new getLikeLL(post_id);
                LL liked_user = l.getLL();
                if (liked_user.contains(user.getName())) {
                    // like.setIcon(new ImageIcon("heartx.png"));
                    likedLabel.setVisible(true);
                    unLikedLabel.setVisible(false);
                    // like.remove(unLikedLabel);
                } else {
                    // like.remove(likedLabel);
                    // like.setIcon(new ImageIcon("heart.png"));
                    likedLabel.setVisible(false);
                    unLikedLabel.setVisible(true);
                }
                liked_user.deleteAll();
                JButton comment = new JButton();
                comment.setOpaque(false);
                comment.setContentAreaFilled(false);
                comment.setAlignmentX(FlowLayout.CENTER);
                comment.setBorderPainted(false);
                comment.setCursor(new Cursor(Cursor.HAND_CURSOR));
                comment.setIcon(commIcon);

                JButton save = new JButton();
                save.setOpaque(false);
                save.setContentAreaFilled(false);
                save.setAlignmentX(FlowLayout.RIGHT);
                save.setBorderPainted(false);
                save.setCursor(new Cursor(Cursor.HAND_CURSOR));
                save.add(savedLabel);
                save.add(unSavedLabel);

                getSavedLL s = new getSavedLL(post_id);
                LL saved_user = s.getLL();
                if (saved_user.contains(user.getName())) {
                    // save.setIcon(new ImageIcon("save(1).png"));
                    savedLabel.setVisible(true);
                    unSavedLabel.setVisible(false);
                } else {
                    // save.setIcon(new ImageIcon("save.png"));
                    savedLabel.setVisible(false);
                    unSavedLabel.setVisible(true);
                }
                saved_user.deleteAll();
                fun.add(like);
                fun.add(comment);
                fun.add(save);

                ActionListener funListener = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Date d = new Date();
                            java.sql.Date date = new java.sql.Date(d.getTime());
                            // query = "select CURRENT_TIMESTAMP()";
                            // pst = con.prepareStatement(query);
                            // rs = pst.executeQuery();
                            // Timestamp date =
                            // rs.getTimestamp("CURRENT_TIMESTAMP()");
                            if (e.getSource() == like) {
                                if (unLikedLabel.isVisible()) {
                                    query = "insert into likes values(?,?,?)";
                                    pst = con.prepareStatement(query);
                                    pst.setString(1, post_id);
                                    pst.setString(2, user.getName());
                                    pst.setDate(3, date);
                                    r = pst.executeUpdate();

                                    likedLabel.setVisible(true);
                                    unLikedLabel.setVisible(false);
                                } else {
                                    query = "delete from likes where post_id=? and username=?";
                                    pst = con.prepareStatement(query);
                                    pst.setString(1, post_id);
                                    pst.setString(2, user.getName());
                                    r = pst.executeUpdate();

                                    likedLabel.setVisible(false);
                                    unLikedLabel.setVisible(true);
                                }
                            } else if (e.getSource() == comment) {
                                // String com = JOptionPane.showInputDialog(null,
                                // "Enter Comment", "Comment",
                                // JOptionPane.OK_CANCEL_OPTION);
                                // query = "insert into comments values(?,?,?,?)";
                                // pst = con.prepareStatement(query);
                                // pst.setString(1, post_id);
                                // pst.setString(2, user.getName());
                                // pst.setString(3, com);
                                // pst.setDate(4, date);
                                // r = pst.executeUpdate();
                                new Comment(loginFrame, headPanel, homePanel,
                                        user, post_id);

                            } else if (e.getSource() == save) {
                                if (unSavedLabel.isVisible()) {
                                    query = "insert into saved values(?,?)";
                                    pst = con.prepareStatement(query);
                                    pst.setString(1, post_id);
                                    pst.setString(2, user.getName());
                                    r = pst.executeUpdate();

                                    unSavedLabel.setVisible(false);
                                    savedLabel.setVisible(true);
                                } else {
                                    query = "delete from saved where post_id=? and username=?";
                                    pst = con.prepareStatement(query);
                                    pst.setString(1, post_id);
                                    pst.setString(2, user.getName());
                                    r = pst.executeUpdate();

                                    unSavedLabel.setVisible(true);
                                    savedLabel.setVisible(false);
                                }
                            }
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null, e1.getMessage(),
                                    "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                };

                like.addActionListener(funListener);
                comment.addActionListener(funListener);
                save.addActionListener(funListener);
                // postPanel.setBorder(
                // BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                // postPanel.setBorder(
                // BorderFactory.createCompoundBorder(postPanel.getBorder(),
                // BorderFactory.createEmptyBorder(5, 0, 5, 0)));
                postPanel.add(fun);
                scrollPanel.add(postPanel);
                scrollPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            }
            scrollPanel.revalidate();
            scrollPanel.repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        homePanel.add(scrollBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home) {
            HomePage.homeOpen.setVisible(false);
            HomePage.homeClose.setVisible(true);
            HomePage.createOpen.setVisible(true);
            HomePage.createClose.setVisible(false);
            HomePage.notificationOpen.setVisible(true);
            HomePage.notificationClose.setVisible(false);
            HomePage.settingOpen.setVisible(true);
            HomePage.settingClose.setVisible(false);
            HomePage.searchOpen.setVisible(true);
            HomePage.serachClose.setVisible(false);

            homePanel.removeAll();
            HomePage.mainPanel.setVisible(false);
            homePanel.setVisible(true);
            HomePage.searchPanel.setVisible(false);
            HomePage.createPanel.setVisible(false);
            HomePage.notificationPanel.setVisible(false);
            HomePage.settingPanel.setVisible(false);

            new Home(loginFrame, headPanel, homePanel, user);
        } else if (e.getSource() == create) {
            HomePage.homeOpen.setVisible(true);
            HomePage.homeClose.setVisible(false);
            HomePage.createOpen.setVisible(false);
            HomePage.createClose.setVisible(true);
            HomePage.notificationOpen.setVisible(true);
            HomePage.notificationClose.setVisible(false);
            HomePage.settingOpen.setVisible(true);
            HomePage.settingClose.setVisible(false);
            HomePage.searchOpen.setVisible(true);
            HomePage.serachClose.setVisible(false);

            HomePage.mainPanel.setVisible(false);
            HomePage.homePanel.setVisible(false);
            HomePage.searchPanel.setVisible(false);
            HomePage.createPanel.setVisible(true);
            HomePage.notificationPanel.setVisible(false);
            HomePage.settingPanel.setVisible(false);
            new Create(loginFrame, headPanel, HomePage.createPanel, user);
        } else if (e.getSource() == notifications) {
            HomePage.homeOpen.setVisible(true);
            HomePage.homeClose.setVisible(false);
            HomePage.createOpen.setVisible(true);
            HomePage.createClose.setVisible(false);
            HomePage.notificationOpen.setVisible(false);
            HomePage.notificationClose.setVisible(true);
            HomePage.settingOpen.setVisible(true);
            HomePage.settingClose.setVisible(false);
            HomePage.searchOpen.setVisible(true);
            HomePage.serachClose.setVisible(false);

            HomePage.notificationPanel.removeAll();
            HomePage.mainPanel.setVisible(false);
            HomePage.homePanel.setVisible(false);
            HomePage.searchPanel.setVisible(false);
            HomePage.createPanel.setVisible(false);
            HomePage.notificationPanel.setVisible(true);
            HomePage.settingPanel.setVisible(false);
            new Notifications(loginFrame, headPanel, HomePage.notificationPanel, user);
        } else if (e.getSource() == setting) {
            HomePage.homeOpen.setVisible(true);
            HomePage.homeClose.setVisible(false);
            HomePage.createOpen.setVisible(true);
            HomePage.createClose.setVisible(false);
            HomePage.notificationOpen.setVisible(true);
            HomePage.notificationClose.setVisible(false);
            HomePage.settingOpen.setVisible(false);
            HomePage.settingClose.setVisible(true);
            HomePage.searchOpen.setVisible(true);
            HomePage.serachClose.setVisible(false);

            HomePage.mainPanel.setVisible(false);
            HomePage.homePanel.setVisible(false);
            HomePage.searchPanel.setVisible(false);
            HomePage.createPanel.setVisible(false);
            HomePage.notificationPanel.setVisible(false);
            HomePage.settingPanel.setVisible(true);
            HomePage.funPanel.setVisible(false);
            new Setting(loginFrame, headPanel, HomePage.funPanel, HomePage.settingPanel, user);
        } else if (e.getSource() == search) {
            HomePage.homeOpen.setVisible(true);
            HomePage.homeClose.setVisible(false);
            HomePage.createOpen.setVisible(true);
            HomePage.createClose.setVisible(false);
            HomePage.notificationOpen.setVisible(true);
            HomePage.notificationClose.setVisible(false);
            HomePage.settingOpen.setVisible(true);
            HomePage.settingClose.setVisible(false);
            HomePage.searchOpen.setVisible(false);
            HomePage.serachClose.setVisible(true);

            HomePage.searchPanel.removeAll();
            HomePage.mainPanel.setVisible(false);
            HomePage.homePanel.setVisible(false);
            HomePage.searchPanel.setVisible(true);
            HomePage.createPanel.setVisible(false);
            HomePage.notificationPanel.setVisible(false);
            HomePage.settingPanel.setVisible(false);
            new Search(loginFrame, headPanel, HomePage.searchPanel, user);
        }
    }

}
