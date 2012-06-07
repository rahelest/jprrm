package web.commands.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import middleware.MyLogger;
import backend.model.Product;

import web.commands.Command;
import web.control.ProductServiceFactory;
import web.forms.ProductForm;

public class addProductCommand implements Command {

	@Override
	public int execute(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			int productID = Integer.parseInt(req.getParameter("id"));
			Product product = ProductServiceFactory.getService().getProductById(productID);
			
			ProductForm form = new ProductForm();
			
			form.getDataFromModel(product);
			req.setAttribute("AutoForm", form);
			
			return 1;
			
		} catch (NumberFormatException e) {
			MyLogger.error("getProductCommand: Id is not an integer! " + e.getMessage());
		} catch (Exception e) {
			MyLogger.error("getProductCommand: " + e.getMessage());
		}
		return 0;
	}

}
