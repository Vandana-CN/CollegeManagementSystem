/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Department;

import DBConnection.DBHandler;
import Menu.CourseController;
import Menu.HomepageController;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
import java.io.IOException;
import static java.lang.Character.isUpperCase;
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
 * @author Vandana
 */
public class AdddepartmentController implements Initializable {
    
     @FXML
    private JFXTextField departmentname;

    @FXML
    private JFXTextField departmenthod;
    
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
    private void addbuttonEvent(ActionEvent e){
        String dept = departmentname.getText();
        String hod = departmenthod.getText();
        String did = getdid();
        
        //Code to check the length of the department name and department code
        if(dept.length()>50 || dept.length()<10)
        {
            JOptionPane.showMessageDialog(null,"Department Name should be between 10 to 50 characters");
           departmentname.setText(null);
        }
        
        //Code to find duplicate elements
        else{
        try{
            String check = "select departmentname from cms.department where departmentname ='"+dept+"'";
            pst = connection.prepareStatement(check);
            ResultSet rs = pst.executeQuery(check);
            if(rs.next() == true)
            {
                JOptionPane.showMessageDialog(null,"Duplicate Department cannot be added please renter a valid name");
                departmentname.setText(null);
                departmenthod.setText(null);
            }
            System.out.println("here");
             //Code to add department
            String insert = "insert into cms.department values ('"+dept+"','"+did+"','"+hod+"')";
            pst = connection.prepareStatement(insert);
            pst.execute(insert);
            JOptionPane.showMessageDialog(null,"Department '"+dept+"' added");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Menu/Department.fxml"));
                HomepageController.setforeignNode(root);
            } catch (IOException ex) {
                Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        catch (HeadlessException | SQLException ex ){
            System.err.println(ex);
        }                              
    }                                        
 }
    
    public String getdid(){
        String dname = departmentname.getText();
        char ch[] = dname.toCharArray();
        String did = "";
        for(int i = 0;i<ch.length;i++){
            if(isUpperCase(ch[i])){
                did = did + ch[i];
            }
        }
        
        if(did.length()<3)
            did = dname.substring(0, 3);
        return did.toUpperCase();   
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
