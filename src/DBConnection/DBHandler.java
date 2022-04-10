/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Soumya Sudipta
 */
public class DBHandler extends Configs{
    Connection dbconnection;
    
    public Connection getConnection()
    {
        
        String connectionString = "jdbc:mysql://" + Configs.dbhost + ":" + Configs.dbport + "/" + Configs.dbname ;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        try{
            dbconnection = DriverManager.getConnection(connectionString,Configs.dbuser,Configs.dbpass);
        }catch(SQLException e){
        }
        
        return dbconnection;
    }
}
