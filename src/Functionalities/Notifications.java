package Functionalities;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import DS.*;
import Login.*;

public class Notifications implements ActionListener {

    Connection con;
    User user;
    JFrame loginFrame;
    JPanel headPanel;
    JPanel notificationPanel;
    JPanel userPanel;
    JPanel scrollPanel;
    static JButton home;
    static JButton create;
    static JButton search;
    static JButton notifications;
    static JButton setting;

    Statement st;
    String query;
    PreparedStatement pst;
    CallableStatement cst;
    ResultSet rs;
    int r;

    public Notifications(JFrame of, JPanel mainPanel, JPanel nPanel, User user) {
        con = ConnectionClass.getConnection();
        loginFrame = of;
        headPanel = mainPanel;
        notificationPanel = nPanel;
        notificationPanel.setVisible(true);
        this.user = user;

        home = HomePage.getHome();
        create = HomePage.getCreate();
        search = HomePage.getSearch();
        notifications = HomePage.getNotifications();
        setting = HomePage.getSetting();

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

        JPanel head = new JPanel();
        JLabel title = new JLabel("Notifications");
        title.setFont(new Font("Georgia", Font.BOLD, 50));
        head.add(title);
        scrollPanel.add(head);
        scrollPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        try {
            query = "select * from notifications where user_gets=? order by date desc";
            pst = con.prepareStatement(query);
            pst.setString(1, user.getName());
            rs = pst.executeQuery();

            while (rs.next()) {
                String send = rs.getString("user_sends");
                JPanel postPanel = new JPanel();
                postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
                postPanel.setBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
                // postPanel.setBackground(Color.WHITE);
                postPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                postPanel.setMaximumSize(
                        new Dimension(scrollPanel.getWidth() - 40, Integer.MAX_VALUE));

                if (rs.getString("type").equals("like")) {
                    query = "select * from likes where username=?";
                    pst = con.prepareStatement(query);
                    pst.setString(1, send);
                    rs = pst.executeQuery();
                    rs.next();

                    String post_id = rs.getString("post_id");
                    JLabel likedLable = new JLabel(send + " liked your post");
                    likedLable.setFont(new Font("Arial", Font.BOLD, 22));
                    likedLable.setAlignmentX(Component.LEFT_ALIGNMENT);
                    postPanel.add(likedLable);
                    postPanel.add(Box.createRigidArea(new Dimension(0, 5)));

                    query = "select * from post where post_id=?";
                    pst = con.prepareStatement(query);
                    pst.setString(1, post_id);
                    rs = pst.executeQuery();
                    rs.next();

                    JLabel usernameLabel = new JLabel(rs.getString("username"));
                    usernameLabel.setFont(new Font("Arial", Font.PLAIN, 22));
                    usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    postPanel.add(usernameLabel);
                    postPanel.add(Box.createRigidArea(new Dimension(0, 5)));

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

                    scrollPanel.add(postPanel);
                } else if (rs.getString("type").equals("comment")) {
                    query = "select * from comments where username = ?";
                    pst = con.prepareStatement(query);
                    pst.setString(1, send);
                    rs = pst.executeQuery();
                    rs.next();

                    JLabel commLable = new JLabel(send + " commented your post : " + rs.getString("comment"));
                    commLable.setFont(new Font("Arial", Font.BOLD, 22));
                    commLable.setAlignmentX(Component.LEFT_ALIGNMENT);
                    postPanel.add(commLable);
                    postPanel.add(Box.createRigidArea(new Dimension(0, 5)));

                    JLabel usernameLabel = new JLabel(send);
                    usernameLabel.setFont(new Font("Arial", Font.PLAIN, 22));
                    usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    postPanel.add(usernameLabel);
                    postPanel.add(Box.createRigidArea(new Dimension(0, 5)));

                    String post_id = rs.getString("post_id");
                    query = "select * from post where post_id=?";
                    pst = con.prepareStatement(query);
                    pst.setString(1, post_id);
                    rs = pst.executeQuery();
                    rs.next();

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

                    scrollPanel.add(postPanel);
                } else if (rs.getString("type").equals("follow")) {
                    JLabel followLable = new JLabel(send + " started following you");
                    followLable.setFont(new Font("Arial", Font.PLAIN, 25));
                    postPanel.add(followLable);
                    postPanel.add(Box.createRigidArea(new Dimension(0, 5)));
                }
                scrollPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                scrollPanel.add(postPanel);
            }
            scrollPanel.revalidate();
            scrollPanel.repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            e.getStackTrace();
        }

        notificationPanel.add(scrollBar);
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

            HomePage.homePanel.removeAll();
            HomePage.mainPanel.setVisible(false);
            HomePage.homePanel.setVisible(true);
            HomePage.searchPanel.setVisible(false);
            HomePage.createPanel.setVisible(false);
            HomePage.notificationPanel.setVisible(false);
            HomePage.settingPanel.setVisible(false);

            new Home(loginFrame, headPanel, HomePage.homePanel, user);
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

            notificationPanel.removeAll();
            HomePage.mainPanel.setVisible(false);
            HomePage.homePanel.setVisible(false);
            HomePage.searchPanel.setVisible(false);
            HomePage.createPanel.setVisible(false);
            HomePage.notificationPanel.setVisible(true);
            HomePage.settingPanel.setVisible(false);
            new Notifications(loginFrame, headPanel, notificationPanel, user);
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
