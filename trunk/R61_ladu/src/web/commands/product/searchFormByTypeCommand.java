package web.commands.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import web.commands.Command;
import web.control.ProductServiceFactory;
import web.controller.FirstController;
import web.forms.SearchForm;

public class searchFormByTypeCommand implements Command {
	
	private Logger MyLogger = Logger.getLogger(FirstController.class);

	@Override
	public int execute(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			int type = Integer.parseInt(req.getParameter("type"));
			Map<String, String> attributes = ProductServiceFactory.getService().getAttributesOfType(type);
			
			SearchForm form = new SearchForm();
			

			/*
			 * Kõige tähtsam on leida vajalikud atribuudid!
			 */
			
			form.setAttributes(attributes);
			
			req.setAttribute("SearchForm", form);
			
			return 1;
			
		} catch (NumberFormatException e) {
			MyLogger.error("getProductCommand: Id is not an integer! " + e.getMessage());
		} catch (Exception e) {
			MyLogger.error("getProductCommand: " + e.getMessage());
		}
		return 0;
	}
}
