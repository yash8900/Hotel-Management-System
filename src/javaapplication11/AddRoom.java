package javaapplication11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddRoom extends JFrame implements ActionListener {
    JTextField t1, t2;
    JComboBox<String> c1, c2, c3;
    JButton b1, b2;

    public AddRoom() {
        setBounds(530, 200, 850, 500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setTitle("Add Hotel Room Information");

        JLabel lblHeading = new JLabel("ADD ROOMS");
        lblHeading.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblHeading.setBounds(150, 20, 150, 20);
        add(lblHeading);

        JLabel lblRoomNo = new JLabel("Room Number:");
        lblRoomNo.setBounds(60, 80, 120, 30);
        add(lblRoomNo);

        t1 = new JTextField();
        t1.setBounds(200, 80, 150, 30);
        add(t1);

        JLabel lblAvailable = new JLabel("Availability:");
        lblAvailable.setBounds(60, 130, 120, 30);
        add(lblAvailable);

        c1 = new JComboBox<>(new String[] { "Available", "Occupied" });
        c1.setBackground(Color.WHITE);
        c1.setBounds(200, 130, 150, 30);
        add(c1);

        JLabel lblStatus = new JLabel("Cleaning Status:");
        lblStatus.setBounds(60, 180, 120, 30);
        add(lblStatus);

        c2 = new JComboBox<>(new String[] { "Cleaned", "Dirty" });
        c2.setBackground(Color.WHITE);
        c2.setBounds(200, 180, 150, 30);
        add(c2);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(60, 230, 120, 30);
        add(lblPrice);

        t2 = new JTextField();
        t2.setBounds(200, 230, 150, 30);
        add(t2);

        JLabel lblBedType = new JLabel("Bed Type:");
        lblBedType.setBounds(60, 280, 120, 30);
        add(lblBedType);

        c3 = new JComboBox<>(new String[] { "Single Bed", "Double Bed", "King Size" });
        c3.setBackground(Color.WHITE);
        c3.setBounds(200, 280, 150, 30);
        add(c3);

        b1 = new JButton("Add Room");
        b1.setBounds(60, 370, 130, 35);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBounds(220, 370, 130, 35);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            String room_number = t1.getText();
            String availability = (String) c1.getSelectedItem();
            String cleaning_status = (String) c2.getSelectedItem();
            String price = t2.getText();
            String bed_type = (String) c3.getSelectedItem();

            if (room_number.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Room Number aur Price khaali nahi ho sakte!");
                return;
            }

            try {
                conn c = new conn();
                // SQL columns ke exact casing format me data insert karne ki query
                String query = "insert into room values('" + room_number + "', '" + availability + "', '" + cleaning_status + "', '" + price + "', '" + bed_type + "')";
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "New Room Added Successfully!");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
            }
        } else if (ae.getSource() == b2) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddRoom();
    }
}