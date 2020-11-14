/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.model;

import edu.data.DBuser;
import java.io.Serializable;
import static java.rmi.server.LogStream.log;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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

    public User fetchKnownUser(HttpServletRequest request) {
        User user = null;
        String emailaddr = null;
        Cookie[] cookies = request.getCookies();
        DBuser dbuser = new DBuser();

        log("UserSingleton: fetchKnownUser() search for user with emailaddr = " + emailaddr);

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("emailCookie")) {
                    emailaddr = cookie.getValue();

                    //user = getUserByEmail(emailaddr);
                    user = dbuser.getUserByEmail(emailaddr);
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
