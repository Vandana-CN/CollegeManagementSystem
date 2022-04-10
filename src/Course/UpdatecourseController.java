/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Course;

import DBConnection.DBHandler;
import Menu.CourseController;
import Menu.HomepageController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.Parent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Soumya Sudipta
 */
public class UpdatecourseController implements Initializable {
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
     @FXML
    private JFXComboBox<String> existingcourse;

    @FXML
    private JFXTextField newcourse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = new DBHandler(); 
        connection = handler.getConnection();
        Fillcombo();
        
    }

    @FXML 
    private void updatebuttonAction(ActionEvent e){
        String exist = existingcourse.getValue();
        String newco = newcourse.getText();
           
        try{
            String check = "select * from cms.course where coursename = '"+exist+"'";
            pst = connection.prepareStatement(check);
            ResultSet rs = pst.executeQuery(check);
            if(rs.next()){
                String update = "update cms.course set coursename = '"+ newco +"' where coursename = '"+ exist +"'";
                pst = connection.prepareStatement(update);
                pst.execute(update);
                JOptionPane.showMessageDialog(null, "Course Data Updated");
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/Menu/Course.fxml"));
                    HomepageController.setforeignNode(root);
                } catch (IOException ex) {
                    Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "No existing course found!!");
            }
            pst.close(); 
        }
        catch (HeadlessException | SQLException ex ){
            System.err.println(ex);
        }                                        
    }
    
    
    private void Fillcombo(){
        try{
            existingcourse.setPromptText("Select a course");
            String getcourse = "select * from cms.course";
            pst = connection.prepareStatement(getcourse);
            ResultSet rs = pst.executeQuery(getcourse);
            while(rs.next()){
                String name = rs.getString("coursename");
                existingcourse.getItems().add(name);
            }
       }  
        catch (HeadlessException | SQLException e ){
            System.err.println(e);
        }                                
    }
    
    @FXML
    private void backbutton(ActionEvent e){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Menu/Course.fxml"));
            HomepageController.setforeignNode(root);
        } catch (IOException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
