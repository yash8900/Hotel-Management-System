package javaapplication11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddEmployee extends JFrame implements ActionListener {
    JTextField t1, t2, t3, t4, t5, t6;
    JRadioButton r1, r2;
    JComboBox<String> cb;
    JButton b1, b2;

    public AddEmployee() {
        setBounds(530, 200, 850, 540);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setTitle("Add New Staff Profile");

        JLabel lblName = new JLabel("NAME:");
        lblName.setBounds(60, 30, 120, 30);
        add(lblName);
        t1 = new JTextField();
        t1.setBounds(200, 30, 150, 30);
        add(t1);

        JLabel lblAge = new JLabel("AGE:");
        lblAge.setBounds(60, 80, 120, 30);
        add(lblAge);
        t2 = new JTextField();
        t2.setBounds(200, 80, 150, 30);
        add(t2);

        JLabel lblGender = new JLabel("GENDER:");
        lblGender.setBounds(60, 130, 120, 30);
        add(lblGender);
        
        r1 = new JRadioButton("Male");
        r1.setBackground(Color.WHITE);
        r1.setBounds(200, 130, 70, 30);
        add(r1);
        
        r2 = new JRadioButton("Female");
        r2.setBackground(Color.WHITE);
        r2.setBounds(280, 130, 80, 30);
        add(r2);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1); bg.add(r2);
        r1.setSelected(true);

        JLabel lblJob = new JLabel("JOB PROFILE:");
        lblJob.setBounds(60, 180, 120, 30);
        add(lblJob);
        
        String jobs[] = {"Front Office Clerks", "Housekeeping", "Kitchen Staff", "Room Service", "Manager", "Accountant", "Chef"};
        cb = new JComboBox<>(jobs);
        cb.setBackground(Color.WHITE);
        cb.setBounds(200, 180, 150, 30);
        add(cb);

        JLabel lblSalary = new JLabel("SALARY:");
        lblSalary.setBounds(60, 230, 120, 30);
        add(lblSalary);
        t3 = new JTextField();
        t3.setBounds(200, 230, 150, 30);
        add(t3);

        JLabel lblPhone = new JLabel("PHONE:");
        lblPhone.setBounds(60, 280, 120, 30);
        add(lblPhone);
        t4 = new JTextField();
        t4.setBounds(200, 280, 150, 30);
        add(t4);

        JLabel lblEmail = new JLabel("EMAIL:");
        lblEmail.setBounds(60, 330, 120, 30);
        add(lblEmail);
        t5 = new JTextField();
        t5.setBounds(200, 330, 150, 30);
        add(t5);

        JLabel lblAadhar = new JLabel("AADHAR NO:");
        lblAadhar.setBounds(60, 380, 120, 30);
        add(lblAadhar);
        t6 = new JTextField();
        t6.setBounds(200, 380, 150, 30);
        add(t6);

        b1 = new JButton("Submit");
        b1.setBounds(60, 440, 130, 35);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBounds(220, 440, 130, 35);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            String name = t1.getText();
            String age = t2.getText();
            String gender = r1.isSelected() ? "Male" : "Female";
            String job = (String) cb.getSelectedItem();
            String salary = t3.getText();
            String phone = t4.getText();
            String email = t5.getText();
            String aadhar = t6.getText();

            // Safety Validation Check
            if (name.isEmpty() || aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name and Aadhar are strictly mandatory!");
                return;
            }

            try {
                // 1. Apni connection file ko call karo
                conn c = new conn();
                
                // 2. SQL Command banao jo tumhare columns se map karega
                String query = "insert into employee values('" + name + "', '" + age + "', '" + gender + "', '" + job + "', '" + salary + "', '" + phone + "', '" + email + "', '" + aadhar + "')";
                
                // 3. Database me execute karke physical storage me push karo
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Employee Added Successfully!");
                setVisible(false);
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error! Duplicate Aadhar or Connection issue.");
            }
        } else if (ae.getSource() == b2) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}