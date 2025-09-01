package Functionalities;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.*;
import Login.*;
import DS.*;

public class Search implements ActionListener {
    Connection con;
    JFrame loginFrame;
    JPanel headPanel;
    JPanel searchPanel;
    JPanel userPanel;
    static JButton home;
    static JButton create;
    static JButton search;
    static JButton notifications;
    static JButton setting;
    JTextField name;
    JButton userButton;
    JButton serach_button;
    JButton follow;
    JButton follower;
    JButton following;
    String username = "";
    Statement st;
    String query;
    PreparedStatement pst;
    CallableStatement cst;
    ResultSet rs;
    int r;
    User user;

    public Search(JFrame of, JPanel mainPanel, JPanel sPanel, User user) {
        con = ConnectionClass.getConnection();
        loginFrame = of;
        headPanel = mainPanel;
        searchPanel = sPanel;
        searchPanel.setVisible(true);
        this.user = user;

        home = HomePage.getHome();
        create = HomePage.getCreate();
        search = HomePage.getSearch();
        notifications = HomePage.getNotifications();
        setting = HomePage.getSetting();

        userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBounds(0, 100, 1216, 768);
        userPanel.setVisible(true);

        JScrollPane userPane = new JScrollPane();
        userPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        userPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        userPane.setBounds(0, 0, 1266, 748);
        userPane.setVisible(false);
        userPane.setViewportView(userPanel);

        ImageIcon icon = new ImageIcon("loupe.png");
        JLabel name_label = new JLabel(icon);
        name_label.setBounds(350, 30, 32, 32);

        name = new JTextField();
        name.setBounds(410, 20, 400, 50);
        name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        name.setText("Enter Username");
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Poppins", Font.PLAIN, 18));
        name.setOpaque(false);
        name.setVisible(true);
        name.selectAll();

        ImageIcon backIcon = new ImageIcon("back.png");
        JButton back_button = new JButton(backIcon);
        back_button.setFont(new Font("Poppins", Font.PLAIN, 18));
        back_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_button.setFocusable(false);
        back_button.setOpaque(false);
        back_button.setContentAreaFilled(false);
        back_button.setBorderPainted(false);
        back_button.setVisible(true);

        // JPanel notFound = new JPanel();
        JLabel notFound = new JLabel("Username not found");
        notFound.setBounds(0, 100, 1266, 758);
        notFound.setFont(new Font("Poppins", Font.PLAIN, 50));
        notFound.setLayout(new FlowLayout(FlowLayout.CENTER));
        notFound.setForeground(Color.GRAY);
        notFound.setVisible(false);
        // notFound.add(notFoundLabel);

        follow = new JButton();
        follow.setFont(new Font("Poppins", Font.PLAIN, 24));
        follow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        follow.setFocusable(false);
        follow.setOpaque(false);
        follow.setContentAreaFilled(false);
        follow.setBorderPainted(false);
        follow.setVisible(true);
        follow.addActionListener(this);

        serach_button = new JButton("search");
        serach_button.setBounds(860, 20, 100, 50);
        serach_button.setFont(new Font("Poppins", Font.PLAIN, 18));
        serach_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        serach_button.setFocusable(false);
        serach_button.setOpaque(false);
        serach_button.setContentAreaFilled(false);
        serach_button.setBorderPainted(false);
        serach_button.setVisible(true);

        JPanel searchedUserPanel = new JPanel();
        searchedUserPanel.setLayout(new BoxLayout(searchedUserPanel, BoxLayout.Y_AXIS));
        searchedUserPanel.setBounds(410, 72, 400, Integer.MAX_VALUE);
        searchedUserPanel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));

        serach_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == serach_button) {
                    searchedUserPanel.removeAll();
                    userPanel.removeAll();
                    userPanel.revalidate();
                    userPanel.repaint();
                    notFound.setVisible(false);
                    userPane.setVisible(false);
                    name_label.setVisible(true);
                    name.setVisible(true);
                    serach_button.setVisible(true);
                    try {

                        query = "select * from user where username like '%" + name.getText() + "%'";
                        pst = con.prepareStatement(query);
                        rs = pst.executeQuery();
                        boolean flag = true;

                        while (rs.next()) {
                            flag = false;
                            String userName = rs.getString("username");
                            String bio = rs.getString("bio");
                            String file_name = rs.getString("profilePicture_name");
                            Blob Pb = rs.getBlob("profile_picture");

                            userButton = new JButton(userName);
                            userButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
                            userButton.setFont(new Font("Poppins", Font.PLAIN, 18));
                            userButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                            userButton.setFocusable(false);
                            userButton.setOpaque(false);
                            // userButton.setBackground(Color.GRAY);
                            userButton.setContentAreaFilled(false);
                            // userButton.setBorderPainted(false);
                            userButton.setVisible(true);
                            userButton.setPreferredSize(new Dimension(searchedUserPanel.getWidth(), 50));
                            userButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                            searchedUserPanel.add(userButton);
                            searchedUserPanel.add(Box.createRigidArea(new Dimension(0, 3)));
                            // username = name.getText();
                            if (userButton.getText().equals(User.getName())) {
                                follow.setVisible(false);
                            } else {
                                follow.setVisible(true);
                            }
                            getFollowingLL l = new getFollowingLL(userName);
                            LL followingL = l.getLL();
                            if (followingL.contains(user.getName())) {
                                follow.setText("following");
                            } else {
                                follow.setText("follow");
                            }

                            searchPanel.revalidate();
                            searchPanel.repaint();
                            ActionListener al = new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (e.getSource() == userButton) {
                                        System.out.println(userButton.getText());
                                        userPanel.removeAll();
                                        userPanel.revalidate();
                                        userPanel.repaint();
                                        userPane.setVisible(true);
                                        userButton.setVisible(false);
                                        name.setVisible(false);
                                        name_label.setVisible(false);
                                        serach_button.setVisible(false);
                                        try {
                                            // back_button.setVisible(true);
                                            userPanel.add(back_button);
                                            userPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                                            back_button.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == back_button) {
                                                        // name.setText(name.getText());
                                                        // name.setText("");
                                                        // userPane.setVisible(false);
                                                        // userButton.setVisible(true);
                                                        // name.setVisible(true);
                                                        // name_label.setVisible(true);
                                                        // serach_button.setVisible(true);
                                                        searchPanel.removeAll();
                                                        searchPanel.setVisible(false);
                                                        new Search(loginFrame, headPanel, searchPanel, user);
                                                    }
                                                }
                                            });
                                            username = userName;

                                            JPanel profilePanel = new JPanel();
                                            profilePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                                            profilePanel.setMaximumSize(
                                                    new Dimension(userPane.getWidth() - 40,
                                                            50));
                                            File Pf = new File(
                                                    "C:\\Users\\shail\\OneDrive\\Desktop\\Group Project\\Downloads\\"
                                                            + file_name);
                                            // BufferedImage bi = ImageIO.read(f);
                                            byte[] Parr = Pb.getBytes(1, (int) Pb.length());
                                            FileOutputStream Pfos = new FileOutputStream(Pf);
                                            Pfos.write(Parr);
                                            Pfos.close();
                                            ImageIcon Picon = new ImageIcon(Pf.getAbsolutePath());
                                            Image Pi = Picon.getImage().getScaledInstance(32, 32,
                                                    Image.SCALE_SMOOTH);
                                            JLabel Pimage = new JLabel(new ImageIcon(Pi));
                                            // JLabel image = new JLabel(icon);
                                            profilePanel.add(Pimage);

                                            JLabel name_Label = new JLabel(userName);
                                            name_Label.setFont(new Font("Poppins", Font.BOLD, 50));
                                            name_Label.setForeground(Color.BLACK);
                                            profilePanel.add(name_Label);

                                            profilePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                                            userPanel.add(profilePanel);
                                            userPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                                            JLabel bio_label = new JLabel();
                                            bio_label.setText(bio);
                                            bio_label.setFont(new Font("Poppins", Font.PLAIN, 20));
                                            bio_label.setForeground(Color.BLACK);
                                            userPanel.add(bio_label);
                                            bio_label.setAlignmentX(Component.CENTER_ALIGNMENT);
                                            userPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                                            JPanel followPanel = new JPanel();
                                            followPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                                            followPanel.setMaximumSize(
                                                    new Dimension(userPane.getWidth() - 40,
                                                            Integer.MAX_VALUE));

                                            getFollowingLL l1 = new getFollowingLL(username);
                                            LL ll = l1.getLL();
                                            following = new JButton(ll.getCount() + " following");
                                            following.setFont(new Font("Poppins", Font.PLAIN, 24));
                                            following.setCursor(new Cursor(Cursor.HAND_CURSOR));
                                            following.setFocusable(false);
                                            following.setOpaque(false);
                                            following.setContentAreaFilled(false);
                                            following.setBorderPainted(false);
                                            following.setVisible(true);
                                            ll.deleteAll();

                                            getFollowerLL l = new getFollowerLL(username);
                                            ll = l.getLL();
                                            follower = new JButton(ll.getCount() + " followers");
                                            follower.setFont(new Font("Poppins", Font.PLAIN, 24));
                                            follower.setCursor(new Cursor(Cursor.HAND_CURSOR));
                                            follower.setFocusable(false);
                                            follower.setOpaque(false);
                                            follower.setContentAreaFilled(false);
                                            follower.setBorderPainted(false);
                                            follower.setVisible(true);
                                            ll.deleteAll();
                                            ActionListener al = new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == following) {
                                                        new Follow(loginFrame, headPanel, searchPanel, user, username,
                                                                "following");
                                                    } else if (e.getSource() == follower) {
                                                        new Follow(loginFrame, headPanel, searchPanel, user, username,
                                                                "followers");
                                                    }
                                                }
                                            };

                                            follower.addActionListener(al);
                                            following.addActionListener(al);
                                            followPanel.add(follow);
                                            followPanel.add(following);
                                            followPanel.add(follower);
                                            userPanel.add(followPanel);
                                            userPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                                            JLabel post_label = new JLabel("post");
                                            post_label.setFont(new Font("Poppins", Font.BOLD, 20));
                                            post_label.setForeground(Color.BLACK);
                                            post_label.setAlignmentX(Component.CENTER_ALIGNMENT);
                                            // userPanel.add(post_label);
                                            // userPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                                            query = "{call getPostCount(?,?)}";
                                            cst = con.prepareCall(query);
                                            cst.setString(1, userName);
                                            cst.execute();
                                            if (cst.getInt(2) > 0) {

                                                query = "select * from post where username=? order by date desc";
                                                pst = con.prepareStatement(query);
                                                pst.setString(1, userName);
                                                rs = pst.executeQuery();
                                                while (rs.next()) {
                                                    String post_id = rs.getString("post_id");
                                                    JPanel postPanel = new JPanel();
                                                    postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
                                                    postPanel.setBorder(
                                                            BorderFactory.createMatteBorder(0, 0, 1, 0,
                                                                    Color.GRAY));
                                                    // postPanel.setBackground(Color.WHITE);
                                                    postPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                                                    postPanel.setMaximumSize(
                                                            new Dimension(userPane.getWidth() - 40,
                                                                    Integer.MAX_VALUE));

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

                                                    query = "{call getCommCount(?,?)}";
                                                    cst = con.prepareCall(query);
                                                    cst.setString(1, post_id);
                                                    cst.execute();
                                                    int comments = cst.getInt(2);

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

                                                    getLikeLL l2 = new getLikeLL(post_id);
                                                    LL liked_user = l2.getLL();
                                                    // like.setText("" + liked_user.getCount());
                                                    if (liked_user.contains(User.getName())) {
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
                                                    // comment.setText("" + comments);

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
                                                    if (saved_user.contains(User.getName())) {
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

                                                    unLikedLabel.setIconTextGap(5);
                                                    likedLabel.setIconTextGap(5);
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
                                                                    getLikeLL l = new getLikeLL(post_id);
                                                                    int likes = liked_user.getCount();
                                                                    if (unLikedLabel.isVisible()) {
                                                                        query = "insert into likes values(?,?,?)";
                                                                        pst = con.prepareStatement(query);
                                                                        pst.setString(1, post_id);
                                                                        pst.setString(2, User.getName());
                                                                        pst.setDate(3, date);
                                                                        // pst.setTimestamp(5, date);
                                                                        r = pst.executeUpdate();

                                                                        // likedLabel.setText("" + (likes++));
                                                                        likedLabel.setIconTextGap(5);
                                                                        likedLabel.setVisible(true);
                                                                        unLikedLabel.setVisible(false);
                                                                    } else if (likedLabel.isVisible()) {
                                                                        query = "{call UnlikePost(?,?)}";
                                                                        cst = con.prepareCall(query);
                                                                        cst.setString(1, post_id);
                                                                        cst.setString(2, user.getName());
                                                                        cst.execute();

                                                                        // unLikedLabel.setText("" + (likes--));
                                                                        unLikedLabel.setIconTextGap(5);
                                                                        likedLabel.setVisible(false);
                                                                        unLikedLabel.setVisible(true);
                                                                    }
                                                                } else if (e.getSource() == comment) {

                                                                    // String com =
                                                                    // JOptionPane.showInputDialog(null,
                                                                    // "Enter Comment", "Comment",
                                                                    // JOptionPane.OK_CANCEL_OPTION);
                                                                    // query = "insert into comments
                                                                    // values(?,?,?,?)";
                                                                    // pst = con.prepareStatement(query);
                                                                    // pst.setString(1, post_id);
                                                                    // pst.setString(2, User.getName());
                                                                    // pst.setString(3, com);
                                                                    // pst.setDate(4, date);
                                                                    // r = pst.executeUpdate();
                                                                    // searchPanel.setVisible(false);
                                                                    new Comment(loginFrame, headPanel, searchPanel,
                                                                            user, post_id);

                                                                } else if (e.getSource() == save) {
                                                                    if (unSavedLabel.isVisible()) {
                                                                        query = "insert into saved values(?,?)";
                                                                        pst = con.prepareStatement(query);
                                                                        pst.setString(1, post_id);
                                                                        pst.setString(2, User.getName());
                                                                        r = pst.executeUpdate();

                                                                        unSavedLabel.setVisible(false);
                                                                        savedLabel.setVisible(true);
                                                                    } else {
                                                                        query = "delete from saved where post_id=? and username=?";
                                                                        pst = con.prepareStatement(query);
                                                                        pst.setString(1, post_id);
                                                                        pst.setString(2, User.getName());
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
                                                    userPanel.add(postPanel);
                                                    userPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                                                }
                                                userPanel.revalidate();
                                                userPanel.repaint();
                                            } else {
                                                JPanel noPostPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                                JLabel noPost = new JLabel("No posts");
                                                noPost.setFont(new Font("Poppins", Font.PLAIN, 25));
                                                noPostPanel.add(noPost);
                                                noPostPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                                                userPanel.add(noPostPanel);
                                            }
                                        } catch (Exception e1) {
                                            JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR",
                                                    JOptionPane.ERROR_MESSAGE);
                                            e1.printStackTrace();
                                        }
                                    }
                                }
                            };

                            userButton.addActionListener(al);
                        }
                        searchedUserPanel.revalidate();
                        searchedUserPanel.repaint();
                        searchPanel.add(searchedUserPanel);
                        if (flag) {
                            notFound.setVisible(true);
                            userPane.setVisible(false);
                            searchPanel.revalidate();
                            searchPanel.repaint();
                        }
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                }
            }
        });

        searchPanel.add(name_label);
        searchPanel.add(name);
        searchPanel.add(serach_button);
        searchPanel.add(notFound);
        searchPanel.add(userPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == follow) {
            try {
                Date d = new Date();
                java.sql.Date date = new java.sql.Date(d.getTime());
                if (follow.getText().equalsIgnoreCase("follow")) {
                    query = "insert into following values(?,?,?)";
                    pst = con.prepareStatement(query);
                    pst.setString(1, username);
                    pst.setString(2, User.getName());
                    pst.setDate(3, date);
                    r = pst.executeUpdate();
                    follow.setText("following");
                } else {
                    query = "delete from following where user_main=? and user_follows=?";
                    pst = con.prepareStatement(query);
                    pst.setString(1, username);
                    pst.setString(2, User.getName());
                    r = pst.executeUpdate();
                    follow.setText("follow");
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        } else if (e.getSource() == home) {
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

            searchPanel.removeAll();
            HomePage.mainPanel.setVisible(false);
            HomePage.homePanel.setVisible(false);
            searchPanel.setVisible(true);
            HomePage.createPanel.setVisible(false);
            HomePage.notificationPanel.setVisible(false);
            HomePage.settingPanel.setVisible(false);
            new Search(loginFrame, headPanel, searchPanel, user);
        }
    }
}
