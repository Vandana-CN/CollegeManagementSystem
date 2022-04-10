/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;
import Menu.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import DBConnection.DBHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class LoginController implements Initializable{
    int login_count = 0,initlogin_count = 0;
     @FXML
    private ImageView close;
    
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton loginbutton;

    @FXML
    private JFXButton forgotpassword;

    @FXML
    private ImageView progress;
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progress.setVisible(false);
        handler = new DBHandler();
    }
    
    public static String un;
    
    // Login button Press
    
    @FXML
    public void loginAction(ActionEvent e) throws InterruptedException {
    	un = username.getText();
        progress.setVisible(true);
        connection = handler.getConnection();
        
        //If exceeded max login times
        //
        //
        
        try{
            String insert = "select logincount from cms.login where id = '"+username.getText()+"'";
            pst = connection.prepareStatement(insert);
            ResultSet rs = pst.executeQuery(insert);
            
            if(rs.next() == true){
                int flag = ((Number) rs.getObject(1)).intValue();
                System.out.println(flag);
                
                if(flag == 3)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Login Denied!!! Contact your administrator");
                    alert.show();
                    System.exit(0);
                }
            }
        } catch (SQLException ex){
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //
        //
        //End
        
        //Username Password Check Option
        //
        //
        String insert1 = "select * from cms.login where id = '"+username.getText()+"' and password = '"+password.getText()+"'"; 
        try {
            pst = connection.prepareStatement(insert1);
            ResultSet rs = pst.executeQuery(insert1);
            
            // If username and password match
            //
            //
            if(rs.next()==true){
                JOptionPane.showMessageDialog(null,"Logged in Successfully");
                try{
                    String insert2 = "update cms.login set logincount = '"+initlogin_count+"' where id = '"+username.getText()+"' and password = '"+password.getText()+"'"; 
                    pst = connection.prepareStatement(insert2);
                    pst.executeUpdate(insert2);
                    Stage home = new Stage();
                     try {
                            Parent root = FXMLLoader.load(getClass().getResource("/Menu/Homepage.fxml"));
                            Scene scene = new Scene(root);
                            home.setScene(scene);
                            home.initStyle(StageStyle.UNDECORATED);
                            loginbutton.getScene().getWindow().hide();
                            home.show();
                    } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     
                } catch (SQLException ex){
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            //
            //
            //End
            
            //If username and password does not match
            //
            //
            else {
                login_count++;
                String insert3 = "update cms.login set logincount = '"+login_count+"' where id = '"+username.getText()+"'";
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Please Check your Login Credentials");
                alert.setContentText("Login Denied");
                alert.show();
                try{
                    pst = connection.prepareStatement(insert3);
                    pst.executeUpdate(insert3);
                } catch (SQLException ex){
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }    
            }
            //
            //
            //End
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // logincount check
        if(login_count == 3){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Contact you Administrator");
            alert.setContentText("Login attempts exceeded!!!");
            alert.show();
            System.exit(0);
        }
        progress.setVisible(false);
        
    }
    

    @FXML
    public void forgotPasswordAction(ActionEvent e) throws IOException{
        Stage fp = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/login/forgotpassword.fxml"));
        Scene scene = new Scene(root);
        fp.setScene(scene);
        fp.initStyle(StageStyle.UNDECORATED);
        fp.show();
        fp.setResizable(false);
    }
    
    public static String getusername(){
        return un;
    }
    
    @FXML
    private void mouseClicked(MouseEvent  e){
        System.exit(0);
    }
}

