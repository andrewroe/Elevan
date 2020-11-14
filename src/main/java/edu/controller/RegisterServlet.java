/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.controller;

import edu.model.Task;
import edu.model.User;
import edu.data.DBuser;
import edu.data.DBtask;
import edu.data.DBinstructor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// import javax.swing.JOptionPane;

/**
 *
 * @author andrewroe
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends AbstractServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        System.out.println("RegisterServlet: entry, action = " + action);
        log("entry, action = " + action);

        String url = "/register.jsp";
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmpwd = request.getParameter("confirmpwd");
        String admin = request.getParameter("role");
        DBuser dbuser = new DBuser();
        DBtask dbtask = new DBtask();
        DBinstructor dbinstructor = new DBinstructor();

        User user = new User(0, username, email, password);

        String messages[] = new String[4];
        for (String s : messages) {
            s = "";
        }

        boolean ok = true;

        if (username == null || username.isEmpty()) {
            messages[0] = "Name must be entered!";
            ok = false;
        }
        if (email == null || email.isEmpty()) {
            messages[1] = "Email must be entered!";
            ok = false;
        }
        if (password == null || password.isEmpty()) {
            messages[2] = "Password must be entered!";
            ok = false;
        }
        if (confirmpwd == null || confirmpwd.isEmpty()) {
            messages[3] = "Confirm Password must be entered!";
            ok = false;
        }

        if (!ok) {
            //url = "/WEB-INF/register.jsp";
            //user = new User("", "Email", "Password", false);

            request.setAttribute("user", user);
            request.setAttribute("messages", messages);
            forward(request, response, url);
        }

        // if we get here, all fields have been entered
        if (!password.equals(confirmpwd)) {
            messages[3] = "Passwords entered do not match!";
            ok = false;
            request.setAttribute("user", user);
            request.setAttribute("messages", messages);
            forward(request, response, url);
        } else {            
            user = dbuser.getUserByEmail(email);
            if (user != null) {
                messages[1] = "This user is already a registered user!";
                ok = false;
                user = new User(0, "", "Email", "Password");
                request.setAttribute("user", user);
                request.setAttribute("messages", messages);
                forward(request, response, url);
            } else {
                // We can add this new user
                boolean adminrole = false;
                if (admin != null) {
                    adminrole = true;
                }
               
                dbuser.addUser(username, email, password);
                user = dbuser.getUserByEmail(email);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.setAttribute("instructors", dbinstructor.getList());
               
                ArrayList<Task> tasks = null;

                try {
                    //tasks = todoAppService.instance().getTasks(user.getId());
                    tasks = dbtask.getTasks(user.getId());
                } catch (Exception e) {

                }

                request.setAttribute("tasks", tasks);

                url = "/AssignmentManager.jsp";
                forward(request, response, url);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
