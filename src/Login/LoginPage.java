package Login;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.login.LoginContext;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DS.UserLL;
import DS.getUserLL;
import Functionalities.Home;

public class LoginPage extends JFrame implements ActionListener {
    JPanel headPanel;
    JPanel fpPanel;
    JButton login;
    JButton register;
    JButton fp;
    JLabel name_label;
    JTextField name;
    JTextField fpPassField;
    JTextField fpEmailField;
    JButton fpBefore;
    JButton fpDone;
    JButton fpBack;
    JLabel pass_label;
    JPasswordField pass;
    JLabel showLabel;
    JLabel hideLabel;
    JButton show;
    JButton hide;

    ConnectionClass con;

    public LoginPage() {
        headPanel = new JPanel();
        headPanel.setBounds(0, 0, 1366, 768);
        headPanel.setLayout(null);
        headPanel.setVisible(true);

        JLabel mainLabel = new JLabel();
        ImageIcon mainIcon = new ImageIcon("bg(1).png");
        mainLabel.setIcon(mainIcon);
        mainLabel.setBounds(0, 0, 1366, 768);
        headPanel.add(mainLabel);
        // ImageIcon bg = new ImageIcon("bg.jpg");
        // frame = new JFrame("EventPlanner");

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

        name_label = new JLabel();
        name_label.setBounds(440, 180, 150, 50);
        name = new JTextField();
        name.setBounds(510, 250, 340, 50);
        name.setBackground(new Color(0x344274));
        name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        name.setText("Enter username");
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Poppins", Font.PLAIN, 18));
        name_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        name.setOpaque(false);
        name.selectAll();

        pass_label = new JLabel();
        pass_label.setBounds(440, 250, 150, 50);
        pass = new JPasswordField();
        pass.setBounds(510, 320, 340, 50);
        pass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        pass.setText("Enter Password");
        pass.setBackground(new Color(0x344274));
        pass.setForeground(Color.WHITE);
        pass.setFont(new Font("Poppins", Font.PLAIN, 18));
        pass_label.setFont(new Font("Poppins", Font.PLAIN, 18));
        pass.setOpaque(false);
        pass.selectAll();
        pass.setEchoChar('*');
        pass.add(show);
        pass.add(hide);

        login = new JButton("Login");
        login.setBorder(null);
        login.setBounds(535, 400, 100, 50);
        login.setFont(new Font("Poppins", Font.BOLD, 12));
        login.setBackground(new Color(0x1a2342));
        login.setForeground(Color.WHITE);
        login.setOpaque(false);
        login.setContentAreaFilled(false);
        login.setBorderPainted(false);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));

        register = new JButton("Create Account");
        register.setBorder(null);
        register.setBounds(700, 400, 100, 50);
        register.setFont(new Font("Poppins", Font.BOLD, 12));
        // register.setBackground(new Color(0x1a2342));
        register.setForeground(Color.WHITE);
        register.setOpaque(false);
        register.setContentAreaFilled(false);
        register.setBorderPainted(false);
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));

        fp = new JButton("Forgot Password");
        fp.setBorder(null);
        fp.setBounds(620, 450, 100, 50);
        fp.setFont(new Font("Poppins", Font.BOLD, 12));
        // fp.setBackground(new Color(0x1a2342));
        fp.setForeground(Color.WHITE);
        fp.setOpaque(false);
        fp.setContentAreaFilled(false);
        fp.setBorderPainted(false);
        fp.setCursor(new Cursor(Cursor.HAND_CURSOR));

        fpPanel = new JPanel(null);
        fpPanel.setBounds(0, 0, 1366, 768);
        JLabel fpMainLabel = new JLabel();
        ImageIcon fpMainIcon = new ImageIcon("bg(1).png");
        fpMainLabel.setIcon(fpMainIcon);
        fpMainLabel.setBounds(0, 0, 1366, 768);

        fpEmailField = new JTextField("Enter Email");
        fpEmailField.setBounds(510, 250, 340, 50);
        fpEmailField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        // fpEmailField.setBackground(new Color(0x344274));
        fpEmailField.setOpaque(false);
        fpEmailField.setForeground(Color.WHITE);
        fpEmailField.setFont(new Font("Poppins", Font.PLAIN, 18));
        fpEmailField.select(0, 11);

        fpPassField = new JTextField("Enter New Password");
        fpPassField.setBounds(510, 320, 340, 50);
        fpPassField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x161d33)));
        // fpPassField.setBackground(new Color(0x344274));
        fpPassField.setOpaque(false);
        fpPassField.setForeground(Color.WHITE);
        fpPassField.setFont(new Font("Poppins", Font.PLAIN, 18));
        fpPassField.select(0, 18);
        fpPassField.setVisible(false);

        fpBefore = new JButton("Submit");
        fpBefore.setBorder(null);
        fpBefore.setBounds(700, 400, 100, 50);
        fpBefore.setFont(new Font("Poppins", Font.BOLD, 15));
        // fpBefore.setBackground(new Color(0x1a2342));
        fpBefore.setForeground(Color.WHITE);
        fpBefore.setOpaque(false);
        fpBefore.setContentAreaFilled(false);
        fpBefore.setBorderPainted(false);
        fpBefore.setFocusable(false);
        fpBefore.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fpBefore.addActionListener(this);

        fpDone = new JButton("Submit");
        fpDone.setBorder(null);
        fpDone.setBounds(700, 400, 100, 50);
        fpDone.setFont(new Font("Poppins", Font.BOLD, 15));
        // fpDone.setBackground(new Color(0x1a2342));
        fpDone.setForeground(Color.WHITE);
        fpDone.setOpaque(false);
        fpDone.setContentAreaFilled(false);
        fpDone.setBorderPainted(false);
        fpDone.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fpDone.setFocusable(false);
        fpDone.setVisible(false);
        fpDone.addActionListener(this);

        fpBack = new JButton("Back");
        fpBack.setBorder(null);
        fpBack.setBounds(560, 400, 100, 50);
        fpBack.setFont(new Font("Poppins", Font.BOLD, 15));
        // fpBack.setBackground(new Color(0x1a2342));
        fpBack.setForeground(Color.WHITE);
        fpBack.setOpaque(false);
        fpBack.setContentAreaFilled(false);
        fpBack.setBorderPainted(false);
        fpBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fpBack.setFocusable(false);
        fpBack.setVisible(true);
        fpBack.addActionListener(this);

        fpPanel.add(fpEmailField);
        fpPanel.add(fpPassField);
        fpPanel.add(fpBack);
        fpPanel.add(fpBefore);
        fpPanel.add(fpDone);
        fpPanel.add(fpMainLabel);
        fpPanel.setVisible(false);

        login.addActionListener(this);
        register.addActionListener(this);
        fp.addActionListener(this);

        login.setFocusable(false);
        register.setFocusable(false);
        fp.setFocusable(false);

        headPanel.add(name_label);
        headPanel.add(name);
        headPanel.add(pass_label);
        headPanel.add(pass);
        headPanel.add(login);
        headPanel.add(register);
        headPanel.add(fp);
        headPanel.add(mainLabel);
        this.add(headPanel);
        this.add(fpPanel);
        this.setTitle("Social Networking Site");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }

    Statement st;
    String query;
    PreparedStatement pst;
    ResultSet rs;
    int r;
    // String user[] = new String[4];
    User user;
    int count = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        Connection con = ConnectionClass.getConnection();
        if (e.getSource() == login) {
            String user_name = name.getText();
            String user_pass = pass.getText();

            try {
                getUserLL l = new getUserLL();
                UserLL userLl = l.getLL();
                if (userLl.checkUser(user_name, user_pass)) {
                    JOptionPane.showMessageDialog(null, "Logged in successfully", "Login",
                            JOptionPane.PLAIN_MESSAGE);
                    headPanel.setVisible(false);
                    // new HomePage(this, headPanel);
                    query = "select username,email,phone_no,gender,dob,password,bio from user where username=?";
                    pst = con.prepareStatement(query);
                    pst.setString(1, user_name);
                    rs = pst.executeQuery();
                    rs.next();
                    // user[0] = rs.getString(1);
                    // user[1] = rs.getString(2);
                    // user[2] = rs.getString(3);
                    // user[3] = rs.getString(4);
                    user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(6),
                            rs.getString(4), rs.getString(7), rs.getDate(5));
                    new HomePage(this, headPanel, user);
                }
                // query = "select * from user where username=?";
                // pst = con.prepareStatement(query);
                // pst.setString(1, user_name);
                // rs = pst.executeQuery();
                // if (rs.next()) {
                // query = "select password from user where username=?";
                // pst = con.prepareStatement(query);
                // pst.setString(1, user_name);
                // rs = pst.executeQuery();
                // rs.next();
                // if (rs.getString(1).equals(user_pass)) {
                // JOptionPane.showMessageDialog(null, "Logged in successfully", "Login",
                // JOptionPane.PLAIN_MESSAGE);
                // headPanel.setVisible(false);
                // // new HomePage(this, headPanel);
                // query = "select username,email,phone_no,gender,dob,password,bio from user
                // where username=?";
                // pst = con.prepareStatement(query);
                // pst.setString(1, user_name);
                // rs = pst.executeQuery();
                // rs.next();
                // // user[0] = rs.getString(1);
                // // user[1] = rs.getString(2);
                // // user[2] = rs.getString(3);
                // // user[3] = rs.getString(4);
                // user = new User(rs.getString(1), rs.getString(2), rs.getString(3),
                // rs.getString(6),
                // rs.getString(4), rs.getString(7), rs.getDate(5));
                // new HomePage(this, headPanel, user);
                // }
                else {
                    JOptionPane.showMessageDialog(null, "INVALID CREDENTIALS", "Login", JOptionPane.ERROR_MESSAGE);
                }
                // else {
                // JOptionPane.showMessageDialog(null, "INVALID PASSWORD", "Login",
                // JOptionPane.ERROR_MESSAGE);
                // }
                // } else {
                // JOptionPane.showMessageDialog(null, "INVALID EMAIL", "Login",
                // JOptionPane.ERROR_MESSAGE);
                // }
            } catch (Exception s) {
                JOptionPane.showMessageDialog(null, s.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                s.getStackTrace();
            }
        } else if (e.getSource() == register) {
            headPanel.setVisible(false);
            new RegisterPage(this, headPanel);
        } else if (e.getSource() == fp) {
            headPanel.setVisible(false);
            fpPanel.setVisible(true);
        } else if (e.getSource() == fpBack) {
            // headPanel.setVisible(false);
            this.dispose();
            new LoginPage();
        } else if (e.getSource() == fpBefore) {
            String user_email = fpEmailField.getText();

            try {
                query = "select phone_no from user where email=?";
                pst = con.prepareStatement(query);
                pst.setString(1, user_email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String p = rs.getString(1);

                    Scanner sc = new Scanner(System.in);
                    Random random = new Random();
                    int otp = 1000 + random.nextInt(9000);
                    JOptionPane.showMessageDialog(null,
                            "OTP SENT on YOUR MOBILE NO.: *******" + p.charAt(p.length() - 3)
                                    + p.charAt(p.length() - 2) + p.charAt(p.length() - 1),
                            "VERIFY", JOptionPane.PLAIN_MESSAGE);
                    while (true) {
                        Thread.sleep(3000);
                        JOptionPane.showMessageDialog(null, "YOUR OTP IS : " + otp, "VERIFY",
                                JOptionPane.PLAIN_MESSAGE);
                        String otp1 = JOptionPane.showInputDialog("Enter OTP");
                        if (String.valueOf(otp).equals(otp1)) {
                            headPanel.setVisible(false);
                            fpBefore.setVisible(false);
                            fpPassField.setVisible(true);
                            fpDone.setVisible(true);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "WRONG OTP", "VERIFY",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Email does't Exist\nEnter Valid Email", "VERIFY",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == fpDone) {
            String user_email_fp = fpEmailField.getText();
            String passString = fpPassField.getText();

            try {
                query = "update user set password=? where email=?";
                pst = con.prepareStatement(query);
                pst.setString(1, passString);
                pst.setString(2, user_email_fp);
                r = pst.executeUpdate();
                if (r > 0) {
                    JOptionPane.showMessageDialog(null, "PASSWORD CHANGED SUCCESSFULLY",
                            "VERIFY",
                            JOptionPane.PLAIN_MESSAGE);
                    fpPanel.setVisible(false);
                    headPanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "PASSWORD NOT CHANGED", "VERIFY",
                            JOptionPane.PLAIN_MESSAGE);
                }
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, e2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == show) {
            hide.setVisible(true);
            show.setVisible(false);
            pass.setEchoChar((char) 0);
        } else if (e.getSource() == hide) {
            hide.setVisible(false);
            show.setVisible(true);
            pass.setEchoChar('*');
        }
    }
}
