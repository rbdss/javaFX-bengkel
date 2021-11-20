/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teskoneksi;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 *
 * @author gilang
 */
public class teskoneksi {
    private static Connection conn;
    private static Statement stm;
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost/PBO_TB1";
            String user = "root";
            String pass = "Password123";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,pass);
            stm = conn.createStatement();
            System.out.println("Koneksi sukses");
        } catch (Exception e) {
            System.err.println("koneksi gagal" + e.getMessage());
        }
    }
}
