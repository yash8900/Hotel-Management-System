package javaapplication11;

import java.sql.*;

public class conn {
    Connection c;
    Statement s;

    public conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
      c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem", "root", "Admin@123");
            
            // YEH LINE ZAROORI HAI: Data ko instantly Workbench me bhejne ke liye
            c.setAutoCommit(true); 
            
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}