package Login;

import Functionalities.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.management.Notification;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class HomePage implements ActionListener {
    Connection con;
    User user;

    JFrame loginFrame;
    JPanel headPanel;
    public static JPanel mainPanel;
    public static JPanel funPanel;
    public static JPanel homePanel;
    public static JPanel searchPanel;
    public static JPanel createPanel;
    public static JPanel notificationPanel;
    public static JPanel settingPanel;
    public static JLabel homeOpen;
    public static JLabel searchOpen;
    public static JLabel createOpen;
    public static JLabel notificationOpen;
    public static JLabel settingOpen;
    public static JLabel homeClose;
    public static JLabel serachClose;
    public static JLabel createClose;
    public static JLabel notificationClose;
    public static JLabel settingClose;
    static JButton home;
    static JButton search;
    static JButton create;
    static JButton notifications;
    static JButton setting;

    HomePage(JFrame of, JPanel op, User details) {
        con = ConnectionClass.getConnection();
        user = details;
        loginFrame = of;

        headPanel = new JPanel(null);
        headPanel.setBounds(0, 0, 1366, 768);
        headPanel.setVisible(true);

        mainPanel = new JPanel(null);
        mainPanel.setBounds(100, 0, 1266, 768);
        mainPanel.setVisible(true);
        // mainPanel.setBackground(new Color(0x424242));

        funPanel = new JPanel(null);
        funPanel.setBounds(0, 0, 100, 768);
        // funPanel.setBackground(new Color(0x424242));
        funPanel.setVisible(true);
        funPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(0x343434)));

        homePanel = new JPanel(null);
        homePanel.setBounds(100, 0, 1266, 768);
        homePanel.setVisible(false);
        // profilePanel.setBackground(new Color(0x424242));

        searchPanel = new JPanel(null);
        searchPanel.setBounds(100, 0, 1266, 768);
        searchPanel.setVisible(false);
        // searchPanel.setBackground(new Color(0x424242));

        createPanel = new JPanel(null);
        createPanel.setBounds(100, 0, 1266, 768);
        createPanel.setVisible(false);
        // createPanel.setBackground(new Color(0x424242));

        notificationPanel = new JPanel(null);
        notificationPanel.setBounds(100, 0, 1266, 768);
        notificationPanel.setVisible(false);
        // notificationPanel.setBackground(new Color(0x424242));

        settingPanel = new JPanel(null);
        settingPanel.setBounds(100, 0, 1266, 768);
        settingPanel.setVisible(false);
        // settingPanel.setBackground(new Color(0x424242));

        ImageIcon hOPen = new ImageIcon("home.png");
        homeOpen = new JLabel(hOPen);
        homeOpen.setBounds(0, 0, 50, 50);
        homeOpen.setVisible(true);

        ImageIcon hClose = new ImageIcon("home(1).png");
        homeClose = new JLabel(hClose);
        homeClose.setBounds(0, 0, 50, 50);
        homeClose.setVisible(false);

        ImageIcon seOPen = new ImageIcon("search.png");
        searchOpen = new JLabel(seOPen);
        searchOpen.setBounds(0, 0, 50, 50);
        searchOpen.setVisible(true);

        ImageIcon seClose = new ImageIcon("search(2).png");
        serachClose = new JLabel(seClose);
        serachClose.setBounds(0, 0, 50, 50);
        serachClose.setVisible(false);

        ImageIcon cOPen = new ImageIcon("add.png");
        createOpen = new JLabel(cOPen);
        createOpen.setBounds(0, 0, 50, 50);

        ImageIcon cClose = new ImageIcon("add(1).png");
        createClose = new JLabel(cClose);
        createClose.setBounds(0, 0, 50, 50);
        createClose.setVisible(false);

        ImageIcon nOPen = new ImageIcon("bell.png");
        notificationOpen = new JLabel(nOPen);
        notificationOpen.setBounds(0, 0, 50, 50);

        ImageIcon nClose = new ImageIcon("bell-ring.png");
        notificationClose = new JLabel(nClose);
        notificationClose.setBounds(0, 0, 50, 50);
        notificationClose.setVisible(false);

        ImageIcon sOPen = new ImageIcon("setting.png");
        settingOpen = new JLabel(sOPen);
        settingOpen.setBounds(0, 0, 50, 50);

        ImageIcon sClose = new ImageIcon("settings.png");
        settingClose = new JLabel(sClose);
        settingClose.setBounds(0, 0, 50, 50);
        settingClose.setVisible(false);

        home = new JButton();
        home.setBorder(null);
        home.setBounds(25, 150, 50, 50);
        home.setBackground(new Color(0x1a2342));
        home.setForeground(Color.WHITE);
        home.setFont(new Font("Poppins", Font.PLAIN, 18));
        home.setCursor(new Cursor(Cursor.HAND_CURSOR));
        home.setFocusable(false);
        home.setOpaque(false);
        home.setContentAreaFilled(false);
        home.setBorderPainted(false);
        home.addActionListener(this);
        home.add(homeOpen);
        home.add(homeClose);

        search = new JButton();
        search.setBorder(null);
        search.setBounds(25, 250, 50, 50);
        search.setBackground(new Color(0x1a2342));
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Poppins", Font.PLAIN, 18));
        search.setCursor(new Cursor(Cursor.HAND_CURSOR));
        search.setFocusable(false);
        search.setOpaque(false);
        search.setContentAreaFilled(false);
        search.setBorderPainted(false);
        search.addActionListener(this);
        search.add(searchOpen);
        search.add(serachClose);

        create = new JButton();
        create.setBorder(null);
        create.setBounds(25, 350, 50, 50);
        create.setBackground(new Color(0x1a2342));
        create.setForeground(Color.WHITE);
        create.setFont(new Font("Poppins", Font.PLAIN, 18));
        create.setCursor(new Cursor(Cursor.HAND_CURSOR));
        create.setFocusable(false);
        create.setOpaque(false);
        create.setContentAreaFilled(false);
        create.setBorderPainted(false);
        create.addActionListener(this);
        create.add(createOpen);
        create.add(createClose);

        notifications = new JButton();
        notifications.setBorder(null);
        notifications.setBounds(25, 450, 50, 50);
        notifications.setBackground(new Color(0x1a2342));
        notifications.setForeground(Color.WHITE);
        notifications.setFont(new Font("Poppins", Font.PLAIN, 18));
        notifications.setCursor(new Cursor(Cursor.HAND_CURSOR));
        notifications.setFocusable(false);
        notifications.setOpaque(false);
        notifications.setContentAreaFilled(false);
        notifications.setBorderPainted(false);
        notifications.addActionListener(this);
        notifications.add(notificationOpen);
        notifications.add(notificationClose);

        setting = new JButton();
        setting.setBorder(null);
        setting.setBounds(25, 550, 50, 50);
        setting.setBackground(new Color(0x1a2342));
        setting.setForeground(Color.WHITE);
        setting.setFont(new Font("Poppins", Font.PLAIN, 18));
        setting.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setting.setFocusable(false);
        setting.setOpaque(false);
        setting.setContentAreaFilled(false);
        setting.setBorderPainted(false);
        setting.addActionListener(this);
        setting.add(settingOpen);
        setting.add(settingClose);

        funPanel.add(home);
        funPanel.add(search);
        funPanel.add(create);
        funPanel.add(notifications);
        funPanel.add(setting);
        headPanel.add(mainPanel);
        headPanel.add(funPanel);
        headPanel.add(homePanel);
        headPanel.add(searchPanel);
        headPanel.add(createPanel);
        headPanel.add(notificationPanel);
        headPanel.add(settingPanel);
        loginFrame.add(headPanel);
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
        if (e.getSource() == home) {
            homeOpen.setVisible(false);
            homeClose.setVisible(true);
            createOpen.setVisible(true);
            createClose.setVisible(false);
            notificationOpen.setVisible(true);
            notificationClose.setVisible(false);
            settingOpen.setVisible(true);
            settingClose.setVisible(false);
            searchOpen.setVisible(true);
            serachClose.setVisible(false);

            mainPanel.setVisible(false);
            homePanel.setVisible(true);
            searchPanel.setVisible(false);
            createPanel.setVisible(false);
            notificationPanel.setVisible(false);
            settingPanel.setVisible(false);

            new Home(loginFrame, headPanel, homePanel, user);
        } else if (e.getSource() == create) {
            homeOpen.setVisible(true);
            homeClose.setVisible(false);
            createOpen.setVisible(false);
            createClose.setVisible(true);
            notificationOpen.setVisible(true);
            notificationClose.setVisible(false);
            settingOpen.setVisible(true);
            settingClose.setVisible(false);
            searchOpen.setVisible(true);
            serachClose.setVisible(false);

            mainPanel.setVisible(false);
            homePanel.setVisible(false);
            searchPanel.setVisible(false);
            createPanel.setVisible(true);
            notificationPanel.setVisible(false);
            settingPanel.setVisible(false);
            new Create(loginFrame, headPanel, createPanel, user);
        } else if (e.getSource() == notifications) {
            homeOpen.setVisible(true);
            homeClose.setVisible(false);
            createOpen.setVisible(true);
            createClose.setVisible(false);
            notificationOpen.setVisible(false);
            notificationClose.setVisible(true);
            settingOpen.setVisible(true);
            settingClose.setVisible(false);
            searchOpen.setVisible(true);
            serachClose.setVisible(false);

            mainPanel.setVisible(false);
            homePanel.setVisible(false);
            searchPanel.setVisible(false);
            createPanel.setVisible(false);
            notificationPanel.setVisible(true);
            settingPanel.setVisible(false);
            new Notifications(loginFrame, headPanel, notificationPanel, user);
        } else if (e.getSource() == setting) {
            homeOpen.setVisible(true);
            homeClose.setVisible(false);
            createOpen.setVisible(true);
            createClose.setVisible(false);
            notificationOpen.setVisible(true);
            notificationClose.setVisible(false);
            settingOpen.setVisible(false);
            settingClose.setVisible(true);
            searchOpen.setVisible(true);
            serachClose.setVisible(false);

            mainPanel.setVisible(false);
            homePanel.setVisible(false);
            searchPanel.setVisible(false);
            createPanel.setVisible(false);
            notificationPanel.setVisible(false);
            settingPanel.setVisible(true);
            funPanel.setVisible(false);
            new Setting(loginFrame, headPanel, funPanel, settingPanel, user);
        } else if (e.getSource() == search) {
            homeOpen.setVisible(true);
            homeClose.setVisible(false);
            createOpen.setVisible(true);
            createClose.setVisible(false);
            notificationOpen.setVisible(true);
            notificationClose.setVisible(false);
            settingOpen.setVisible(true);
            settingClose.setVisible(false);
            searchOpen.setVisible(false);
            serachClose.setVisible(true);

            mainPanel.setVisible(false);
            homePanel.setVisible(false);
            searchPanel.setVisible(true);
            createPanel.setVisible(false);
            notificationPanel.setVisible(false);
            settingPanel.setVisible(false);
            new Search(loginFrame, headPanel, searchPanel, user);
        }
    }

    public static JButton getHome() {
        return home;
    }

    public static JButton getCreate() {
        return create;
    }

    public static JButton getNotifications() {
        return notifications;
    }

    public static JButton getSetting() {
        return setting;
    }

    public static JButton getSearch() {
        return search;
    }
}
