package edu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
	* Provides common methods for servlets.
	*/ 
abstract class AbstractServlet extends HttpServlet{
		
		protected void forward(HttpServletRequest req, HttpServletResponse res, String path) 
										throws ServletException, IOException{
				req.getServletContext().getRequestDispatcher(path).forward(req, res);
		}
		
		protected void redirect(HttpServletResponse res, String path) throws IOException{
				res.sendRedirect(path);
		}
		

}
