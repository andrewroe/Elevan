/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.model;

import java.io.Serializable;

/**
 *
 * @author andrewroe
 */
public class Task implements Serializable {

    private int id;
    private String description;
    private String instructor;
    private String duedate;
    private boolean submitted; 
    private int userID;

    public Task() {
        id = 0;
        description = "";
        instructor = "";
        duedate = "";
        submitted = false;
        userID = 0;
    }

    public Task(int id, String description,
            String instructor, String duedate, boolean submitted, int userID) {
        this.id = id;
        this.description = description;
        this.instructor = instructor;
        this.duedate = duedate;
        this.submitted = submitted;
        this.userID = userID;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the instructor
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * @param instructor the instructor to set
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * @return the duedate
     */
    public String getDuedate() {
        return duedate;
    }

    /**
     * @param duedate the duedate to set
     */
    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }
    
    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5d", id));
        sb.append(String.format("%-30s", description));
        sb.append(String.format("%-20s", instructor));
        sb.append(String.format("%-20s", duedate));
        sb.append(String.format("%-5s", submitted));
        sb.append(String.format("%-5d", userID));
        return sb.toString();
    }

}
