package web.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewManager {
	
	public void go(String whereToGo, HttpServletRequest req, HttpServletResponse res, ServletContext context) throws ServletException, IOException {
		
		if (whereToGo.equals("show_product")) {
			context.getRequestDispatcher("/product.jsp").forward(req, res);
		}
		
	}

}
