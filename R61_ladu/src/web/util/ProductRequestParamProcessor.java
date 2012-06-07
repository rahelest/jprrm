package web.util;

import javax.servlet.http.HttpServletRequest;

import web.forms.AddProductForm;
import web.forms.ChangeProductForm;

public class ProductRequestParamProcessor {
	
	public AddProductForm getProductAddData(HttpServletRequest req) {
		AddProductForm form = new AddProductForm();
		
		form.setName(req.getParameter("name"));
		form.setDescription(req.getParameter("description"));
		form.setSale_price(req.getParameter("sale_price"));
		form.setType(req.getParameter("type"));
		form.setAttributes(req.getParameter("attributes"));
		
		return form;
	}
	
	public ChangeProductForm getProductChangeData(HttpServletRequest req) {
		
		ChangeProductForm form = new ChangeProductForm();
		
		form.setName(req.getParameter("name"));
		form.setDescription(req.getParameter("description"));
		form.setSale_price(req.getParameter("sale_price"));
		form.setType(req.getParameter("type"));
		form.setAttributes(req.getParameter("attributes"));
		
		return form;
		
	}
}
