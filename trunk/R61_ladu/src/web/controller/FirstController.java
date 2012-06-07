package web.controller;

import javax.servlet.ServletContext;
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
		
		ServletContext context = getServletConfig().getServletContext();
		
		Controller controller = (new ControllerFactory()).create(req, res);
		String whereToGo = controller.control(req, res);
		try {
			(new ViewManager()).go(whereToGo, req, res, context);
		} catch (Exception e) {
			MyLogger.error("FirstController: " + e.getMessage());
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
