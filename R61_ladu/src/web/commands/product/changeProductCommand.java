package web.commands.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import web.commands.Command;
import web.control.ProductServiceFactory;
import web.controller.FirstController;
import web.forms.ProductForm;

public class changeProductCommand implements Command {
	
	private Logger MyLogger = Logger.getLogger(FirstController.class);

	@Override
	public int execute(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			int type = Integer.parseInt(req.getParameter("type"));
			
			ProductForm form = new ProductForm();
			
			form.setDescription(req.getParameter("decription"));
			form.setName(req.getParameter("name"));
			form.setType(req.getParameter("type"));
			form.setSale_price(req.getParameter("sale_price"));
			form.setId(req.getParameter("id"));

			
			Map<String, String> attributes = ProductServiceFactory.getService().getAttributesOfType(type);
			for(String s: attributes.keySet()) {
				attributes.put(s, req.getParameter(s));
			}
			form.setAttributes(attributes);
			
			int result = ProductServiceFactory.getService().updateProduct(form);
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
