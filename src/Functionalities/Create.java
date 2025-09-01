package Functionalities;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Login.*;

public class Create implements ActionListener {

    Connection con;
    JFrame loginFrame;
    JPanel headPanel;
    JPanel createPanel;
    static JButton home;
    static JButton create;
    static JButton search;
    static JButton notifications;
    static JButton setting;
    JButton fileChooser;
    JButton close;
    JButton cancle;
    JButton post;
    JTextArea text;
    JLabel fileLabel1;
    JLabel fileLabel2;
    JLabel buttonLabel;
    User user;

    public Create(JFrame of, JPanel mainPanel, JPanel cPanel, User user) {
        con = ConnectionClass.getConnection();
        loginFrame = of;
        headPanel = mainPanel;
        createPanel = cPanel;
        this.user = user;

        home = HomePage.getHome();
        create = HomePage.getCreate();
        search = HomePage.getSearch();
        notifications = HomePage.getNotifications();
        setting = HomePage.getSetting();

        JLabel textLable = new JLabel("Enter Text");
        textLable.setBounds(400, 80, 100, 50);
        textLable.setFont(new Font("Poppins", Font.PLAIN, 18));
        textLable.setForeground(Color.BLACK);
        text = new JTextArea();
        text.setBounds(520, 80, 350, 150);
        text.setOpaque(false);
        text.setBorder(BorderFactory.createLineBorder(new Color(0x343434)));
        text.setVisible(true);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Poppins", Font.PLAIN, 18));
        text.setLayout(new FlowLayout());
        JScrollPane textScrollPane = new JScrollPane(text);
        // JScrollBar v = new JScrollBar(JScrollBar.VERTICAL);
        // v.setBounds(130, 0, 20, 150);
        // JScrollBar h = new JScrollBar(JScrollBar.HORIZONTAL);
        // h.setBounds(0, 320, 150, 20);
        // text.add(v);
        // text.add(h);

        JPanel addPanel = new JPanel(null);
        addPanel.setVisible(true);
        addPanel.setBounds(400, 300, 470, 300);
        // addPanel.setBackground(new Color(0x424242));
        addPanel.setBorder(BorderFactory.createDashedBorder(new Color(0x424242)));

        ImageIcon fileIcon1 = new ImageIcon("image-upload.png");
        fileLabel1 = new JLabel(fileIcon1);
        fileLabel1.setBounds(0, 0, 128, 128);
        fileLabel1.setVisible(true);

        ImageIcon fileIcon2 = new ImageIcon("photo(1).png");
        fileLabel2 = new JLabel(fileIcon2);
        fileLabel2.setBounds(0, 0, 128, 128);
        fileLabel2.setVisible(false);

        fileChooser = new JButton();
        fileChooser.setBorder(null);
        fileChooser.setBounds(175, 50, 128, 128);
        fileChooser.setBackground(new Color(0x1a2342));
        fileChooser.setForeground(Color.WHITE);
        fileChooser.setFont(new Font("Poppins", Font.PLAIN, 18));
        fileChooser.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fileChooser.setFocusable(false);
        fileChooser.setOpaque(false);
        fileChooser.setContentAreaFilled(false);
        fileChooser.setBorderPainted(false);
        fileChooser.addActionListener(this);
        fileChooser.add(fileLabel1);
        fileChooser.add(fileLabel2);

        close = new JButton(new ImageIcon("cross.png"));
        close.setBorder(null);
        close.setBounds(390, -5, 100, 70);
        close.setBackground(new Color(0x1a2342));
        close.setForeground(Color.BLACK);
        close.setFont(new Font("Poppins", Font.BOLD, 18));
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));
        close.setFocusable(false);
        close.setOpaque(false);
        close.setContentAreaFilled(false);
        close.setBorderPainted(false);
        close.setVisible(false);
        close.addActionListener(this);

        buttonLabel = new JLabel("choose image to upload");
        buttonLabel.setBounds(130, 210, 200, 50);
        buttonLabel.setForeground(Color.BLACK);
        buttonLabel.setFont(new Font("Poppins", Font.PLAIN, 18));
        buttonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonLabel.setVisible(true);

        cancle = new JButton("cancle");
        cancle.setBorder(null);
        cancle.setBounds(450, 650, 100, 50);
        cancle.setBackground(new Color(0x1a2342));
        cancle.setForeground(Color.BLACK);
        cancle.setFont(new Font("Poppins", Font.BOLD, 18));
        cancle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancle.setFocusable(false);
        cancle.setOpaque(false);
        cancle.setContentAreaFilled(false);
        cancle.setBorderPainted(false);
        cancle.addActionListener(this);

        post = new JButton("post");
        post.setBorder(null);
        post.setBounds(625, 650, 100, 50);
        post.setBackground(new Color(0x1a2342));
        post.setForeground(Color.BLACK);
        post.setFont(new Font("Poppins", Font.BOLD, 18));
        post.setCursor(new Cursor(Cursor.HAND_CURSOR));
        post.setFocusable(false);
        post.setOpaque(false);
        post.setContentAreaFilled(false);
        post.setBorderPainted(false);
        post.addActionListener(this);

        addPanel.add(fileChooser);
        addPanel.add(buttonLabel);
        addPanel.add(close);
        createPanel.add(textLable);
        createPanel.add(text);
        // createPanel.add(textScrollPane);
        createPanel.add(addPanel);
        createPanel.add(cancle);
        createPanel.add(post);

    }

    Statement st;
    String query;
    PreparedStatement pst;
    ResultSet rs;
    int r;
    String file_path = "default.png";
    FileInputStream fis;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fileChooser) {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("C:\\Users\\shail\\OneDrive\\Desktop"));
            int response = fc.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                file_path = fc.getSelectedFile().getAbsolutePath();
            }
            if (!file_path.equals("default.png")) {
                fileLabel2.setVisible(true);
                fileLabel1.setVisible(false);
                close.setVisible(true);
                buttonLabel.setVisible(false);
                fileChooser.setBounds(175, 75, 128, 128);
            } else {
                close.setVisible(false);
                fileLabel2.setVisible(false);
                fileLabel1.setVisible(true);
                buttonLabel.setVisible(true);
                fileChooser.setBounds(175, 50, 128, 128);
            }
        } else if (e.getSource() == close) {
            file_path = "default.png";
            close.setVisible(false);
            fileLabel2.setVisible(false);
            fileLabel1.setVisible(true);
            buttonLabel.setVisible(true);
            fileChooser.setBounds(175, 50, 128, 128);
        } else if (e.getSource() == post) {
            String content = text.getText();
            String user_name = user.name;
            String id = user_name + "_01";
            try {
                query = "select count(*) from post where username = ?";
                pst = con.prepareStatement(query);
                pst.setString(1, user_name);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count < 10) {
                        id = user_name + "_0" + (count + 1);
                    } else {
                        id = user_name + "_" + (count + 1);
                    }
                } else {
                    id = user_name + "_01";
                }

                query = "select current_date()";
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                rs.next();
                java.sql.Date d = rs.getDate(1);

                query = "insert into post values(?,?,?,?,?,?) ";
                pst = con.prepareStatement(query);
                pst.setString(1, id);
                pst.setString(2, user_name);
                pst.setString(3, content);
                pst.setDate(6, d);
                try {
                    File f = new File(file_path);
                    FileInputStream fis = new FileInputStream(f);
                    pst.setString(4, f.getName());
                    pst.setBlob(5, fis);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Upload",
                            JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
                r = pst.executeUpdate();
                if (r > 0) {
                    JOptionPane.showMessageDialog(null, "Posted Successfully", "Upload",
                            JOptionPane.PLAIN_MESSAGE);
                    file_path = "default.png";
                    close.setVisible(false);
                    fileLabel2.setVisible(false);
                    fileLabel1.setVisible(true);
                    buttonLabel.setVisible(true);
                    fileChooser.setBounds(175, 50, 128, 128);
                } else {
                    JOptionPane.showMessageDialog(null, "Posting Unsuccessfull", "Upload",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Upload",
                        JOptionPane.ERROR_MESSAGE);
                file_path = "default.png";
                close.setVisible(false);
                fileLabel2.setVisible(false);
                fileLabel1.setVisible(true);
                buttonLabel.setVisible(true);
                fileChooser.setBounds(175, 50, 128, 128);
                // e1.printStackTrace();
            }
        } else if (e.getSource() == cancle) {
            createPanel.setVisible(false);
            HomePage.homePanel.setVisible(true);
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
