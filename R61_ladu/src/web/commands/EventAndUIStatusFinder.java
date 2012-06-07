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
	 * nt show_products_in_catalog
	 * insert_product
	 * 
	 * LISAMINE: vali tüüp, lisa.
	 */

	public static Map<String, String> find(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> events = new HashMap<String, String>();
		
		try {if (!request.getParameter("id").equals(null)) {
			events.put("id", request.getParameter("id"));
			//TODO: otsi id järgi toodet
		} else if (!request.getParameter("type").equals(null) && !request.getParameter("action").equals(null)) {
			String action = request.getParameter("action");
			if (action.equals("new")) {
				//TODO: vastava tüübi jaoks uue form
				events.put("new", request.getParameter("type"));
			} else if (action.equals("search")) {
				//TODO: vastava tüübi jaoks otsinguform
				events.put("search", request.getParameter("type"));
			} else {
				//TODO: mingi vale action
				events.put("fault", request.getParameter("id"));
			}
		} else if (request.getParameter("type").equals(null)) {
			//TODO: vastava tootetüübi listi näitamine
			events.put("type", request.getParameter("type"));
		} else {
			//TODO: mingi uus käsk, mida pole veel 
			events.put("fault", request.getParameter("id"));
		}
		
		} catch (Exception e) {
			MyLogger.error("Fault at EventAndUIStatusFinder: " + e.getMessage());
		}
		return events;		
	}
}
