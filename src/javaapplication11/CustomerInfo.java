package javaapplication11;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class CustomerInfo extends JFrame {
    JTable table;
    JButton btnLoad, btnBack;

    public static void main(String[] args) {
        new CustomerInfo().setVisible(true);
    }

    public CustomerInfo() {
        // Frame Configurations
        setBounds(530, 200, 900, 600);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Customer Information Archive");

        // Heading Label
        JLabel lblHeading = new JLabel("CUSTOMER INFORMATION DIRECTORY");
        lblHeading.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblHeading.setBounds(280, 15, 400, 30);
        add(lblHeading);

        // Table Setup
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.setGridColor(Color.LIGHT_GRAY);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 60, 840, 400);
        add(scrollPane);

        // Action Button: Load Customer Data
        btnLoad = new JButton("Load Data");
        btnLoad.setBounds(280, 490, 140, 30);
        btnLoad.setBackground(Color.BLACK);
        btnLoad.setForeground(Color.WHITE);
        add(btnLoad);

        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    String query = "select * from customer";
                    ResultSet rs = c.s.executeQuery(query);

                    // Creating a structural Model mapping exactly to our updated SQL schema
                    String[] columnNames = {"ID Type", "ID Number", "Name", "Gender", "Country", "Room No", "Check-In Time", "Deposit"};
                    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                    while (rs.next()) {
                        String docType = rs.getString("document_type");
                        String docNum = rs.getString("document_number");
                        String name = rs.getString("name");
                        String gender = rs.getString("gender");
                        String country = rs.getString("country");
                        String room = rs.getString("room_number");
                        String time = rs.getString("checkintime");
                        String deposit = rs.getString("deposit");

                        Object[] rowData = {docType, docNum, name, gender, country, room, time, deposit};
                        model.addRow(rowData);
                    }
                    
                    table.setModel(model);
                    
                    if(model.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(null, "No customer records found in the database.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database Connection Error: " + ex.getMessage());
                }
            }
        });

        // Action Button: Back to Dashboard Menu
        btnBack = new JButton("Back");
        btnBack.setBounds(460, 490, 140, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });

        // Trigger automatic data loading on initialization
        // This cuts out an extra manual click step for the user
        setVisible(true);
    }
}