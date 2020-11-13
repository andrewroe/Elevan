/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.controller;


import edu.model.InstructorService;
import edu.model.todoAppService;
import edu.model.TaskHandlerBean;
import edu.model.User;
import edu.model.UserSingleton;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andrewroe
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends AbstractServlet {

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

        log("entry, action = " + action);

        String rememberMe = request.getParameter("rememberMe");

        log("rememberMe = " + rememberMe);

        HttpSession session = request.getSession();
        String url = "/whosThere.jsp";  // !!!!   
        User user = null;
        boolean forwarded = false;

       
        // the action has to be loginUser
        String emailaddr = null;
        // Cart cart = null;   !!! Address

        // check for known user
        user = UserSingleton.instance().fetchKnownUser(request);

        log("validate input");

        String username = "";
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        log("password = " + password);

        String messages[] = new String[2];
        for (String s : messages) {
            s = "";
        }

        boolean ok = true;

        if (email == null || email.isEmpty()) {
            messages[1] = "Email must be entered!";
            ok = false;
        }
        if (password == null || password.isEmpty()) {
            messages[1] = "Password must be entered!";
            ok = false;
        }

        if (!ok) {
            url = "/login.jsp";
            
            user = new User(0, "", "Email", "Password");

            request.setAttribute("user", user);
            request.setAttribute("messages", messages);
            forward(request, response, url);
            
        } else {
            // if we get here, all fields have been entered
            // Potential new login, check for existing user
            user = UserSingleton.instance().getUserByEmail(email);

            // request.setAttribute("products", ProductService.instance().getAllProducts());

            if (user == null) {
                ok = false;
                messages[0] = "User not found!";
                log("user not found! ");
                url = "/login.jsp";
                user = new User(0, "", "Email", "Password");

                request.setAttribute("user", user);
                request.setAttribute("messages", messages);
                forward(request, response, url);
            } else if (!user.getPassword().equals(password)) { // need to verify password
                ok = false;
                messages[1] = "Password invalid!";
                log("password mismatch! " + password);
                url = "/login.jsp";
                user = new User(0, "", email, password);

                request.setAttribute("user", user);
                request.setAttribute("messages", messages);
                forward(request, response, url);
                
            } else {
                // found the user
                session.setAttribute("user", user);

                // add emailCookie if rememberMe set
                if (ok && rememberMe != null) {
                    Cookie c = new Cookie("emailCookie", email);
                    c.setMaxAge(60 * 60 * 24 * 30); // 30 days
                    c.setPath("/"); // allow access by entire app
                    response.addCookie(c);
                }
                
                url = "/AssignmentManager.jsp";
                ArrayList<TaskHandlerBean> tasks = null;
                
                try {
                    tasks = todoAppService.instance().getTasks(user.getId());
                    request.setAttribute("instructors", InstructorService.instance().getList());
                    //request.setAttribute("unlist", InstructorService.instance().getUnSelectList(task.getInstructor()));
                }
                catch (Exception e) {
                    System.out.println("got a catch ");
                    e.printStackTrace(System.out);
                }
                
                session.setAttribute("tasks", tasks);  // maybe remove
                request.setAttribute("tasks", tasks);
                
                log("exiting with user = " + user);
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
