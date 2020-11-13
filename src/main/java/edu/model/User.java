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
public class User implements Serializable {
    
    private int id;
    private String username;
    private String email;
    private String password;
    
    public User() {
        id = 0;
        username = "";
        email = "";
        password = "";
    }
    
    public User(int id, String username, String email, String password) {
        
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
