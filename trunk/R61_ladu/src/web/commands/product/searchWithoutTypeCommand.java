package web.commands.product;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import backend.model.Product;
import web.commands.Command;
import web.control.ProductServiceFactory;
import web.controller.FirstController;
import web.forms.ProductForm;
import web.forms.SearchForm;

public class searchWithoutTypeCommand implements Command {
	
	private Logger MyLogger = Logger.getLogger(FirstController.class);

	@Override
	public int execute(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			SearchForm form = new SearchForm();
			
			form.setAmount_in_warehouse(req.getParameter("amount_in_warehouse"));
			form.setDescription(req.getParameter("decription"));
			form.setManufacturer(req.getParameter("manufacturer"));
			form.setManufacturers_code(req.getParameter("manufacturers_code"));
			form.setName(req.getParameter("name"));
			form.setSale_price_end(req.getParameter("sale_price_end"));
			form.setSale_price_start(req.getParameter("sale_price_start"));
			form.setType(req.getParameter("type"));
			form.setWarehouse_price_end(req.getParameter("warehouse_price_end"));
			form.setWarehouse_price_start(req.getParameter("warehouse_price_start"));

			form.setAttributes(null);
			
			List<Product> products = ProductServiceFactory.getService().searchProducts(form);
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
