/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.data;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author andrewroe
 */
public class ConnectionPool {
    
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    private static boolean driverRegistered = false;
    
    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/MyTasks");
        }
        catch (NamingException e) {
            System.out.println(e);
            e.printStackTrace(System.out);
        }
        
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            driverRegistered = true;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }
    
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        }
        catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace(System.out);
            return null;
        }
    }
    
    public void freeConnection(Connection c) {
        try {
            c.close();
        }
        catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace(System.out);
        }
    }
}
