package Login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import DS.UserLL;
import DS.getUserLL;
import Functionalities.Home;

public class RegisterPage extends JFrame implements ActionListener {

    JFrame loginFrame;
    JPanel headPanel;
    JPanel logiPanel;
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
    JButton submit;
    JButton login;
    JDateChooser dc;
    JLabel showLabel;
    JLabel hideLabel;
    JButton show;
    JButton hide;
    JLabel fileLabel1;
    JLabel fileLabel2;
    JButton fileChooser;
    JButton close;
    JLabel buttonLabel;
    Connection con;

    String user_gender;

    RegisterPage(JFrame of, JPanel op) {
        loginFrame = of;
        logiPanel = op;
        headPanel = new JPanel();
        headPanel.setBounds(0, 0, 1366, 768);
        headPanel.setLayout(null);
        headPanel.setVisible(true);

        con = ConnectionClass.getConnection();
        JLabel mainLabel = new JLabel();
        ImageIcon mainIcon = new ImageIcon("bg(1).png");
        mainLabel.setIcon(mainIcon);
        mainLabel.setBounds(0, 0, 1376, 778);

        ImageIcon showIcon = new ImageIcon("show.png");
        showLabel = new JLabel();
        showLabel.setIcon(showIcon);
        showLabel.setBounds(300, 10, 32, 32);

        ImageIcon hideIcon = new ImageIcon("hide.png");
        hideLabel = new JLabel(hideIcon);
        hideLabel.setBounds(300, 10, 32, 32);

        show = new JButton(showIcon);
        show.setBorder(null);
        show.setBounds(300, 10, 32, 32);
        show.setOpaque(false);
        show.setContentAreaFilled(false);
        show.setBorderPainted(false);
        show.setCursor(new Cursor(Cursor.HAND_CURSOR));
        show.addActionListener(this);
        show.setVisible(true);

        hide = new JButton(hideIcon);
        hide.setBorder(null);
        hide.setBounds(300, 10, 32, 32);
        hide.setOpaque(false);
        hide.setContentAreaFilled(false);
        hide.setBorderPainted(false);
        hide.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hide.addActionListener(this);
        hide.setVisible(false);

        email_label = new JLabel("Email");
        // email_label.setBounds(450, 400, 150, 50);
        email_label.setBounds(230, 108, 150, 50);
        email_label.setForeground(Color.WHITE);
        email = new JTextField();
        // email.setBounds(510, 408, 350, 50);
        email.setBounds(380, 108, 350, 50);
        email.setBorder(null);
        email.setBackground(new Color(0x344274));
        email.setForeground(Color.WHITE);
        email.setOpaque(false);
        email.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        // email.setText("Enter Email");
        email.selectAll();
        email_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        email.setFont(new Font("Poppins", Font.PLAIN, 18));

        name_label = new JLabel("Username");
        // name_label.setBounds(450, 190, 150, 50);
        name_label.setBounds(230, 178, 150, 50);
        name_label.setForeground(Color.WHITE);
        name = new JTextField();
        // name.setBounds(510, 198, 350, 50);
        name.setBounds(380, 178, 350, 50);
        name.setBorder(null);
        name.setBackground(new Color(0x344274));
        name.setForeground(Color.WHITE);
        name.setOpaque(false);
        name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        // name.setText("Enter Name");
        name.selectAll();
        name_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        name.setFont(new Font("Poppins", Font.PLAIN, 18));

        phno_label = new JLabel("Mobile No.");
        phno_label.setBounds(230, 248, 150, 50);
        phno_label.setForeground(Color.WHITE);
        phno = new JTextField();
        phno.setBounds(380, 248, 350, 50);
        phno.setBorder(null);
        phno.setBackground(new Color(0x344274));
        phno.setForeground(Color.WHITE);
        phno.setOpaque(false);
        phno.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        phno_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        phno.setFont(new Font("Poppins", Font.PLAIN, 18));
        phno.setText("+91 ");
        phno.setEditable(true);

        pass_label = new JLabel("Password");
        // pass_label.setBounds(450, 268, 150, 50);
        pass_label.setBounds(230, 318, 150, 50);
        pass_label.setForeground(Color.WHITE);
        pass = new JPasswordField();
        // pass.setBounds(510, 268, 350, 50);
        pass.setBounds(380, 318, 350, 50);
        pass.setBorder(null);
        pass.setBackground(new Color(0x344274));
        pass.setForeground(Color.WHITE);
        pass.setOpaque(false);
        pass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        // pass.setText("Enter Password");
        pass.selectAll();
        pass_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        pass.setFont(new Font("Poppins", Font.PLAIN, 18));
        pass.setEchoChar('*');
        pass.add(show);
        pass.add(hide);

        age_label = new JLabel("Gender");
        age_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        age_label.setBounds(230, 380, 150, 50);
        age_label.setForeground(Color.WHITE);
        male = new JRadioButton("male");
        male.setBounds(380, 380, 100, 50);
        male.setOpaque(false);
        male.setFont(new Font("Poppins", Font.PLAIN, 18));
        male.setForeground(Color.WHITE);
        male.setCursor(new Cursor(Cursor.HAND_CURSOR));
        male.setFocusable(false);
        male.setSelected(true);
        female = new JRadioButton("female");
        female.setBounds(500, 380, 100, 50);
        female.setOpaque(false);
        female.setFont(new Font("Poppins", Font.PLAIN, 18));
        female.setForeground(Color.WHITE);
        female.setCursor(new Cursor(Cursor.HAND_CURSOR));
        female.setFocusable(false);
        ButtonGroup gender = new ButtonGroup();
        gender.add(male);
        gender.add(female);

        ActionListener date = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == male) {
                    user_gender = male.getText();
                } else if (e.getSource() == female) {
                    user_gender = female.getText();
                }
            }
        };
        female.addActionListener(date);
        male.addActionListener(date);

        dc_label = new JLabel("Date of Birth");
        dc_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        dc_label.setBounds(230, 440, 150, 50);
        dc_label.setForeground(Color.WHITE);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -15);
        dc = new JDateChooser();
        dc.setBounds(380, 440, 300, 50);
        dc.getJCalendar().setPreferredSize(new Dimension(300, 200));
        dc.setMaxSelectableDate(c.getTime());
        dc.setFont(new Font("Poppins", Font.PLAIN, 15));
        dc.getJCalendar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        dc.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bio_Label = new JLabel("Bio");
        bio_Label.setBounds(230, 510, 150, 50);
        bio_Label.setForeground(Color.WHITE);
        bio = new JTextArea();
        bio.setBounds(380, 510, 350, 50);
        bio.setBorder(null);
        bio.setBackground(new Color(0x344274));
        bio.setForeground(Color.WHITE);
        bio.setOpaque(false);
        bio.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        bio_Label.setFont(new Font("Poppins", Font.PLAIN, 18));
        bio.setFont(new Font("Poppins", Font.PLAIN, 18));
        bio.setEditable(true);

        JPanel addPanel = new JPanel(null);
        addPanel.setVisible(true);
        addPanel.setBounds(800, 200, 470, 300);
        // addPanel.setBackground(new Color(0x424242));
        addPanel.setOpaque(false);
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

        buttonLabel = new JLabel("choose profile picture");
        buttonLabel.setBounds(150, 210, 200, 50);
        buttonLabel.setForeground(Color.WHITE);
        buttonLabel.setFont(new Font("Poppins", Font.PLAIN, 18));
        buttonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonLabel.setVisible(true);

        submit = new JButton("Register");
        submit.setBorder(null);
        submit.setBounds(680, 620, 100, 50);
        submit.setBackground(new Color(0x1a2342));
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Poppins", Font.PLAIN, 18));
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit.setFocusable(false);
        submit.setOpaque(false);
        submit.setContentAreaFilled(false);
        submit.setBorderPainted(false);
        submit.addActionListener(this);

        login = new JButton("Login");
        login.setBorder(null);
        login.setBounds(530, 620, 100, 50);
        login.setBackground(new Color(0x1a2342));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Poppins", Font.PLAIN, 18));
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.setFocusable(false);
        login.setOpaque(false);
        login.setContentAreaFilled(false);
        login.setBorderPainted(false);
        login.addActionListener(this);

        addPanel.add(fileChooser);
        addPanel.add(buttonLabel);
        addPanel.add(close);

        headPanel.add(addPanel);
        headPanel.add(name_label);
        headPanel.add(name);
        headPanel.add(pass_label);
        headPanel.add(pass);
        headPanel.add(phno_label);
        headPanel.add(phno);
        headPanel.add(email_label);
        headPanel.add(email);
        headPanel.add(age_label);
        headPanel.add(male);
        headPanel.add(female);
        headPanel.add(dc_label);
        headPanel.add(dc);
        headPanel.add(bio_Label);
        headPanel.add(bio);
        headPanel.add(submit);
        headPanel.add(login);
        headPanel.add(mainLabel);
        loginFrame.add(headPanel);
    }

    Statement st;
    String query;
    PreparedStatement pst;
    ResultSet rs;
    int r;
    User user;
    String file_path = "profilePic.png";

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            // headPanel.setVisible(false);
            loginFrame.dispose();
            new LoginPage();
        } else if (e.getSource() == show) {
            hide.setVisible(true);
            show.setVisible(false);
            pass.setEchoChar((char) 0);
        } else if (e.getSource() == hide) {
            hide.setVisible(false);
            show.setVisible(true);
            pass.setEchoChar('*');
        } else if (e.getSource() == fileChooser) {
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
            file_path = "profilePic.png";
            close.setVisible(false);
            fileLabel2.setVisible(false);
            fileLabel1.setVisible(true);
            buttonLabel.setVisible(true);
            fileChooser.setBounds(175, 50, 128, 128);
        } else if (e.getSource() == submit) {
            String user_email = email.getText();
            String user_pass = pass.getText();
            String user_phno = phno.getText();
            String user_name = name.getText();
            java.sql.Date user_dob = new java.sql.Date(dc.getDate().getTime());
            String user_bio = bio.getText();

            try {
                getUserLL l = new getUserLL();
                UserLL userLl = l.getLL();
                if (userLl.checkOldUser(user_name)) {
                    JOptionPane.showMessageDialog(null, "EMAIL ALREADY EXIST", "Register",
                            JOptionPane.WARNING_MESSAGE);
                }
                // query = "select * from user where email=?";
                // pst = con.prepareStatement(query);
                // pst.setString(1, user_email);
                // rs = pst.executeQuery();
                // if (rs.next()) {
                // JOptionPane.showMessageDialog(null, "EMAIL ALREADY EXIST", "Register",
                // JOptionPane.WARNING_MESSAGE);
                // }
                else {
                    query = "insert into user(username,email,phone_no,gender,dob,password,bio,profilePicture_name,profile_picture) values(?,?,?,?,?,?,?,?,?)";
                    pst = con.prepareStatement(query);
                    pst.setString(1, user_name);
                    pst.setString(2, user_email);
                    pst.setString(3, user_phno);
                    pst.setString(4, user_gender);
                    pst.setDate(5, user_dob);
                    pst.setString(6, user_pass);
                    pst.setString(7, user_bio);

                    File f = new File(file_path);
                    FileInputStream fis = new FileInputStream(f);
                    pst.setString(8, f.getName());
                    pst.setBlob(9, fis);
                    r = pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "REGISTERED SUCCESSFULLY", "Register",
                            JOptionPane.PLAIN_MESSAGE);
                    headPanel.setVisible(false);
                    user = new User(user_name, user_email, user_phno, user_pass, user_gender, user_bio, user_dob);
                    new HomePage(loginFrame, headPanel, user);

                }

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
