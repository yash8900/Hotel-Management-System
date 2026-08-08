/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication11;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class Employee extends JFrame {

    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Employee frame = new Employee();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Employee() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(430, 200, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Header Labels
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(40, 11, 100, 20);
        contentPane.add(lblName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(160, 11, 100, 20);
        contentPane.add(lblAge);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(280, 11, 100, 20);
        contentPane.add(lblGender);

        JLabel lblJob = new JLabel("Job");
        lblJob.setBounds(410, 11, 100, 20);
        contentPane.add(lblJob);

        JLabel lblSalary = new JLabel("Salary");
        lblSalary.setBounds(540, 11, 100, 20);
        contentPane.add(lblSalary);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(670, 11, 100, 20);
        contentPane.add(lblPhone);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(790, 11, 100, 20);
        contentPane.add(lblEmail);

        JLabel lblAadhar = new JLabel("Aadhar");
        lblAadhar.setBounds(910, 11, 100, 20);
        contentPane.add(lblAadhar);

        // Table setup with ScrollPane
        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 40, 1000, 450);
        contentPane.add(jsp);

        // --- BUTTONS ---
        
        JButton btnLoadData = new JButton("Load Data");
        btnLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    // Query must be lowercase to match your SQL script
                    String query = "select * from employee"; 
                    ResultSet rs = c.s.executeQuery(query);
                    
                    // Fills the JTable with database rows
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                    
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnLoadData.setBounds(350, 510, 120, 30);
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.WHITE);
        contentPane.add(btnLoadData);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                // Make sure your Reception file exists!
                // new Reception().setVisible(true); 
            }
        });
        btnBack.setBounds(510, 510, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);
    }
}