/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.model;

import edu.data.DBinstructor;
import java.io.Serializable;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.data.ConnectionPool;
import edu.data.DBuser;

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

    public ArrayList<Instructor> getUnSelectList(String instructor) {
        DBinstructor dbinstructor = new DBinstructor();
        ArrayList<Instructor> fulllist = dbinstructor.getList();
        ArrayList<Instructor> unlist = new ArrayList<Instructor>();
        
        for (Instructor instr: fulllist) {
            if (!instructor.equals(instr.getName()))
                unlist.add(instr);
        }
        
        return unlist;
    }

}
