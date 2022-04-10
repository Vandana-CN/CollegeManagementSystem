/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Course;

import Menu.HomepageController;
import DBConnection.DBHandler;
import Menu.CourseController;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import login.LoginController;

/**
 * FXML Controller class
 *
 * @author Vandana
 */
public class AddcourseController implements Initializable {
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
    @FXML
    private AnchorPane addcourseframe;
  
    @FXML
    private JFXComboBox<String> departmentlist;

    @FXML
    private JFXTextField coursename;

    @FXML
    private JFXButton addbutton;

    @FXML
    private JFXComboBox<String> yearlist;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = new DBHandler(); 
        connection = handler.getConnection();
        yearlist.getItems().addAll("1st Year","2nd Year","3rd Year","4th Year");
        Fillcombo();
}

    @FXML 
    public void addButtonAction(ActionEvent e){
        String dept = departmentlist.getValue();
        String cname = dept + "-" + toUpperCase(coursename.getText());
        int yr = Checkyear();
          
        // Check Course name length and year combobox and department combobox
        if(cname.length()>50 || cname.length()<10){
           
          }
        
            try{
                // Check for Duplicate Elements
                String checkcourse = "select coursename from cms.course where coursename = '"+cname+"'";
                pst = connection.prepareStatement(checkcourse);
                ResultSet rs1 = pst.executeQuery(checkcourse);
                if(rs1.next() == true){
                    
                }
                //
                
                // Calculate Course ID
                String calculateid = "select cid from cms.course where department = '"+dept+"'";
                pst = connection.prepareStatement(calculateid);
                ResultSet rs2 = pst.executeQuery(calculateid);
                int max = yr*1000;
                while(rs2.next()){
                    String temp = rs2.getString("cid"); 
                    int idvalue = Integer.valueOf(temp.substring(3,7));
                    int check = Integer.valueOf(temp.substring(3,4));
                    if(yr == check)
                        max = idvalue;  
                }
                max = max +1;
                String cid = dept + String.valueOf(max);
         
                //Insert into Database
                String insert = "insert into cms.course values ('"+cid+"','"+cname+"','"+dept+"')";
                pst = connection.prepareStatement(insert); 
                pst.execute(insert);
                JOptionPane.showMessageDialog(null,"Course '"+cname+"' added to the database");
                try {
                        Parent root = FXMLLoader.load(getClass().getResource("/Menu/Course.fxml"));
                        HomepageController.setforeignNode(root);
                } catch (IOException ex) {
                        Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
           
            catch (SQLException ex){
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }        
        
    
// Check year
    private int Checkyear()
    {
        int yr;
        if("1st Year".equals(yearlist.getValue()))
            yr = 1 ;
        else if("2nd Year".equals(yearlist.getValue()))
            yr = 2;
        else if("3rd Year".equals(yearlist.getValue()))
            yr = 3;
        else
            yr = 4;

        return yr;
    }
    //
    
    //Fill departmentlist
    private void Fillcombo(){
        departmentlist.setValue("Select a department");
        try{
          String DBQ = "select departmentid from cms.department";
          pst = connection.prepareStatement(DBQ);
          ResultSet rs = pst.executeQuery(DBQ);
          int i =0;
          while(rs.next()){
              //departmentlist.getSelectionModel().select((rs.getString("departmentid")));
              departmentlist.getItems().add(rs.getString("departmentid"));
              i++;
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
