/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Course;

import DBConnection.DBHandler;
import Menu.CourseController;
import Menu.HomepageController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import login.LoginController;

/**
 * FXML Controller class
 *
 * @author Soumya Sudipta
 */
public class ViewcourseController implements Initializable {
   
    @FXML
        JFXButton back;
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
    
    @FXML
    private TableView<getcoursedata> coursetable;
    
    @FXML
    private TableColumn<getcoursedata, String> id;

    @FXML
    private TableColumn<getcoursedata, String> coursename;

    @FXML
    private TableColumn<getcoursedata, String> department;
    
    private ObservableList<getcoursedata>data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = new DBHandler(); 
        connection = handler.getConnection();
        
        data = FXCollections.observableArrayList();
        
        try{
            
                String insert = "select *from cms.course";
                pst = connection.prepareStatement(insert);
                ResultSet rs = pst.executeQuery(insert);
                
                while(rs.next())
                {
                    data.add(new getcoursedata(rs.getString("cid"),rs.getString("coursename"),rs.getString("department")));
                }
        
        } catch (SQLException ex){
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
        id.setCellValueFactory(new PropertyValueFactory<>("cid"));
        coursename.setCellValueFactory(new PropertyValueFactory<>("course"));
        department.setCellValueFactory(new PropertyValueFactory<>("department"));
        
        coursetable.setItems(null);
        coursetable.setItems(data);
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
