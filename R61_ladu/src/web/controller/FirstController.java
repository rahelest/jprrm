package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import middleware.MyLogger;

@SuppressWarnings("serial")
public class FirstController extends HttpServlet {
	
	public void init() {
		try {
			super.init();
		} catch (ServletException e) {
			MyLogger.error("FirstController initError: " + e.getMessage());
		}		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		//TODO: logging in and out.
		
		Controller controller = (new ControllerFactory()).create(req, res);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
