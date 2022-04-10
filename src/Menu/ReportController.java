/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Soumya Sudipta
 */
public class ReportController implements Initializable {
     @FXML
    private AnchorPane reoprtframe;

    @FXML
    private JFXButton course;

    @FXML
    private JFXButton student;

    @FXML
    private JFXButton department;

    @FXML
    private JFXButton faculty;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    public void viewCourse(ActionEvent e){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Report/coursereport.fxml"));
            HomepageController.setforeignNode(root);
        } catch (IOException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    public void viewDepartment(ActionEvent e){
       try {
            Parent root = FXMLLoader.load(getClass().getResource("/Report/departmentreport.fxml"));
            HomepageController.setforeignNode(root);
        } catch (IOException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
     @FXML
    public void viewStudent(ActionEvent e){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Report/studentreport.fxml"));
            HomepageController.setforeignNode(root);
        } catch (IOException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    public void viewFaculty(ActionEvent e){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Report/facultyreport.fxml"));
            HomepageController.setforeignNode(root);
        } catch (IOException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
