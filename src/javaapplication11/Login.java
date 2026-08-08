package javaapplication11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel l1, l2;
    JTextField t1;
    JPasswordField t2;
    JButton b1, b2;

    public Login() {
        super("Hotel Management System - Admin Login");
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(600, 300, 600, 300);

        l1 = new JLabel("Username:");
        l1.setBounds(40, 40, 100, 30);
        add(l1);

        l2 = new JLabel("Password:");
        l2.setBounds(40, 100, 100, 30);
        add(l2);

        t1 = new JTextField();
        t1.setBounds(150, 40, 150, 30);
        add(t1);

        t2 = new JPasswordField();
        t2.setBounds(150, 100, 150, 30);
        add(t2);

        // Submit/Login Button
        b1 = new JButton("Login");
        b1.setBounds(40, 180, 120, 30);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        // Cancel/Exit Button
        b2 = new JButton("Cancel");
        b2.setBounds(180, 180, 120, 30);
        b2.setFont(new Font("serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        // Right side default placeholder icon (optional decoration)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("javaapplication11/icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(350, 20, 200, 200);
        add(l3);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            String username = t1.getText();
            String password = new String(t2.getPassword());

            // Simple validation check empty blocks ke liye
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username aur Password khaali nahi ho sakte!");
                return;
            }

            try {
                // 1. Database Connection initialize karo
                conn c = new conn();
                
                // 2. Query run karo login details check karne ke liye
                // Note: Tumhare system ke default verification credentials (admin / 12345)
                String query = "select * from login where username='" + username + "' and password='" + password + "'";
                
                ResultSet rs = c.s.executeQuery(query);
                
                if (rs.next()) {
                    // Agar login details match ho gayi toh Dashboard open karo
                    new Dashboard().setVisible(true);
                    setVisible(false); // Login screen close kar do
                } else {
                    // Agar mismatch hua toh error throw karo
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password! Dobara try karein.");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Connection Error: " + e.getMessage());
            }
            
        } else if (ae.getSource() == b2) {
            System.exit(0); // App ko directly close karne ke liye
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}