/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.model;

import com.sun.tools.javac.comp.Todo;
import edu.data.ConnectionPool;
import java.lang.System.Logger.Level;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;

/**
 *
 * @author andrewroe
 */
public class todoAppService {

    private static todoAppService service = null;

    public static todoAppService instance() {
        if (service == null) {
            service = new todoAppService();
        }
        return service;
    }

    private todoAppService() {

    }
    
    public ArrayList<TaskHandlerBean> getTasks(int user) throws ClassNotFoundException, SQLException {

        log("entry = ");
        ArrayList<TaskHandlerBean> tasks = new ArrayList<TaskHandlerBean>();
        
        String dbURL = "jdbc:mysql://localhost:3306/MyTasks";
        
        int id;
        String description;
        String instructor;
        String duedate;
        boolean submitted;
        int    userID;

        try {
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            
            Statement statement = con.createStatement();
            String sql = "Select id, description, instructor, duedate, submitted, userID from ToDo where userID = " + user;
            ResultSet results = statement.executeQuery(sql);
            log("query submitted = " + sql);
            while (results.next()) {
           
                id = results.getInt("id");
                description = results.getString("description");
                instructor = results.getString("instructor");
                duedate = results.getString("duedate");
                submitted = results.getBoolean("submitted");
                userID = results.getInt("userID");
                
                tasks.add(new TaskHandlerBean(id, description, instructor, duedate, submitted, userID));
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

        return tasks;
    }

    public TaskHandlerBean getATask(int taskId) throws ClassNotFoundException, SQLException {

        log("entry = ");
        
        TaskHandlerBean task = null;
        String dbURL = "jdbc:mysql://localhost:3306/MyTasks";
        String username = "andrewroe";
        String password = "andysql";
        int id = 0;
        String description;
        String instructor;
        String duedate;
        boolean submitted;
        int userID = 0;
        TaskHandlerBean theTask = null;

        try {
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            
            Statement statement = con.createStatement();
            String sql = "Select id, description, instructor, duedate, submitted, userID from ToDo where id = " + taskId;
            ResultSet results = statement.executeQuery(sql);
            log("query submitted = " + sql);
            if (results.next()) {
                
                id = results.getInt("id");
                description = results.getString("description");
                instructor = results.getString("instructor");
                duedate = results.getString("duedate");
                submitted = results.getBoolean("submitted");
                userID = results.getInt("userID");
               
                theTask = new TaskHandlerBean(id, description, instructor, duedate, submitted, userID);
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

        return theTask;
    }

    public void add(String description, String instructor, String duedate, boolean submitted, int userid)
            throws ClassNotFoundException, SQLException {
        
        log("entry = ");
        
        String dbURL = "jdbc:mysql://localhost:3306/MyTasks";
        
        try {
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
               
            String sql = "insert into ToDo (description, instructor, duedate, submitted, userID)";
            sql +="Values (?,?,?,?,?)";
                
            PreparedStatement pStatement = con.prepareStatement(sql);
         
            pStatement.setString(1, description);
            pStatement.setString(2, instructor);
            pStatement.setString(3, duedate);
            
            pStatement.setBoolean(4, submitted);
            pStatement.setInt(5, userid);
           
            //pStatement.executeUpdate();  Book shows executeUpdate() 
            pStatement.execute();
            log("insert for add submitted = " + sql);

            pStatement.close();            
            pool.freeConnection(con);   // added
        } catch (SQLException e) {
            
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    public void remove(int id) throws ClassNotFoundException, SQLException {
        log("entry = ");
        
        String dbURL = "jdbc:mysql://localhost:3306/MyTasks";
        
        String ID = String.format("%d", id);
        try {
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            
            String sql = "delete from ToDo where id = '" + id + "'";
            log("SQL = " + sql); 
            
            Statement statement = con.createStatement();
            int rowCount = statement.executeUpdate(sql);
            
            statement.executeUpdate(sql);  
           
            log("delete submitted = " + sql);

            statement.close();
            pool.freeConnection(con);   // added
        } catch (SQLException e) {
            
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    public void markSubmitted(int id) throws ClassNotFoundException, SQLException {
       
        log("entry, id = " + id);
        
        String dbURL = "jdbc:mysql://localhost:3306/MyTasks";
        
        try {
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            
            String sql = "update ToDo SET " + "submitted = 1 " + "where id = " + id;
            log("SQL = " + sql);
            
            Statement statement = con.createStatement();
            int rowCount = statement.executeUpdate(sql);
            
            log("task marked submitted = " + sql);

            statement.close();
            pool.freeConnection(con);   // added
        } catch (SQLException e) {
            
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

  
    public void edit(int id, String description, String instructor, String duedate, boolean submitted) {

        log("entry");
        
        String dbURL = "jdbc:mysql://localhost:3306/MyTasks";
        
        try {
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            
            String sql = "update ToDo SET description = ?, instructor = ?, duedate = ?, submitted = ? where id = ?";
                
            PreparedStatement pStatement = con.prepareStatement(sql);
         
            pStatement.setString(1, description);
            pStatement.setString(2, instructor);
            pStatement.setString(3, duedate);
            pStatement.setBoolean(4, submitted);
            pStatement.setInt(5, id);
            
            pStatement.execute();
            pStatement.close();
            
            pool.freeConnection(con);   // added
        } catch (SQLException e) {
            
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

}
