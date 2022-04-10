/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faculty;

import DBConnection.DBHandler;
import Menu.CourseController;
import Menu.HomepageController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javax.swing.JTextField;

/**
 * FXML Controller class
 *
 * @author Soumya Sudipta
 */
public class AddfacultyController implements Initializable {
    
    
    @FXML
    private JFXTextField fname;

    @FXML
    private JFXTextField mname;

    @FXML
    private JFXTextField lname;

    @FXML
    private JFXTextArea education;

    @FXML
    private JFXDatePicker dob;

    @FXML
    private JFXComboBox<String> gender;

    @FXML
    private JFXComboBox<String> department;

    @FXML
    private JFXTextArea address;

    @FXML
    private JFXTextField city;

    @FXML
    private JFXTextField state;

    @FXML
    private JFXTextField pincode;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXComboBox<?> cc;

    @FXML
    private JFXTextField emailid;

    @FXML
    private JFXDatePicker doj;

    @FXML
    private JFXButton addbutton;
    
    @FXML
    private JFXComboBox<String> bloodgroup;
    
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = new DBHandler(); 
        connection = handler.getConnection();
        gender.getItems().addAll("Male","Female");
        getdepartment();
        bloodgroup.getItems().addAll("A+","B+","O+","AB+","A-","B-","O-","AB-");
    }
    
    @FXML
    private void addbuttonAction(ActionEvent e){
        String nm = fname.getText() +" "+ mname.getText() +" "+ lname.getText();
        String ed = education.getText();
        String add = address.getText();
        String bd =  dob.getValue().toString();
        String dp = department.getSelectionModel().getSelectedItem();
        String gn = gender.getSelectionModel().getSelectedItem();
        String ph = phone.getText();
        String emid = emailid.getText();
        String joining = doj.getValue().toString();
        String bg = bloodgroup.getSelectionModel().getSelectedItem();
        String ct = city.getText();
        String st = state.getText();
        String zc = pincode.getText();
        int yr = Integer.valueOf(joining.substring(3, 4));
        String pass ="CMS12345";

        try{
            String search = "select id from cms.faculty where department = '"+dp+"'";
            pst = connection.prepareStatement(search);
            ResultSet rs = pst.executeQuery(search);
          
            int max = yr*1000;
            while(rs.next())
            {
                int temp = Integer.valueOf(rs.getString("id").substring(0,1));
                if(yr == temp){
                max = max+1;
            }    
        }
            max = max+1;
            int id = max;
          
            String insert = "insert into cms.faculty values('"+id+"','"+nm+"','"+bd+"','"+gn+"','"+dp+"','"+bg+"','"+ed+"','"+add+"','"+ct+"','"+st+"','"+zc+"','"+ph+"','"+emid+"','"+joining+"')";
            String insert2 = "insert into cms.login values('"+id+"','"+pass+"','"+0+"')";
            pst = connection.prepareStatement(insert);
            pst.execute(insert);
            pst = connection.prepareStatement(insert2);
            pst.execute(insert2);
            JOptionPane.showMessageDialog(null, "Successfully inserted the data of '"+ nm +"'");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Menu/Faculty.fxml"));
                HomepageController.setforeignNode(root);
            } catch (IOException ex) {
                Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch (HeadlessException | SQLException ex ){
            System.err.println(ex);
            JOptionPane.showMessageDialog(null, "Please check the above details");
        }                                       
    }
     
    private void getdepartment(){
        department.setValue("Select a department");
        try{
            String getdep = "select departmentid from cms.department";
            pst = connection.prepareStatement(getdep);
            ResultSet rs = pst.executeQuery(getdep);
            while(rs.next()){
                department.getItems().add(rs.getString("departmentid"));
            }
        }   
        catch (HeadlessException | SQLException e ){
            System.err.println(e);
        }                          
    }
    
    @FXML
    private void backbutton(ActionEvent e){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Menu/Faculty.fxml"));
            HomepageController.setforeignNode(root);
        } catch (IOException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

