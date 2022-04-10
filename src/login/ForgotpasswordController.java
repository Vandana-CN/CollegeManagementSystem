/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import DBConnection.DBHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.fxml.Initializable;
import java.util.*;
import javafx.scene.input.MouseEvent;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import static sun.security.jgss.GSSUtil.login;



/**
 * FXML Controller class
 *
 * @author Soumya Sudipta
 */
public class ForgotpasswordController implements Initializable {
    
    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton resetbutton;
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = new DBHandler();
    }    
    
    @FXML
    public void forgotPasswordAction(ActionEvent e) throws IOException, AddressException, MessagingException{
        connection = handler.getConnection();
        try{
                String QUERY = "select * from cms.login where id = '"+username.getText()+"'";
                pst = connection.prepareStatement(QUERY);
                ResultSet rs = pst.executeQuery(QUERY);
                if(rs.next() == true){
                   try{ 
                    String to = "sudipta.sarangi@vitap.ac.in";
                    String user = "soumya.sarangi.10@gmail.com";
                    String from = "soumya.sarangi.10@gmail.com";
                    String pass = "Soumya-1";
                    String host = "smtp.gmail.com";
                    String subject = "Password";
                    String messageText = "Your password is : ";
                    boolean sessionDebug = false;
                    Properties props = System.getProperties();
                    
                    props.put("mail.smtp.starttls.enable","true");
                    props.put("mail.smtp.host",host);
                    props.put("mail.smtp.port","587");
                    props.put("mail.smtp.auth","true");
                    props.put("mail.smtp.starttls.required","true");
                    
                    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                    
                    Session mailSession = Session.getDefaultInstance(props,null);
                    mailSession.setDebug(sessionDebug);
                    Message msg = new MimeMessage(mailSession);
                    InternetAddress[] address = {new InternetAddress(to)};
                    msg.setRecipients(Message.RecipientType.TO, address);
                    msg.setSubject(subject);
                    msg.setSentDate(new Date());
                    msg.setText(messageText);
                    
                    Transport transport = mailSession.getTransport("smtp");
                    transport.connect(host,user,pass);
                    transport.sendMessage(msg, msg.getAllRecipients());
                    transport.close();
                    System.out.println("Sent");
                   }
                   catch(Exception ex){
                       System.out.println(ex);
                   }  
           }
                else
                {
                    System.out.println(username.getText().length());
                    
                }
            
        } catch (SQLException ex){
                   Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void mouseClicked(MouseEvent  e){
        resetbutton.getScene().getWindow().hide();
    }
}
