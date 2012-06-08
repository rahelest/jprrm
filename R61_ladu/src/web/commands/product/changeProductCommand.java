package web.commands.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import middleware.MyLogger;
import backend.model.Product;
import web.commands.Command;
import web.control.ProductServiceFactory;
import web.forms.ProductForm;
import web.forms.SearchForm;

public class changeProductCommand implements Command {

	@Override
	public int execute(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			
			ProductForm form = new ProductForm();
			
			form.setDescription(req.getParameter("decription"));
			form.setName(req.getParameter("name"));
			form.setType(req.getParameter("type"));
			form.setSale_price(req.getParameter("sale_price"));

			
			Map<String, String> attributes = ProductServiceFactory.getService().getAttributesOfType(type);
			for(String s: attributes.keySet()) {
				attributes.put(s, req.getParameter(s));
			}
			form.setAttributes(attributes);
			
			int result = ProductServiceFactory.getService().updateProduct(form);
			List<ProductForm> productForms = new ArrayList<>();
			
			for (Product p : products) {
				ProductForm pForm = new ProductForm();
				pForm.getDataFromModel(p);
				productForms.add(pForm);
			}
			
			req.setAttribute("ResultList", productForms);
			
			return 1;
			
		} catch (NumberFormatException e) {
			MyLogger.error("getProductCommand: Id is not an integer! " + e.getMessage());
		} catch (Exception e) {
			MyLogger.error("getProductCommand: " + e.getMessage());
		}
		return 0;
	}
}
