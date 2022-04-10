/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student;

import DBConnection.DBHandler;
import Menu.CourseController;
import Menu.HomepageController;
import com.jfoenix.controls.JFXButton;
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
public class ViewstudentController implements Initializable {
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
    @FXML
    JFXButton back;
    
    @FXML
    private TableView<getstudentdata> studenttable;

    @FXML
    private TableColumn<getstudentdata, String> id;

    @FXML
    private TableColumn<getstudentdata, String> name;

    @FXML
    private TableColumn<getstudentdata, String> fathersname;

    @FXML
    private TableColumn<getstudentdata, String> dob;

    @FXML
    private TableColumn<getstudentdata, String> gender;

    @FXML
    private TableColumn<getstudentdata, String> bloodgroup;

    @FXML
    private TableColumn<getstudentdata, String> department;

    @FXML
    private TableColumn<getstudentdata, String> address;

    @FXML
    private TableColumn<getstudentdata, String> city;

    @FXML
    private TableColumn<getstudentdata, String> state;

    @FXML
    private TableColumn<getstudentdata, String> zipcode;

    @FXML
    private TableColumn<getstudentdata, String> phone;

    @FXML
    private TableColumn<getstudentdata, String> email;

    @FXML
    private TableColumn<getstudentdata, String> doj;
    
    private ObservableList<getstudentdata>data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = new DBHandler(); 
        connection = handler.getConnection();
        
        data = FXCollections.observableArrayList();
        
        data = FXCollections.observableArrayList();
        
        try{
            
                String insert = "select *from cms.student";
                pst = connection.prepareStatement(insert);
                ResultSet rs = pst.executeQuery(insert);
                
                while(rs.next())
                {
                    data.add(new getstudentdata(rs.getString("id"),rs.getString("name"),rs.getString("fathersname"),rs.getString("dob"),rs.getString("gender"),rs.getString("bloodgroup"),rs.getString("department"),rs.getString("address"),rs.getString("city"),rs.getString("state"),rs.getString("zipcode"),rs.getString("phone"),rs.getString("email"),rs.getString("doj")));
                }
        
        } catch (SQLException ex){
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        fathersname.setCellValueFactory(new PropertyValueFactory<>("fathersname"));
        dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        bloodgroup.setCellValueFactory(new PropertyValueFactory<>("bloodgroup"));
        department.setCellValueFactory(new PropertyValueFactory<>("department"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        doj.setCellValueFactory(new PropertyValueFactory<>("doj"));
        
        studenttable.setItems(null);
        studenttable.setItems(data);
    }    
    
    @FXML
    private void backbutton(ActionEvent e){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Menu/Student.fxml"));
            HomepageController.setforeignNode(root);
        } catch (IOException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
