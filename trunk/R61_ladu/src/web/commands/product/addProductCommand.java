package web.commands.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import web.commands.Command;
import web.control.ProductServiceFactory;
import web.controller.FirstController;
import web.forms.ProductForm;

public class addProductCommand implements Command {
	
	private Logger MyLogger = Logger.getLogger(FirstController.class);

	@Override
	public int execute(HttpServletRequest req, HttpServletResponse res) {
		
		try {
		
			ProductForm form = new ProductForm();
			
			form.setDescription(req.getParameter("description"));
			form.setName(req.getParameter("name"));
			form.setSale_price(req.getParameter("sale_price"));
			form.setType(req.getParameter("type"));
			
			int type = Integer.parseInt(req.getParameter("type"));
			form.setAttributes(ProductServiceFactory.getService().getAttributesOfType(type));
			
			int result = ProductServiceFactory.getService().createProduct(form);

			req.setAttribute("ProductForm", form);
			
			if (result < 0) {
				//TODO: ei saanud lisatud.. MIKS?
				return -1;
			} else {
				//TODO: edu, suuna vaatamisse?
				return 1;
			}
			
			
		} catch (NumberFormatException e) {
			MyLogger.error("getProductCommand: Id is not an integer! " + e.getMessage());
		} catch (Exception e) {
			MyLogger.error("getProductCommand: " + e.getMessage());
		}
		return 0;
	}

}
