package javaapplication11;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateCheck extends JFrame {
    Choice c1;
    JTextField t1, t2, t3, t4, t5;
    JButton b1, b2, b3;

    public UpdateCheck() {
        setBounds(530, 200, 800, 500);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Update Check-In Status");

        JLabel lblId = new JLabel("Customer ID:");
        lblId.setBounds(35, 50, 100, 20);
        add(lblId);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                c1.add(rs.getString("document_number"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        c1.setBounds(200, 50, 150, 25);
        add(c1);

        JLabel lblRoom = new JLabel("Room Number:");
        lblRoom.setBounds(35, 100, 100, 20);
        add(lblRoom);

        t1 = new JTextField();
        t1.setBounds(200, 100, 150, 25);
        add(t1);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(35, 150, 100, 20);
        add(lblName);

        t2 = new JTextField();
        t2.setBounds(200, 150, 150, 25);
        add(t2);

        JLabel lblCheckIn = new JLabel("Check-In Time:");
        lblCheckIn.setBounds(35, 200, 100, 20);
        add(lblCheckIn);

        t3 = new JTextField();
        t3.setBounds(200, 200, 150, 25);
        add(t3);

        JLabel lblDeposit = new JLabel("Amount Deposited:");
        lblDeposit.setBounds(35, 250, 120, 20);
        add(lblDeposit);

        t4 = new JTextField();
        t4.setBounds(200, 250, 150, 25);
        add(t4);

        b1 = new JButton("Check");
        b1.setBounds(30, 350, 100, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        add(b1);
        b1.addActionListener(e -> {
            try {
                conn c = new conn();
                String id = c1.getSelectedItem();
                ResultSet rs = c.s.executeQuery("select * from customer where document_number = '" + id + "'");
                if (rs.next()) {
                    t1.setText(rs.getString("room_number"));
                    t2.setText(rs.getString("name"));
                    t3.setText(rs.getString("checkintime"));
                    t4.setText(rs.getString("deposit"));
                }
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        b2 = new JButton("Update");
        b2.setBounds(150, 350, 100, 30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        add(b2);
        b2.addActionListener(e -> {
            try {
                conn c = new conn();
                String id = c1.getSelectedItem();
                String room = t1.getText();
                String name = t2.getText();
                String time = t3.getText();
                String deposit = t4.getText();
                
                String str = "update customer set room_number = '" + room + "', name = '" + name + "', checkintime = '" + time + "', deposit = '" + deposit + "' where document_number = '" + id + "'";
                c.s.executeUpdate(str);
                
                JOptionPane.showMessageDialog(null, "Customer Record Updated Successfully");
                new Reception().setVisible(true);
                setVisible(false);
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        b3 = new JButton("Back");
        b3.setBounds(270, 350, 100, 30);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        add(b3);
        b3.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
    }
}