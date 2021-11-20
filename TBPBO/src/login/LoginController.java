/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.ResultSet;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gilang
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane pane_login;
    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button btn_login;
    @FXML
    private AnchorPane pane_signup;
    @FXML
    private TextField txt_username_signup;
    @FXML
    private TextField txt_password_signup;
    @FXML
    private TextField txt_email;
    @FXML
    private Label label_sgup_login;
    @FXML
    private Button btn_menu_login;
    @FXML
    private Button btn_menu_signup;
    @FXML
    private ComboBox account_type;
    @FXML
    private Button btn_signup;
    @FXML
    private Pane dashboard_pane;
    @FXML
    private TableView<Pelanggan> tb_pelanggan;
    @FXML
    private TableColumn<Pelanggan, Integer> col_no;
    @FXML
    private TableColumn<Pelanggan, String> col_nama;
    @FXML
    private TableColumn<Pelanggan, String> col_telp;
    @FXML
    private Label label_dashboard;
  
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @FXML
    private Pane panePelanggan;
    @FXML
    private Pane paneHome;
    @FXML
    private Button btnAddPlngn;
    @FXML
    private Pane paneAddPlngn;
    @FXML
    private TableColumn<Pelanggan, String> col_motor;
    @FXML
    private TableColumn<Pelanggan, String> col_nopol;
    @FXML
    private TextField txt_Pnama;
    @FXML
    private TextField txt_PnoTelp;
    @FXML
    private TextField txt_Pmotor;
    @FXML
    private TextField txt_Pnopol;
    @FXML
    private Label labelFormPel;
    @FXML
    private Button btnAddSave;
    @FXML
    private Button btnEditSave;
    
    
    
    
    @FXML
    public void logout(){
        dashboard_pane.setVisible(false);
    }
    
    public void hideElement(){
        panePelanggan.setVisible(false);
        dashboard_pane.setVisible(false);
    }
    
    public ObservableList<Pelanggan> getPelangganList(){
        ObservableList<Pelanggan> pelangganList = FXCollections.observableArrayList();
        conn = (Connection) mysqlconnect.connectDb();
        String query = "select * from tb_pelanggan";
        Statement st;
        ResultSet rs;
        
        try {
            st = (Statement) conn.createStatement();
            rs = st.executeQuery(query);
            Pelanggan pelanggan;
            while(rs.next()){
                pelanggan = new Pelanggan(rs.getInt("id"), rs.getString("nama"), rs.getString("motor"), rs.getString("noTelp"), rs.getString("noPol"));
                pelangganList.add(pelanggan);
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pelangganList;
        
    }
    
    public void showPelanggan(){
        ObservableList<Pelanggan> list = getPelangganList();
        
        col_no.setCellValueFactory(new PropertyValueFactory<Pelanggan, Integer>("id"));
        col_nama.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("nama"));
        col_motor.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("motor"));
        col_telp.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("noTelp"));
        col_nopol.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("noPol"));
        
        tb_pelanggan.setItems(list);
    }
    
    @FXML
    public void menuPelanggan(){
        showPelanggan();
        panePelanggan.setVisible(true);
        paneHome.setVisible(false);
        paneAddPlngn.setVisible(false);
        tb_pelanggan.setVisible(true);
    }
    @FXML
    public void menuHome(){
        paneHome.setVisible(true);
        panePelanggan.setVisible(false);
    }

