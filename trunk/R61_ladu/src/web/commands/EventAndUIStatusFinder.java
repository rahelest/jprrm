package web.commands;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import middleware.MyLogger;

public class EventAndUIStatusFinder {
	
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

	public static String find(HttpServletRequest request, HttpServletResponse response) {
		String mode = request.getParameter("mode");
		String action = request.getParameter("action");
		String event = action;
		
		try {
			if (mode.equals("product")) {
				
				if (action.equals("getProductAddForm")) {

				} else if (action.equals("addProduct")) {
					
				} else if (action.equals("getProduct")) {
					
				} else if (action.equals("changeProduct")) {
					
				} else if (action.equals("viewProducts")) {
					
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
			}
			
			
			
		} catch (Exception e) {
			MyLogger.error("Fault at EventAndUIStatusFinder: " + e.getMessage());
		}
		return event;		
	}
}
