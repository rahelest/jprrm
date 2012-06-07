package web.commands.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.model.Product;

import middleware.MyLogger;
import middleware.interfaces.FacadeInterface;
import web.commands.Command;
import web.forms.ProductForm;

public class getProductCommand implements Command {

	@Override
	public int execute(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			int productID = Integer.parseInt(req.getParameter("id"));
			Product product = FacadeInterface.getProductById();
			
			ProductForm form = new ProductForm();
			
			form.getDataFromModel(product);
			req.setAttribute("AutoForm", form);
			
			return 1;
			
		} catch (NumberFormatException e) {
			MyLogger.error("getProductCommand: Id is not an integer! " + e.getMessage());
		}
		return 0;
	}
}
