/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

/**
 *
 * @author gilang
 */
public class Pelanggan{

   private int id;
   private String nama;
   private String motor;
   private String noTelp;
   private String noPol;

    public Pelanggan(int id, String nama, String motor, String noTelp, String noPol) {
        this.id = id;
        this.nama = nama;
        this.motor = motor;
        this.noTelp = noTelp;
        this.noPol = noPol;
    }

   

    public String getNoPol() {
        return noPol;
    }

    public void setNoPol(String noPol) {
        this.noPol = noPol;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getMotor() {
        return motor;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    

   
}