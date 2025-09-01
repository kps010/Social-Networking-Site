package Functionalities;

import java.awt.*;
import javax.swing.*;

public class check {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JLabel name = new JLabel("Kunal Prajapati");
        name.setBounds(40, 40, 300, 75);
        name.setFont(new Font("Poppins", Font.BOLD, 25));
        JLabel bio = new JLabel("Bio");
        bio.setBounds(40, 120, 300, 75);
        bio.setFont(new Font("Poppins", Font.PLAIN, 18));
        JButton follow = new JButton("follow");
        follow.setBounds(340, 55, 100, 50);
        follow.setOpaque(false);
        follow.setContentAreaFilled(false);
        follow.setBorderPainted(false);
        follow.setFont(new Font("Poppins", Font.PLAIN, 18));
        JPanel post = new JPanel();
        post.setBounds(40, 200, 500, 500);
        JLabel label = new JLabel("no posts uploaded");
        label.setFont(new Font("Poppins", Font.PLAIN, 18));
        label.setBounds(100, 50, 200, 50);
        post.add(label);

        frame.add(name);
        frame.add(bio);
        frame.add(follow);
        frame.add(post);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
