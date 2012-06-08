package web.util;

import javax.servlet.http.HttpServletRequest;

import web.forms.ProductForm;

public class ProductRequestParamProcessor {
	
	public static ProductForm getProductAddData(HttpServletRequest req) {
		ProductForm form = new ProductForm();
		
		form.setName(req.getParameter("name"));
		form.setDescription(req.getParameter("description"));
		form.setSale_price(req.getParameter("sale_price"));
		form.setType(req.getParameter("type"));
//		form.setAttributes(req.getParameter("attributes"));
		
		return form;
	}
	
	public static ProductForm getProductChangeData(HttpServletRequest req) {
		
		ProductForm form = new ProductForm();
		
		form.setName(req.getParameter("name"));
		form.setDescription(req.getParameter("description"));
		form.setSale_price(req.getParameter("sale_price"));
		form.setType(req.getParameter("type"));
//		form.setAttributes(req.getParameter("attributes"));
		
		return form;
	}
}
