/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.controller;

import edu.model.TaskHandlerBean;
import edu.model.User;
import edu.data.DBuser;
import edu.data.DBtask;
import edu.data.DBinstructor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.tools.java.ClassNotFound;

/**
 *
 * @author andrewroe
 */
@WebServlet(name = "AddTask", urlPatterns = {"/AddTask"})
public class AddTask extends HttpServlet {

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

        if (request.getParameter("add") != null) {
            String description = request.getParameter("description");
            String instructor = request.getParameter("instructor");
            String duedate = request.getParameter("duedate");
            
            DBuser dbuser = new DBuser();
            DBtask dbtask = new DBtask();
            DBinstructor dbinstructor = new DBinstructor();
            
            User user = (User) request.getSession().getAttribute("user");
            int id = Integer.parseInt(request.getParameter("id"));
            
            String url = "/AssignmentManger.jsp";
            String message = "";
            String msgDescription = "";
            String msgInstructor = "";
            String msgDuedate = "";

            boolean ok = true;
            int i;
            char ch;

            // validate the parameters
            if (description == null || description.isEmpty()) {
                msgDescription = "fill in description";
                message = "Bad or no Input, please correct";
            }

            if (instructor == null || instructor.isEmpty()) {
                msgInstructor = "fill in instructor";
                message = "Bad or no Input, please correct";
            }

            if (duedate == null || duedate.isEmpty()) {
                msgDuedate = "yyyy-mm-dd";
                message = "Bad or no Input, please correct, date Must be yyyy-mm-dd";
            } else {
                if (duedate.length() < 10) {
                    ok = false;
                }

                for (i = 0; (i < duedate.length()) & (ok == true); i++) {
                    ch = duedate.charAt(i);
                    if (!Character.isDigit(ch) && !(ch == '-')) {
                        ok = false;
                    }
                }
            }

            if (!ok) {
                msgDuedate = "yyyy-mm-dd";
                message = "Bad or no Input, please correct, date Must be yyyy-mm-dd";
            }

            if (message.isEmpty()) {
                //todoAppService.instance().add(description, instructor, duedate, false, id);
                dbtask.add(description, instructor, duedate, false, id);
            }

            ArrayList<TaskHandlerBean> tasks = null;
                
                try {
                    //tasks = todoAppService.instance().getTasks(id);
                    tasks = dbtask.getTasks(id);
                }
                catch (Exception e) {
                    System.out.println("got a catch ");
                    e.printStackTrace(System.out);
                }
                
            request.setAttribute("user", user);
            request.setAttribute("tasks", tasks);
            request.setAttribute("id", id);
            //request.setAttribute("instructors", InstructorService.instance().getList());
            request.setAttribute("instructors", dbinstructor.getList());
                     
            request.setAttribute("message", message);
            request.setAttribute("msgDescription", msgDescription);
            request.setAttribute("msgInstructor", msgInstructor);
            request.setAttribute("msgDuedate", msgDuedate);
        }

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
        } catch (ClassNotFoundException e) {
            //printStackTrace(); 
            System.out.println("class not found ");
        
        } catch (SQLException e) {
            //printStackTrace(); 
            System.out.println("SQL error ");
        
        } catch (Exception e) {
            //printStackTrace(); 
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
            //printStackTrace();
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
