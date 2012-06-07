package web.commands;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventAndUIStatusFinder {
	
	/*
	 * Leiab sündmused ja staatused.
	 * 
	 * nt show_products_in_catalog
	 * insert_product
	 */

	public Map<String, String> find(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> events = new HashMap<String, String>();
		
		if (request.getParameter("action") != null) {
			events.put("action", request.getParameter("action"));
		}
		return events;
		
	}
}
