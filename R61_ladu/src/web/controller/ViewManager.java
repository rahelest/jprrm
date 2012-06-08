package web.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewManager {
	
	public void go(String whereToGo, HttpServletRequest req, HttpServletResponse res, ServletContext context) throws ServletException, IOException {
		
		if (whereToGo.equals("show_product")) {
			context.getRequestDispatcher("/WEB-INF/JSP/product.jsp").forward(req, res);
		} else if (whereToGo.equals("start")) {
			context.getRequestDispatcher("/WEB-INF/JSP/index.jsp").forward(req, res);
		} else {
			context.getRequestDispatcher("/WEB-INF/JSP/error.jsp").forward(req, res);
		}
		
	}

}
