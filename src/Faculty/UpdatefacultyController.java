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
import static java.lang.String.format;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.beans.binding.Bindings.format;
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
public class UpdatefacultyController implements Initializable {
    
    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextArea education;

    @FXML
    private JFXTextField dob;

    @FXML
    private JFXTextField gender;

    @FXML
    private JFXTextField department;

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
    private JFXTextField emailid;

    @FXML
    private JFXTextField doj;

    @FXML
    private JFXButton addbutton;
    
    @FXML
    private JFXTextField bloodgroup;
    
    
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
    }    
    
    @FXML
    private void findbuttonAction(ActionEvent e){
        try{
            String find = "select * from cms.faculty where id = '"+id.getText()+"'";
            pst = connection.prepareStatement(find);
            ResultSet rs = pst.executeQuery(find);
            
            if(rs.next())
            {
                name.setText(rs.getString("name"));
                education.setText(rs.getString("education"));
                address.setText(rs.getString("address"));
                dob.setText(rs.getString("dob"));
                department.setText(rs.getString("department"));
                gender.setText(rs.getString("gender"));
                phone.setText(rs.getString("phone"));
                emailid.setText(rs.getString("email"));
                doj.setText(rs.getString("doj"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                pincode.setText(rs.getString("zipcode"));
                bloodgroup.setText(rs.getString("bloodgroup"));
            }
        }
        catch (HeadlessException | SQLException ex ){
            System.err.println(ex);
        }                                       
    }
    
    @FXML
    private void updatebuttonAction(ActionEvent e){
        String ed = education.getText();
        String add = address.getText();
        String ph = phone.getText();
        String emid = emailid.getText();
        String ct = city.getText();
        String st = state.getText();
        String zc = pincode.getText();

        try{
            String update = "update cms.faculty set education = '"+ed+"',address = '"+add+"', phone = '"+add+"', phone = '"+ph+"', email = '"+emid+"', city = '"+ct+"' , state = '"+st+"' , zipcode = '"+zc+"' where id = '"+id.getText()+"'";
            pst = connection.prepareStatement(update);
            pst.execute(update);
            JOptionPane.showMessageDialog(null, "Successfully inserted the data of '"+ name.getText() +"'");
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
