/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import login.LoginController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author Soumya Sudipta
 */
public class HomepageController implements Initializable {
    @FXML
    private ImageView image;

    @FXML
    private JFXButton coursebutton;

    @FXML
    private JFXButton departmentbutton;

    @FXML
    private JFXButton facultybutton;

    @FXML
    private JFXButton studentbutton;

    @FXML
    private JFXButton reportbutton;

    @FXML
    private Label username;

    @FXML
    private JFXButton contactbutton;
    
    
    @FXML 
            AnchorPane  holderpane; 
            AnchorPane homeframe;
            static AnchorPane courseframe;

              
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText(LoginController.getusername());
        try {
            homeframe = FXMLLoader.load(getClass().getResource("/Menu/HOME.fxml"));
            setNode(homeframe);
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    @FXML
    private void coursebuttonAction(ActionEvent e){
        try {
            courseframe = FXMLLoader.load(getClass().getResource("/Menu/Course.fxml"));
            setNode(courseframe);
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
    
    @FXML
    private void departmentbuttonAction(ActionEvent e){
        try {
            courseframe = FXMLLoader.load(getClass().getResource("/Menu/Department.fxml"));
            setNode(courseframe);
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     
    @FXML
    private void facultybuttonAction(ActionEvent e){
        try {
            courseframe = FXMLLoader.load(getClass().getResource("/Menu/Faculty.fxml"));
            setNode(courseframe);
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
    
    @FXML
    private void studentbuttonAction(ActionEvent e){
        try {
            courseframe = FXMLLoader.load(getClass().getResource("/Menu/Student.fxml"));
            setNode(courseframe);
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void reportbuttonAction(ActionEvent e){
        try {
            courseframe = FXMLLoader.load(getClass().getResource("/Menu/Report.fxml"));
            setNode(courseframe);
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNode(Node node){
        holderpane.getChildren().clear();
        holderpane.getChildren().add((Node)node);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
     
    @FXML
    private void logoutAction(MouseEvent e){
        holderpane.getScene().getWindow().hide();
        System.exit(0);
    }
     
     public static void setforeignNode(Node node){
        courseframe.getChildren().clear();
        courseframe.getChildren().add(node);
         
        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
     }
     
      public static void getforeignNode(Node node){
        courseframe.getChildren().clear();
        courseframe.getChildren().add(node);
         
        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
     }
}
