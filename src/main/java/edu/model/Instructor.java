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
 * This is a bean
 */
public class Instructor implements Serializable {
    
    private int id;
    private String name;
 
   public Instructor() {
        id = 0;
        name = "";
    }

    public Instructor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
   
}
