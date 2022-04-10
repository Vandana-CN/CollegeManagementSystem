/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Department;

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
public class UpdatedepartmentController implements Initializable {
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
    @FXML
    private JFXComboBox<String> existingdepartment;

    @FXML
    private JFXTextField newdepartment;
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
    private void updatebuttonEvent(ActionEvent e){
        String exist = (String) existingdepartment.getSelectionModel().getSelectedItem();
        String newdep = newdepartment.getText();
        
        if(newdep.length()>50 || newdep.length()<10)
            JOptionPane.showMessageDialog(null, "HOD name should contain characters between 10 to 50");
        try{
            String check = "select * from cms.department where departmentname = '"+exist+"'";
            pst = connection.prepareStatement(check);
            ResultSet rs = pst.executeQuery(check);
            if(rs.next()==true){
                String insert = "update cms.department set departmenthod = '"+ newdep +"' where departmentname = '"+ exist +"'";
                pst = connection.prepareStatement(insert);
                pst.execute(insert);
                JOptionPane.showMessageDialog(null, "Department Data Updated");
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/Menu/Department.fxml"));
                    HomepageController.setforeignNode(root);
                } catch (IOException ex) {
                    Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           }
           catch (HeadlessException | SQLException ex ){
          System.err.println(e);
        }  
    }
    
    private void Fillcombo(){
        try{
            String check = "select * from cms.department";
            pst = connection.prepareStatement(check);
            ResultSet rs = pst.executeQuery(check);
            rs = pst.executeQuery(check);
          
            while(rs.next()){
                String name = rs.getString("departmentname");
                existingdepartment.getItems().add(name);
            }
        }
        catch (HeadlessException | SQLException e ){
            System.err.println(e);
        }                                
    }                                            

    @FXML
    private void backbutton(ActionEvent e){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Menu/Department.fxml"));
            HomepageController.setforeignNode(root);
        } catch (IOException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
