/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.model;

import edu.data.ConnectionPool;
import edu.data.DBuser;
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
