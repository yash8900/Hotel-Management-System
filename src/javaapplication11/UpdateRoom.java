package javaapplication11;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateRoom extends JFrame {
    Choice c1;
    JTextField t1, t2, t3;
    JButton b1, b2, b3;

    public UpdateRoom() {
        setBounds(530, 200, 800, 400);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Update Room Status");

        JLabel lblId = new JLabel("Room Number:");
        lblId.setBounds(35, 80, 100, 20);
        add(lblId);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from room");
            while (rs.next()) {
                c1.add(rs.getString("room_number"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        c1.setBounds(160, 80, 150, 25);
        add(c1);

        JLabel lblAvailability = new JLabel("Availability:");
        lblAvailability.setBounds(35, 120, 100, 20);
        add(lblAvailability);

        t1 = new JTextField();
        t1.setBounds(160, 120, 150, 25);
        add(t1);

        JLabel lblClean = new JLabel("Clean Status:");
        lblClean.setBounds(35, 160, 100, 20);
        add(lblClean);

        t2 = new JTextField();
        t2.setBounds(160, 160, 150, 25);
        add(t2);

        b1 = new JButton("Check");
        b1.setBounds(30, 260, 100, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        add(b1);
        b1.addActionListener(e -> {
            try {
                conn c = new conn();
                String room = c1.getSelectedItem();
                ResultSet rs = c.s.executeQuery("select * from room where room_number = '" + room + "'");
                if (rs.next()) {
                    t1.setText(rs.getString("availability"));
                    t2.setText(rs.getString("cleaning_status"));
                }
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        b2 = new JButton("Update");
        b2.setBounds(150, 260, 100, 30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        add(b2);
        b2.addActionListener(e -> {
            try {
                conn c = new conn();
                String room = c1.getSelectedItem();
                String available = t1.getText();
                String clean = t2.getText();
                
                String str = "update room set availability = '" + available + "', cleaning_status = '" + clean + "' where room_number = '" + room + "'";
                c.s.executeUpdate(str);
                
                JOptionPane.showMessageDialog(null, "Room Updated Successfully");
                new Reception().setVisible(true);
                setVisible(false);
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        b3 = new JButton("Back");
        b3.setBounds(270, 260, 100, 30);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        add(b3);
        b3.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
    }
}