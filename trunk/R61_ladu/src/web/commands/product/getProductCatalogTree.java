package web.commands.product;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import web.commands.Command;
import web.control.ProductServiceFactory;
import web.controller.FirstController;
import web.forms.ProductForm;

public class getProductCatalogTree implements Command {
	
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

			
			ArrayList[] tree = ProductServiceFactory.getTree().getCatalogTree();
			
			req.setAttribute("Tree", tree);
			
			if (tree == null) {
				return -1;
			} else {
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
