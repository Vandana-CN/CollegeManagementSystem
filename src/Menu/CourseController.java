/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import DBConnection.DBHandler;
import com.jfoenix.controls.JFXButton;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login.LoginController;



/**
 * FXML Controller class
 *
 * @author Soumya Sudipta
 */
public class CourseController implements Initializable {
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
    @FXML
    private AnchorPane courseframe;

    @FXML
    private JFXButton addbutton;

    @FXML
    private JFXButton viewbutton;

    @FXML
    private JFXButton updatebutton;

    @FXML
        AnchorPane hold;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String un = LoginController.getusername().toString();
        handler = new DBHandler(); 
        connection = handler.getConnection();
        
        try{
            String DBQ = "select * from cms.login where id = '"+un+"'";
            pst = connection.prepareStatement(DBQ);
            ResultSet rs = pst.executeQuery(DBQ);
            if(rs.next() == true)
            {
                if(un.length() == 9){
                    addbutton.setDisable(true);
                    updatebutton.setDisable(true);
                }
            }
            
        }
           
        catch (HeadlessException | SQLException e ){
          System.err.println(e);
         }                                
    }    

    @FXML
    public void addEvent(ActionEvent e){
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Course/addcourse.fxml"));
            HomepageController.setforeignNode(root);
        } catch (IOException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    public void updateEvent(ActionEvent e){
       try {
            Parent root = FXMLLoader.load(getClass().getResource("/Course/updatecourse.fxml"));
            HomepageController.setforeignNode(root);
        } catch (IOException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
     @FXML
    public void viewEvent(ActionEvent e){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Course/viewcourse.fxml"));
            HomepageController.setforeignNode(root);
        } catch (IOException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
