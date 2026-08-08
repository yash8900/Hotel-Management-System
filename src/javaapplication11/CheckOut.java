package javaapplication11;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class CheckOut extends JFrame {
    Choice c1;
    JTextField t1;
    JButton b1, b2, btnCheck;

    public CheckOut() {
        setBounds(530, 200, 800, 400);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Check Out");

        JLabel lblId = new JLabel("Customer ID:");
        lblId.setBounds(35, 80, 100, 20);
        add(lblId);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                c1.add(rs.getString("document_number"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        c1.setBounds(150, 80, 150, 25);
        add(c1);

        JLabel lblRoom = new JLabel("Room Number:");
        lblRoom.setBounds(35, 120, 100, 20);
        add(lblRoom);

        t1 = new JTextField();
        t1.setBounds(150, 120, 150, 25);
        t1.setEditable(false);
        add(t1);

        btnCheck = new JButton("Check");
        btnCheck.setBounds(310, 80, 80, 25);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
        add(btnCheck);

        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    conn c = new conn();
                    String id = c1.getSelectedItem();
                    String query = "select * from customer where document_number = '" + id + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    if (rs.next()) {
                        t1.setText(rs.getString("room_number"));
                    } else {
                        JOptionPane.showMessageDialog(null, "ID Not Found.");
                    }
                } catch (Exception e) { e.printStackTrace(); }
            }
        });

        b1 = new JButton("Check Out");
        b1.setBounds(50, 250, 120, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        add(b1);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    conn c = new conn();
                    String id = c1.getSelectedItem();
                    String room = t1.getText();
                    
                    if (room.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please click Check first.");
                        return;
                    }

                    String q1 = "delete from customer where document_number = '" + id + "'";
                    String q2 = "update room set availability = 'Available' where room_number = '" + room + "'";
                    
                    c.s.executeUpdate(q1);
                    c.s.executeUpdate(q2);

                    JOptionPane.showMessageDialog(null, "Check Out Successful");
                    new Reception().setVisible(true);
                    setVisible(false);
                } catch (Exception e) { e.printStackTrace(); }
            }
        });

        b2 = new JButton("Back");
        b2.setBounds(200, 250, 120, 30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        add(b2);
        b2.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
    }
}