package javaapplication11;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewCustomer extends JFrame implements ActionListener {
    JTextField t1, t2, t3, t5, t6;
    JComboBox<String> comboBox;
    JRadioButton r1, r2;
    Choice c1;
    JButton b1, b2;

    public NewCustomer() {
        setBounds(530, 200, 850, 550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setTitle("Guest Check-In Form");

        JLabel lblTitle = new JLabel("NEW CUSTOMER FORM");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitle.setBounds(100, 20, 250, 30);
        add(lblTitle);

        JLabel lblId = new JLabel("ID Document Type:");
        lblId.setBounds(35, 75, 150, 20);
        add(lblId);

        comboBox = new JComboBox<>(new String[]{"Passport", "Aadhar Card", "Voter ID", "Driving License"});
        comboBox.setBounds(220, 75, 150, 25);
        comboBox.setBackground(Color.WHITE);
        add(comboBox);

        JLabel lblNo = new JLabel("ID Document Number:");
        lblNo.setBounds(35, 120, 150, 20);
        add(lblNo);

        t1 = new JTextField();
        t1.setBounds(220, 120, 150, 25);
        add(t1);

        JLabel lblName = new JLabel("Guest Name:");
        lblName.setBounds(35, 165, 150, 20);
        add(lblName);

        t2 = new JTextField();
        t2.setBounds(220, 165, 150, 25);
        add(t2);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(35, 210, 150, 20);
        add(lblGender);

        r1 = new JRadioButton("Male");
        r1.setBackground(Color.WHITE);
        r1.setBounds(220, 210, 60, 25);
        add(r1);

        r2 = new JRadioButton("Female");
        r2.setBackground(Color.WHITE);
        r2.setBounds(290, 210, 80, 25);
        add(r2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1); bg.add(r2);
        r1.setSelected(true);

        JLabel lblCountry = new JLabel("Country:");
        lblCountry.setBounds(35, 255, 150, 20);
        add(lblCountry);

        t3 = new JTextField();
        t3.setBounds(220, 255, 150, 25);
        add(t3);

        JLabel lblRoom = new JLabel("Allocated Room Number:");
        lblRoom.setBounds(35, 300, 150, 20);
        add(lblRoom);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from room where availability = 'Available'");
            while (rs.next()) {
                c1.add(rs.getString("room_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(220, 300, 150, 25);
        add(c1);

        JLabel lblTime = new JLabel("Checked-In Time:");
        lblTime.setBounds(35, 345, 150, 20);
        add(lblTime);

        t5 = new JTextField();
        t5.setBounds(220, 345, 180, 25);
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy");
        t5.setText(sdf.format(new Date()));
        t5.setEditable(false);
        add(t5);

        JLabel lblDeposit = new JLabel("Advance Deposit:");
        lblDeposit.setBounds(35, 390, 150, 20);
        add(lblDeposit);

        t6 = new JTextField();
        t6.setBounds(220, 390, 150, 25);
        add(t6);

        b1 = new JButton("Add Customer");
        b1.setBounds(50, 450, 140, 35);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBounds(220, 450, 140, 35);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            String document_type = (String) comboBox.getSelectedItem();
            String document_number = t1.getText();
            String name = t2.getText();
            String gender = r1.isSelected() ? "Male" : "Female";
            String country = t3.getText();
            String room_number = c1.getSelectedItem();
            String checkintime = t5.getText();
            String deposit = t6.getText();

            if (document_number.isEmpty() || name.isEmpty() || room_number == null) {
                JOptionPane.showMessageDialog(null, "Error: Saari details bharein aur check karein ki room select hua hai!");
                return;
            }

            try {
                conn c = new conn();
                // Dono queries sequential chalengi—customer add hoga aur room table automatic lock/occupied ho jayega
                String q1 = "insert into customer values('" + document_type + "', '" + document_number + "', '" + name + "', '" + gender + "', '" + country + "', '" + room_number + "', '" + checkintime + "', '" + deposit + "')";
                String q2 = "update room set availability = 'Occupied' where room_number = '" + room_number + "'";
                
                c.s.executeUpdate(q1);
                c.s.executeUpdate(q2);

                JOptionPane.showMessageDialog(null, "Customer Checked-In Successfully!");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Transaction Failed: " + e.getMessage());
            }
        } else if (ae.getSource() == b2) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}