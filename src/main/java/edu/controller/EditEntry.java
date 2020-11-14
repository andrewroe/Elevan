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
import edu.model.InstructorService;
import edu.model.TaskHandlerBean;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author andrewroe
 */
@WebServlet(name = "EditEntry", urlPatterns = {"/EditEntry"})
public class EditEntry extends HttpServlet {

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

        HttpSession session = request.getSession();
        String url = "/EditTask.jsp";
        
        DBuser dbuser = new DBuser();
        DBtask dbtask = new DBtask();
        DBinstructor dbinstructor = new DBinstructor();
        
        User user = (User) request.getSession().getAttribute("user");
        int userid = user.getId();
        
        String taskid = request.getParameter("taskid");
        String description = request.getParameter("description");
        String instructor = request.getParameter("instructor");
        String duedate = request.getParameter("duedate");
        String submitted = request.getParameter("submitted");

        String message = "";
        String msgDescription = "";
        String msgInstructor = "";
        String msgDuedate = "";
        String msgSubmitted = "";

        TaskHandlerBean task = null;

        String action = request.getParameter("action");

        System.out.println("entry, action: " + action);
        // also into the log file
        log("entry, action: " + action);
        
        System.out.println("userid = " + userid + "taskid = " + taskid);
        log("userid = " + userid + "taskid = " + taskid);

        if (action.equals("enterinfo")) {
            url = "/EditTask.jsp";

            // message = " "; // make non-empty
        } else if (action.equals("editinfo")) {
            
            boolean ok = true;
            int i;
            char ch;
            int indexId = 0;
            boolean done = false;
            
            System.out.println("in the editinfo section, taskid: " + taskid);
            log("in the editinfo section, taskid: " + taskid);

            // validate the parameters
            if (taskid == null || taskid.isEmpty()) {
                message = "Logic Problem, id parm not there!";
            } else {
                indexId = Integer.parseInt(taskid);
            }

            if (description == null || description.isEmpty()) {
                msgDescription = "fill in description";
                message = "Bad or no Input, please correct";
            }

            if (instructor == null || instructor.isEmpty()) {
                msgInstructor = "fill in instructor";
                message = "Bad or no Input, please correct";
            }

            if (submitted == null || submitted.isEmpty()) {
                msgSubmitted = "false/true";
                message = "Bad or no Input, please correct";
            } else {

                if (!(submitted.compareTo("true") == 0 || submitted.compareTo("false") == 0)) {
                    msgSubmitted = "false/true";
                    message = "Bad or no Input, please correct";
                } else {
                    if (submitted.compareTo("true") == 0) {
                        done = true;
                    } else {
                        done = false;
                    }
                }
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
                url = "/EditTask.jsp";
                msgDuedate = "yyyy-mm-dd";
                message = "Bad or no Input, please correct, date Must be yyyy-mm-dd";
            }

            if (message.isEmpty()) {
                System.out.println("in editinfo section and message is empty, taskid: " + taskid);
                log("in editinfo section and message is empty, taskid: " + taskid);

                url = "/AssignmentManager.jsp";
                //todoAppService.instance().edit(indexId, description, instructor, duedate, done);
                dbtask.edit(indexId, description, instructor, duedate, done);
            }

            //task = todoAppService.instance().getATask(Integer.parseInt(id));
        }

        System.out.println("wrap up, userid = " + userid + " taskid = " + taskid);
        log("wrap up, userid = " + userid + " taskid = " + taskid);
                
        ArrayList<TaskHandlerBean> tasks = null;

        try {
            //tasks = todoAppService.instance().getTasks(userid);
            tasks = dbtask.getTasks(userid);
            request.setAttribute("tasks", tasks);
            
            //task = todoAppService.instance().getATask(Integer.parseInt(taskid));
            task = dbtask.getATask(Integer.parseInt(taskid));
            request.setAttribute("task", task);
            //request.setAttribute("instructors", InstructorService.instance().getList());
            request.setAttribute("instructors", dbinstructor.getList());
            request.setAttribute("unlist", InstructorService.instance().getUnSelectList(task.getInstructor()));
        } catch (Exception e) {
            System.out.println("got a catch ");
            e.printStackTrace(System.out);
        }

        request.setAttribute("user", user);
        request.setAttribute("taskid", taskid);  
        request.setAttribute("message", message);
        request.setAttribute("msgDescription", msgDescription);
        request.setAttribute("msgInstructor", msgInstructor);
        request.setAttribute("msgDuedate", msgDuedate);
        request.setAttribute("msgSubmitted", msgSubmitted);

        getServletContext().getRequestDispatcher(url)
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
