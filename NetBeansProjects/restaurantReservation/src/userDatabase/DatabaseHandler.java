/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author marina
 */
public class DatabaseHandler {
    Connection conn = null;
    private static DatabaseHandler handler = null;
    
    private DatabaseHandler() {
        createConnection();
    }
    
    private void createConnection() {
        try{
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/testdb?user=root&password=password&useSSL=false"
            );
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static DatabaseHandler getInstance(){
        if(handler == null){
            handler = new DatabaseHandler();
        }
        
        return handler;
    }
    public boolean insertUser(String firstName, String lastName, String email, String phone, String date, String time, String guests){
        PreparedStatement preparedStatement = null;
        
        try{
            ResultSet resultSet;
            
            String countEmails = "SELECT COUNT(*) FROM USERS WHERE email = ?";
            
            preparedStatement = conn.prepareStatement(countEmails);
            preparedStatement.setString(1, email);
            
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
                if(resultSet.getInt(1) > 0){
                    return false;
                }
            }
            
            String insertQuery = "INSERT INTO USERS (firstName, lastName, email, phone, date, time, guests) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, date);
            preparedStatement.setString(6, time);
            preparedStatement.setString(7, guests);
            
            int result = preparedStatement.executeUpdate();
            
            return (result == 1);
        }catch(Exception e){
            System.out.println("Insert user error: " + e.getMessage());
        }
        
        return false;
    }
    
    public int checkCredentials(String email){
        String query = "SELECT id FROM USERS WHERE email = ?";
        
        PreparedStatement preparedStatement = null;
        
        try{
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
            
        }catch(Exception e){
            System.out.println("Check credentials error, " + e.getMessage());
        }
        
        return -1;
    }
    
}
