/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.controller;

import edu.data.DBinstructor;
import edu.data.DBtask;
import edu.data.DBuser;
import edu.model.Instructor;
import edu.model.Task;
import edu.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrewroe
 */
@WebServlet(name = "RemoveTask", urlPatterns = {"/RemoveTask"})
public class RemoveTask extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        DBuser dbuser = new DBuser();
        DBtask dbtask = new DBtask();
        DBinstructor dbinstructor = new DBinstructor();
        User user = (User) request.getSession().getAttribute("user");
        int userid = user.getId();
        int taskid = Integer.parseInt(request.getParameter("taskid"));
        
        System.out.println("userid = " + userid + "taskid = " + taskid);
        log("userid = " + userid + "taskid = " + taskid);
        
        if (request.getParameter("action") != null) {
            dbtask.remove(taskid);
        }

        //ArrayList<TaskHandlerBean> tasks = todoAppService.instance().getTasks(user.getId());
        ArrayList<Task> tasks = dbtask.getTasks(user.getId());
        request.setAttribute("tasks", tasks);
        request.setAttribute("instructors", dbinstructor.getList());
        request.setAttribute("user", user);
            
        getServletContext().getRequestDispatcher("/AssignmentManager.jsp")
                .forward(request, response);

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

        try {
            processRequest(request, response);
        } catch (Exception e) {
            System.out.println("got a catch ");
            e.printStackTrace(System.out);
        }

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

        try {
            processRequest(request, response);
        } catch (Exception e) {
            System.out.println("got a catch ");
            e.printStackTrace(System.out);
        }
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
