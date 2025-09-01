package Functionalities;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import DS.LL;
import DS.getLikeLL;
import DS.getSavedLL;
import Login.*;

public class Setting implements ActionListener {

    User user;
    Connection con;
    JFrame logingFrame;
    JPanel headPanel;
    JPanel oldPanel;
    JPanel settingPanel;
    JPanel funPanel;
    JPanel profilePanel;
    JPanel savedPanel;
    JScrollPane savedBar;
    JButton profile;
    JButton saved;
    JButton back;
    JButton logout;
    JLabel profileOpen;
    JLabel profileClose;
    JLabel savedOpen;
    JLabel savedClose;
    JLabel logout_Label;
    JLabel back_Label;
    JLabel name_label;
    JLabel pass_label;
    JLabel phno_label;
    JLabel email_label;
    JLabel age_label;
    JLabel dc_label;
    JTextField name;
    JPasswordField pass;
    JTextField phno;
    JTextField email;
    JTextArea bio;
    JLabel bio_Label;
    JRadioButton male;
    JRadioButton female;
    JButton save;
    JButton cancle;
    JButton edit;
    JDateChooser dc;
    String user_gender;

    public Setting(JFrame of, JPanel mainPanel, JPanel oPanel, JPanel sPanel, User user) {
        con = ConnectionClass.getConnection();
        logingFrame = of;
        headPanel = mainPanel;
        oldPanel = oPanel;
        settingPanel = sPanel;
        this.user = user;

        funPanel = new JPanel(null);
        funPanel.setBounds(0, 0, 100, 768);
        // funPanel.setBackground(new Color(0x424242));
        funPanel.setVisible(true);
        funPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(0x343434)));

        profilePanel = new JPanel(null);
        profilePanel.setBounds(100, 0, 1266, 768);
        profilePanel.setVisible(false);

        savedPanel = new JPanel();
        savedPanel.setLayout(new BoxLayout(savedPanel, BoxLayout.Y_AXIS));
        savedPanel.setBounds(100, 0, 1266, 768);
        savedPanel.setVisible(true);

        savedBar = new JScrollPane();
        savedBar.setBounds(0, 0, 1266, 768);
        savedBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        savedBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        savedBar.setVisible(false);
        savedBar.setViewportView(savedPanel);

        profileOpen = new JLabel();
        ImageIcon pOpen = new ImageIcon("user(1).png");
        profileOpen.setIcon(pOpen);
        profileOpen.setBounds(0, 0, 50, 50);
        profileOpen.setVisible(true);

        profileClose = new JLabel();
        ImageIcon pClose = new ImageIcon("user.png");
        profileClose.setIcon(pClose);
        profileClose.setBounds(0, 0, 50, 50);
        profileClose.setVisible(false);

        savedOpen = new JLabel();
        ImageIcon sOpen = new ImageIcon("save.png");
        savedOpen.setIcon(sOpen);
        savedOpen.setBounds(0, 0, 50, 50);
        savedOpen.setVisible(true);

        savedClose = new JLabel();
        ImageIcon sClose = new ImageIcon("save(1).png");
        savedClose.setIcon(sClose);
        savedClose.setBounds(0, 0, 50, 50);
        savedClose.setVisible(false);

        back_Label = new JLabel();
        ImageIcon backIcon = new ImageIcon("previous.png");
        back_Label.setIcon(backIcon);
        back_Label.setBounds(0, 0, 50, 50);
        back_Label.setVisible(true);

        profile = new JButton();
        profile.setBorder(null);
        profile.setBounds(25, 200, 50, 50);
        profile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profile.setFocusable(false);
        profile.setOpaque(false);
        profile.setContentAreaFilled(false);
        profile.setBorderPainted(false);
        profile.addActionListener(this);
        profile.add(profileOpen);
        profile.add(profileClose);

        saved = new JButton();
        saved.setBorder(null);
        saved.setBounds(25, 300, 50, 50);
        saved.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saved.setFocusable(false);
        saved.setOpaque(false);
        saved.setContentAreaFilled(false);
        saved.setBorderPainted(false);
        saved.addActionListener(this);
        saved.add(savedOpen);
        saved.add(savedClose);

        back = new JButton();
        back.setBorder(null);
        back.setBounds(25, 400, 50, 50);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setFocusable(false);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.addActionListener(this);
        back.add(back_Label);

        ImageIcon logoutIcon = new ImageIcon("logout.png");
        logout_Label = new JLabel(logoutIcon);
        logout_Label.setBounds(0, 0, 75, 75);
        logout_Label.setFont(new Font("Poppins", Font.PLAIN, 18));
        logout = new JButton();
        logout.setBounds(10, 500, 75, 75);
        logout.setForeground(Color.BLACK);
        logout.setOpaque(false);
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.add(logout_Label);
        logout.addActionListener(this);

        funPanel.add(profile);
        funPanel.add(saved);
        funPanel.add(back);
        funPanel.add(logout);

        JLabel mainLabel = new JLabel();
        ImageIcon mainIcon = new ImageIcon("bg(1).png");
        mainLabel.setIcon(mainIcon);
        mainLabel.setBounds(0, 0, 1376, 778);

        email_label = new JLabel("Email");
        email_label.setBounds(330, 108, 150, 50);
        email_label.setForeground(Color.BLACK);
        email_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        email = new JTextField();
        email.setBounds(480, 108, 350, 50);
        email.setForeground(Color.BLACK);
        email.setOpaque(false);
        email.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        email.setText(user.getEmail());
        email.setEditable(false);
        email.setFont(new Font("Poppins", Font.PLAIN, 18));
        email.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        name_label = new JLabel("Username");
        name_label.setBounds(330, 178, 150, 50);
        name_label.setForeground(Color.BLACK);
        name_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        name = new JTextField();
        name.setBounds(480, 178, 350, 50);
        name.setForeground(Color.BLACK);
        name.setOpaque(false);
        name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        name.setText(user.getName());
        name.setEditable(false);
        name.setFont(new Font("Poppins", Font.PLAIN, 18));
        name.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        phno_label = new JLabel("Mobile No.");
        phno_label.setBounds(330, 248, 150, 50);
        phno_label.setForeground(Color.BLACK);
        phno_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        phno = new JTextField();
        phno.setBounds(480, 248, 350, 50);
        phno.setForeground(Color.BLACK);
        phno.setOpaque(false);
        phno.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        phno.setFont(new Font("Poppins", Font.PLAIN, 18));
        phno.setText(user.getPhno());
        phno.setEditable(false);
        phno.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        pass_label = new JLabel("Password");
        pass_label.setBounds(330, 318, 150, 50);
        pass_label.setForeground(Color.BLACK);
        pass_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        pass = new JPasswordField();
        pass.setBounds(480, 318, 350, 50);
        pass.setForeground(Color.BLACK);
        pass.setOpaque(false);
        pass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        pass.setText(user.getPass());
        pass.setEditable(false);
        pass.setFont(new Font("Poppins", Font.PLAIN, 18));
        pass.setEchoChar('*');
        pass.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        age_label = new JLabel("Gender");
        age_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        age_label.setBounds(330, 380, 150, 50);
        age_label.setForeground(Color.BLACK);
        male = new JRadioButton("male");
        male.setBounds(480, 380, 100, 50);
        male.setOpaque(false);
        male.setFont(new Font("Poppins", Font.PLAIN, 18));
        male.setForeground(Color.BLACK);
        male.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        male.setFocusable(false);
        female = new JRadioButton("female");
        female.setBounds(600, 380, 100, 50);
        female.setOpaque(false);
        female.setFont(new Font("Poppins", Font.PLAIN, 18));
        female.setForeground(Color.BLACK);
        female.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        female.setFocusable(false);
        if (user.getGender().equalsIgnoreCase("Male")) {
            male.setSelected(true);
            female.setSelected(false);
            female.setEnabled(false);
        } else if (user.getGender().equalsIgnoreCase("Female")) {
            male.setSelected(false);
            female.setSelected(true);
            male.setEnabled(false);
        }
        ButtonGroup gender = new ButtonGroup();
        gender.add(male);
        gender.add(female);

        ActionListener gen = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == male) {
                    user_gender = male.getText();
                } else if (e.getSource() == female) {
                    user_gender = female.getText();
                }
            }
        };
        female.addActionListener(gen);
        male.addActionListener(gen);

        dc_label = new JLabel("Date of Birth");
        dc_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        dc_label.setBounds(330, 440, 150, 50);
        dc_label.setForeground(Color.BLACK);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -15);
        dc = new JDateChooser(user.getDob());
        dc.setBounds(480, 440, 300, 50);
        dc.getJCalendar().setPreferredSize(new Dimension(300, 200));
        dc.setMaxSelectableDate(c.getTime());
        dc.setFont(new Font("Poppins", Font.PLAIN, 15));
        dc.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        dc.setEnabled(false);

        bio_Label = new JLabel("Bio");
        bio_Label.setBounds(330, 510, 150, 50);
        bio_Label.setForeground(Color.BLACK);
        bio = new JTextArea();
        bio.setText(user.getBio());
        bio.setBounds(480, 510, 350, 50);
        bio.setForeground(Color.BLACK);
        bio.setOpaque(false);
        bio.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        bio_Label.setFont(new Font("Poppins", Font.PLAIN, 18));
        bio.setFont(new Font("Poppins", Font.PLAIN, 18));
        bio.setEditable(false);
        bio.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        ImageIcon showImage = new ImageIcon("show.png");
        JLabel showLabel = new JLabel(showImage);
        showLabel.setBounds(0, 0, 32, 32);
        JButton show = new JButton();
        show.add(showLabel);
        show.setBounds(310, 10, 32, 32);
        show.setOpaque(false);
        show.setBorder(null);
        show.setContentAreaFilled(false);
        show.setVisible(true);
        show.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ImageIcon hideImage = new ImageIcon("hide.png");
        JLabel hideLabel = new JLabel(hideImage);
        hideLabel.setBounds(0, 0, 32, 32);
        JButton hide = new JButton();
        hide.add(hideLabel);
        hide.setBounds(310, 10, 32, 32);
        hide.setOpaque(false);
        hide.setBorder(null);
        hide.setContentAreaFilled(false);
        hide.setVisible(false);
        hide.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ActionListener showHide = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == show) {
                    show.setVisible(false);
                    hide.setVisible(true);
                    pass.setEchoChar((char) 0);
                } else if (e.getSource() == hide) {
                    show.setVisible(true);
                    hide.setVisible(false);
                    pass.setEchoChar('*');
                }
            }
        };

        show.addActionListener(this);
        hide.addActionListener(this);
        pass.add(show);
        pass.add(hide);

        edit = new JButton("edit");
        edit.setBorder(null);
        edit.setBounds(550, 620, 100, 50);
        edit.setForeground(Color.BLACK);
        edit.setFont(new Font("Poppins", Font.BOLD, 18));
        edit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        edit.setFocusable(false);
        edit.setOpaque(false);
        edit.setContentAreaFilled(false);
        edit.setBorderPainted(false);
        edit.addActionListener(this);
        edit.setVisible(true);

        save = new JButton("save");
        save.setBorder(null);
        save.setBounds(630, 620, 100, 50);
        save.setForeground(Color.BLACK);
        save.setFont(new Font("Poppins", Font.BOLD, 18));
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        save.setFocusable(false);
        save.setOpaque(false);
        save.setContentAreaFilled(false);
        save.setBorderPainted(false);
        save.addActionListener(this);
        save.setVisible(false);

        cancle = new JButton("cancle");
        cancle.setBorder(null);
        cancle.setBounds(480, 620, 100, 50);
        cancle.setForeground(Color.BLACK);
        cancle.setFont(new Font("Poppins", Font.BOLD, 18));
        cancle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancle.setFocusable(false);
        cancle.setOpaque(false);
        cancle.setContentAreaFilled(false);
        cancle.setBorderPainted(false);
        cancle.addActionListener(this);
        cancle.setVisible(false);

        profilePanel.add(name_label);
        profilePanel.add(name);
        profilePanel.add(pass_label);
        profilePanel.add(pass);
        profilePanel.add(phno_label);
        profilePanel.add(phno);
        profilePanel.add(email_label);
        profilePanel.add(email);
        profilePanel.add(age_label);
        profilePanel.add(male);
        profilePanel.add(female);
        profilePanel.add(dc_label);
        profilePanel.add(dc);
        profilePanel.add(bio_Label);
        profilePanel.add(bio);
        profilePanel.add(edit);
        profilePanel.add(save);
        profilePanel.add(cancle);

        settingPanel.add(profilePanel);
        settingPanel.add(savedBar);
        headPanel.add(funPanel);
    }

    Statement st;
    String query;
    PreparedStatement pst;
    ResultSet rs;
    int r;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == profile) {
            profileOpen.setVisible(false);
            profileClose.setVisible(true);
            savedOpen.setVisible(true);
            savedClose.setVisible(false);
            profilePanel.setVisible(true);
            savedBar.setVisible(false);
        } else if (e.getSource() == saved) {
            profileOpen.setVisible(true);
            profileClose.setVisible(false);
            savedOpen.setVisible(false);
            savedClose.setVisible(true);
            savedBar.setVisible(true);
            profilePanel.setVisible(false);

            savedPanel.removeAll();
            savedPanel.revalidate();
            savedPanel.repaint();
            JPanel head = new JPanel();
            JLabel title = new JLabel("SAVED POSTS");
            title.setFont(new Font("Georgia", Font.BOLD, 50));
            head.add(title);
            savedPanel.add(head);
            savedPanel.add(Box.createRigidArea(new Dimension(0, 20)));

            try {
                query = "select count(*) from saved where username=?";
                pst = con.prepareStatement(query);
                pst.setString(1, user.getName());
                rs = pst.executeQuery();
                rs.next();
                if (rs.getInt(1) > 0) {
                    query = "select * from post where post_id in (select post_id from saved where username = ?) order by date desc";
                    pst = con.prepareStatement(query);
                    pst.setString(1, user.getName());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        String post_id = rs.getString("post_id");
                        JPanel postPanel = new JPanel();
                        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
                        postPanel.setBorder(
                                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
                        postPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                        postPanel.setMaximumSize(
                                new Dimension(savedPanel.getWidth() - 40, Integer.MAX_VALUE));

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
                        savedLabel.setVisible(true);
                        unSavedLabel.setVisible(false);

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

                        System.out.println(post_id);
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
                        unSavedLabel.setVisible(false);
                        fun.add(like);
                        fun.add(comment);
                        fun.add(save);

                        ActionListener funListener = new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    // query = "select CURRENT_TIMESTAMP()";
                                    // pst = con.prepareStatement(query);
                                    // rs = pst.executeQuery();
                                    // Timestamp date = rs.getTimestamp("CURRENT_TIMESTAMP()");
                                    if (e.getSource() == like) {
                                        if (unLikedLabel.isVisible()) {
                                            query = "insert into likes values(?,?)";
                                            pst = con.prepareStatement(query);
                                            pst.setString(1, post_id);
                                            pst.setString(2, user.getName());
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
                                        String com = JOptionPane.showInputDialog(null,
                                                "Enter Comment", "Comment",
                                                JOptionPane.OK_CANCEL_OPTION);
                                        query = "insert into comments values(?,?,?)";
                                        pst = con.prepareStatement(query);
                                        pst.setString(1, post_id);
                                        pst.setString(2, user.getName());
                                        pst.setString(3, com);
                                        // pst.setTimestamp(5, date);
                                        r = pst.executeUpdate();

                                    } else if (e.getSource() == save) {
                                        if (unSavedLabel.isVisible()) {
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
                        savedPanel.add(postPanel);
                        savedPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                    }
                    savedPanel.revalidate();
                    savedPanel.repaint();
                } else {
                    JPanel notFound = new JPanel();
                    notFound.setLayout(new FlowLayout(FlowLayout.CENTER));
                    JLabel noPosts = new JLabel("Not saved any posts yet");
                    noPosts.setFont(new Font("Poppins", Font.PLAIN, 24));
                    notFound.add(noPosts);
                    savedPanel.add(notFound);
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                // TODO: handle exception
            }
        } else if (e.getSource() == back) {
            profileOpen.setVisible(true);
            profileClose.setVisible(false);
            savedOpen.setVisible(true);
            savedClose.setVisible(false);
            oldPanel.setVisible(true);
            funPanel.setVisible(false);
            settingPanel.setVisible(false);
            profilePanel.setVisible(false);
            savedPanel.setVisible(false);
        } else if (e.getSource() == logout) {
            logingFrame.dispose();
            new LoginPage();
        } else if (e.getSource() == edit) {
            edit.setVisible(false);
            cancle.setVisible(true);
            save.setVisible(true);
            email.setEditable(true);
            name.setEditable(true);
            pass.setEditable(true);
            phno.setEditable(true);
            male.setEnabled(true);
            female.setEnabled(true);
            dc.setEnabled(true);
            bio.setEditable(true);

            male.setCursor(new Cursor(Cursor.HAND_CURSOR));
            female.setCursor(new Cursor(Cursor.HAND_CURSOR));
            dc.getJCalendar().setCursor(new Cursor(Cursor.HAND_CURSOR));
            dc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else if (e.getSource() == cancle) {
            edit.setVisible(true);
            cancle.setVisible(false);
            save.setVisible(false);
            email.setEditable(false);
            name.setEditable(false);
            pass.setEditable(false);
            phno.setEditable(false);
            male.setEnabled(false);
            female.setEnabled(false);
            dc.setEnabled(false);
            bio.setEditable(false);

            email.setText(user.getEmail());
            name.setText(user.getName());
            pass.setText(user.getPass());
            phno.setText(user.getPhno());
            bio.setText(user.getBio());
            if (user.getGender().equals("Male")) {
                male.setSelected(true);
                female.setSelected(false);
            } else if (user.getGender().equals("Female")) {
                male.setSelected(false);
                female.setSelected(true);
            }
            dc.setDate(user.getDob());
        } else if (e.getSource() == save) {
            String user_name = name.getText();
            String user_email = email.getText();
            String user_phno = phno.getText();
            String user_pass = pass.getText();
            java.sql.Date d = new java.sql.Date(dc.getDate().getTime());
            String user_bio = bio.getText();

            int c = JOptionPane.showConfirmDialog(null, "Do you want to save changes ?", "Save",
                    JOptionPane.YES_NO_OPTION);
            if (c == 0) {
                try {
                    query = "update user set username=?,email=?,password=?,phone_no=?,gender=?,dob=?,bio=? where email=?";
                    pst = con.prepareStatement(query);
                    pst.setString(1, user_name);
                    pst.setString(2, user_email);
                    pst.setString(3, user_pass);
                    pst.setString(4, user_phno);
                    pst.setString(5, user_gender);
                    pst.setDate(6, d);
                    pst.setString(7, user_bio);
                    pst.setString(8, user.getEmail());
                    r = pst.executeUpdate();
                    if (r > 0) {
                        JOptionPane.showMessageDialog(null, "Changes Saved", "Save",
                                JOptionPane.PLAIN_MESSAGE);
                        edit.setVisible(true);
                        email.setEditable(false);
                        name.setEditable(false);
                        phno.setEditable(false);
                        pass.setEditable(false);
                        cancle.setVisible(false);
                        save.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Changes doesn't Saved", "Save",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Save",
                            JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        }
    }
}