//    Panel Pelanggan
    @FXML
    private void addPelanggan(){
        conn = (Connection) mysqlconnect.connectDb();
        String sql = "insert into tb_pelanggan (nama,motor,noTelp,noPol) values (?,?,?,?)";
        
        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, txt_Pnama.getText());
            pst.setString(2, txt_Pmotor.getText());
            pst.setString(3, txt_PnoTelp.getText());
            pst.setString(4, txt_Pnopol.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Ditambahkan");
            menuPelanggan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }                  
    }
    
    @FXML
    private void editPelanggan(){
        conn = (Connection) mysqlconnect.connectDb();
        String sql = "update tb_pelanggan set nama=?, motor=?, noTelp=?, noPol=? where id=?";
        Pelanggan pel = tb_pelanggan.getSelectionModel().getSelectedItem();
        if (pel != null){
            try {
                pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, txt_Pnama.getText());
                pst.setString(2, txt_Pmotor.getText());
                pst.setString(3, txt_PnoTelp.getText());
                pst.setString(4, txt_Pnopol.getText());
                pst.setString(5, String.valueOf(pel.getId()));
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                menuPelanggan();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
    }
    
    @FXML
    private void tambahPelanggan(ActionEvent event) {
        paneAddPlngn.setVisible(true);
        labelFormPel.setText("Tambah Pelanggan");
        btnAddSave.setVisible(true);
        btnEditSave.setVisible(false);
    }
    
    @FXML
    private void ubahPelanggan(ActionEvent event) {
        paneAddPlngn.setVisible(true);
        labelFormPel.setText("Ubah Data");
        btnAddSave.setVisible(false);
        btnEditSave.setVisible(true);
        Pelanggan pel = tb_pelanggan.getSelectionModel().getSelectedItem();
        txt_Pnama.setText(pel.getNama());
        txt_Pmotor.setText(pel.getMotor());
        txt_PnoTelp.setText(pel.getNoTelp());
        txt_Pnopol.setText(pel.getNoPol());
    }
    
    @FXML
    private void hapusPelanggan(){
        conn = (Connection) mysqlconnect.connectDb();
        String sql = "delete from tb_pelanggan where noPol=?";
        Pelanggan pel = tb_pelanggan.getSelectionModel().getSelectedItem();
        if (pel != null){
            try {
                pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, pel.getNoPol());
                System.out.println(pel.getNoPol());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data dihapus");
                menuPelanggan();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

//    end Pelanggan    
    
//    Panel Login & SignUp
    @FXML
    public void loginPaneShow(){
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
        btn_menu_login.setStyle("-fx-background-color: #00BFFF; -fx-text-fill:WHITE");
        btn_menu_signup.setStyle("-fx-background-color:  #87CEFA; -fx-text-fill:WHITE");
        label_sgup_login.setText("LOGIN");
        label_sgup_login.setStyle("-fx-font-size:35; -fx-text-fill:#005b90");
        
    }
    
    @FXML
    public void signupPaneShow(){
        pane_login.setVisible(false);
        pane_signup.setVisible(true);
        btn_menu_login.setStyle("-fx-background-color: #87CEFA; -fx-text-fill:WHITE");
        btn_menu_signup.setStyle("-fx-background-color:  #00BFFF; -fx-text-fill:WHITE");
        label_sgup_login.setText("SIGN UP");
        label_sgup_login.setStyle("-fx-font-size:35; -fx-text-fill:#005b90");
    }
    
    private void addUser(){
        conn = (Connection) mysqlconnect.connectDb();
        String sql = "insert into user (username,password,type,email) values (?,?,?,?)";
        
        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, txt_username_signup.getText());
            pst.setString(2, txt_password_signup.getText());
            pst.setString(3, account_type.getValue().toString());
            pst.setString(4, txt_email.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "User Ditambahkan");
            loginPaneShow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    
    @FXML
    private void loginUser(){
        conn = (Connection) mysqlconnect.connectDb();
        String sql = "select * from user where username = ? and password = ? and type =?";
        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, txt_username.getText());
            pst.setString(2, txt_password.getText());
            pst.setString(3, account_type.getValue().toString());
            var nama = txt_username.getText();
            rs = pst.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Username dan Password benar");
                dashboard_pane.setVisible(true);

            }else{
                JOptionPane.showMessageDialog(null, "Username atau password salah");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
//    end Login & SignUp

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane_login.setVisible(true);
        paneHome.setVisible(true);
        account_type.getItems().addAll("Admin", "Client", "Cashier", "StoreKeeper");
        hideElement();
        showPelanggan();
    }    

    

   
    
}
