package web.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


@WebServlet(value = { "/" }, loadOnStartup = 1)
public class FirstController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7936929581445658939L;
	private Logger MyLogger = Logger.getLogger(FirstController.class);

	public void init() {
		try {
			super.init();
			MyLogger.info("Initialization");
		} catch (ServletException e) {
			MyLogger.error("FirstController initError: " + e.getMessage());
		}		
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		//TODO: logging in and out.
		
//		getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(req, res);
		
		ServletContext context = getServletConfig().getServletContext();
		
		Controller controller = (new ControllerFactory()).create(req, res); // tagastab uue tühja controlleri
		String whereToGo = "start";
		if (controller != null) {
			whereToGo = controller.control(req, res); // jooksutab leitud käsud
		}
		//show_product, start ja error, viewProducts
		try {
			(new ViewManager()).go(whereToGo, req, res, context);
		} catch (Exception e) {
			MyLogger.error("FirstController: " + e.getMessage());
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
