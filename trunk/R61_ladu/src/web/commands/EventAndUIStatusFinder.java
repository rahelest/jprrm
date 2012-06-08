package web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import web.controller.FirstController;

public class EventAndUIStatusFinder {
	
	private static Logger MyLogger = Logger.getLogger(FirstController.class);
	
	/*
	 * Leiab sündmused ja staatused, vastavalt 
	 * URLile.
	 * 

	add product VIEW FORM
	add product POST
	view product
	view products of this type
	change product POST
	delete product POST
	
	search for products by type FORM
	search for products by type POST
	
	search for products without type POST (form on püsiv)

	 */

	public static String find(HttpServletRequest req, HttpServletResponse res) {
		String mode = req.getParameter("mode");
		String action = req.getParameter("action");
		if (action == null) action = "";
		String event = action;
		
//		?action=viewProducts&type=9
		
		try {
			if (mode.equals("product")) {
				
				if (action.equals("getProductAddForm")) {

				} else if (action.equals("addProduct")) {
					
				} else if (action.equals("getProduct")) {
					
				} else if (action.equals("changeProduct")) {
					
				} else if (action.equals("viewProducts") && req.getParameter("type") != null) {
					
				} else if (action.equals("deleteProduct")) {
					
				} else if (action.equals("getTypeSearchForm")) {
					
				} else if (action.equals("doTypeSearch")) {
					
				} else if (action.equals("doTypelessSearch")) {
					
				} else {
					event = "undefined";
				}
			} else if (mode.equals("warehouse")) {
				
				if (action.equals("addToWarehouse")) {
					
				} else if (action.equals("removeFromWarehouse")) {
					
				} else if (action.equals("moveBetweenWarehouses")) {
					
				} else {
					event = "undefined";
				}
			} else {
				event = "undefined";
			}
			
			
			
		} catch (Exception e) {
			MyLogger.error("Fault at EventAndUIStatusFinder: " + e.getMessage());
		}
		return event;		
	}
}
