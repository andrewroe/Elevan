/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.model;

import edu.data.ConnectionPool;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author andrewroe
 */
public class UserSingleton {

    private static UserSingleton single_instance = null;

    public static UserSingleton instance() {
        if (single_instance == null) {
            single_instance = new UserSingleton();
        }
        return single_instance;
    }

    // private constructor - restricted to this class only
    private UserSingleton() {
        // do nothing.
    }

    
    public void addUser(String name, String email, String pword) {
        
        String dbURL = "jdbc:mysql://localhost:3306/MyTasks";
        
        try {
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            
            String sql = "insert into User (name, email, password)";
            sql +="Values (?,?,?)";
                
            PreparedStatement pStatement = con.prepareStatement(sql);
         
            pStatement.setString(1, name);
            pStatement.setString(2, email);
            pStatement.setString(3, pword);
              
            pStatement.execute();
            pStatement.close();
            pool.freeConnection(con);   // added
        } catch (SQLException e) {
            //Logger.getLogger(todoAppService.class.getName().log(Level.SEVERE, null,e);
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

   
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
           
        String dbURL = "jdbc:mysql://localhost:3306/MyTasks";
        
        int id;
        String name;
        String email;
        String pword;

        try {
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            
            Statement statement = con.createStatement();
     
            String sql = "Select id, name, email, password from User";
           
            ResultSet results = statement.executeQuery(sql);
            log("query submitted = " + sql);
            while (results.next()) {
             
                id = results.getInt("id");
                name = results.getString("name");
                email = results.getString("email");
                pword = results.getString("password");
                
                users.add(new User(id, name, email, pword));
            }
            
            results.close();
            statement.close();   
            pool.freeConnection(con);   
        } catch (SQLException e) {
            //Logger.getLogger(todoAppService.class.getName().log(Level.SEVERE, null,e);
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }   
        return users;
    }

    public User getUserByEmail(String email) {
        String emailLC = email.toLowerCase();
        
        String dbURL = "jdbc:mysql://localhost:3306/MyTasks";
        
        int id;
        String name;
        String dbemail;
        String pword;
        
        User user = null;

        try {
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            
            Statement statement = con.createStatement();
          
            String sql = "Select id, name, email, password from User where email = " 
                    + "'" + email + "'";
           
            ResultSet results = statement.executeQuery(sql);
            
            if (results.next()) {
               
                id = results.getInt("id");
                name = results.getString("name");
                dbemail = results.getString("email");
                pword = results.getString("password");
                
                user = new User(id, name, dbemail, pword);
            }
            
            else {
                statement = con.createStatement();
                sql = "Select id, name, email, password from User where email = " 
                        + "'" + emailLC + "'";
           
                results = statement.executeQuery(sql);
            
                if (results.next()) {
               
                    id = results.getInt("id");
                    name = results.getString("name");
                    dbemail = results.getString("email");
                    pword = results.getString("password");
                
                    user = new User(id, name, dbemail, pword);
                }
            }
            
            results.close();
            statement.close();
            
            pool.freeConnection(con);   // added
        } catch (SQLException e) {
            //Logger.getLogger(todoAppService.class.getName().log(Level.SEVERE, null,e);
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        
        return user;
           
    }

    public String getUserPassword(User user) {
        return user.getPassword();
    }

    public User fetchKnownUser(HttpServletRequest request) {
        User user = null;
        String emailaddr = null;
        Cookie[] cookies = request.getCookies();

        log("UserSingleton: fetchKnownUser() search for user with emailaddr = " + emailaddr);

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("emailCookie")) {
                    emailaddr = cookie.getValue();

                    user = getUserByEmail(emailaddr);
                    if (user != null) {
                        log("UserSinglton: fetchKnownUser() user found, emailaddr " + emailaddr);
                        return user;
                    }
                }
            }
        }

        return null;
    }

}
