package javaapplication11;

import javax.swing.*;
import java.sql.*;	
import java.awt.event.*;
import java.awt.*;

public class Reception extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        new Reception();
    }
    
    public Reception(){
        // Frame Settings
        setBounds(530, 200, 850, 570);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Hotel Management System - Reception");
        
        // Sidebar Image Handling
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("javaapplication11/icons/fourth.jpg"));
            Image i3 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
            JLabel l1 = new JLabel(new ImageIcon(i3));
            l1.setBounds(250, 30, 500, 470);
            add(l1);
        } catch (Exception e) {
            System.out.println("Image path error: javaapplication11/icons/fourth.jpg not found.");
        }
        
        // --- BUTTONS USING LAMBDA EXPRESSIONS ---
        
        createMenuButton("New Customer Form", 30, e -> {
            try {
                new NewCustomer().setVisible(true);
                setVisible(false);
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        createMenuButton("Room Information", 70, e -> {
            try {
                new Room().setVisible(true);
                setVisible(false);
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        createMenuButton("Department", 110, e -> {
            try {
                new Department().setVisible(true);
                setVisible(false);
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        createMenuButton("All Employee Info", 150, e -> {
            try {
                new Employee().setVisible(true);
                setVisible(false);
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        createMenuButton("Customer Info", 190, e -> {
            try {
                new CustomerInfo().setVisible(true);                
                setVisible(false);
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        // FIXED: Catching general Exception to avoid the "SQLException never thrown" build error
        createMenuButton("Check Out", 230, e -> {
            try {
                new CheckOut().setVisible(true);
                setVisible(false);
            } catch (Exception ex) { 
                ex.printStackTrace(); 
            }
        });

        createMenuButton("Update Check Status", 270, e -> {
            try {
                new UpdateCheck().setVisible(true);
                setVisible(false);
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        createMenuButton("Update Room Status", 310, e -> {
            try {
                new UpdateRoom().setVisible(true);
                setVisible(false);
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        createMenuButton("Pick up Service", 350, e -> {
            try {
                new PickUp().setVisible(true);
                setVisible(false);
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        createMenuButton("Search Room", 390, e -> {
            try {
                new SearchRoom().setVisible(true);
                setVisible(false);
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        createMenuButton("Log Out", 470, e -> {
            new Login().setVisible(true);
            setVisible(false);
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Helper Method to reduce redundant code and keep the UI consistent.
     */
    private void createMenuButton(String text, int y, ActionListener action) {
        JButton btn = new JButton(text);
        btn.setBounds(10, y, 200, 30);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFocusable(false);
        btn.addActionListener(action);
        contentPane.add(btn);
    }
}