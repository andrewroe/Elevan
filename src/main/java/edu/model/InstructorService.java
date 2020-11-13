/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.model;

import java.io.Serializable;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.data.ConnectionPool;

/**
 *
 * @author andrewroe
 */
public class InstructorService {

    private static InstructorService service = null;
    private static boolean driverRegistered = false;

    public static InstructorService instance() {
        if (service == null) {
            service = new InstructorService();
        }
        return service;
    }

    private InstructorService() {
    }

    public ArrayList<Instructor> getList() {

        log("entry = ");
        ArrayList<Instructor> list = new ArrayList<Instructor>();
       
        String dbURL = "jdbc:mysql://localhost:3306/MyTasks";
        
        int id;
        String name;

        try {
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            
            Statement statement = con.createStatement();
            String sql = "Select id, name from Instructor";
            ResultSet results = statement.executeQuery(sql);
            log("query submitted = " + sql);
            while (results.next()) {
                
                id = results.getInt("id");
                name = results.getString("name");

                list.add(new Instructor(id, name));
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

        return list;
    }

    public ArrayList<Instructor> getUnSelectList(String instructor) {
        ArrayList<Instructor> fulllist = getList();
        ArrayList<Instructor> unlist = new ArrayList<Instructor>();
        
        for (Instructor instr: fulllist) {
            if (!instructor.equals(instr.getName()))
                unlist.add(instr);
        }
        
        return unlist;
    }

}
