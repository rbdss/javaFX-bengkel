/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author gilang
 */
public class mysqlconnect {
    Connection conn = null;
    public static Connection connectDb(){
        String driver = "com.mysql.jdbc.Driver";
        String host = "jdbc:mysql://localhost/PBO_TB1";
        String user = "root";
        String pass = "Password123";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(host, user, pass);
            System.out.println("Koneksi berhasil");
            return conn;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}