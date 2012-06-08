package web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.commands.Command;
import web.commands.EventAndUIStatusFinder;
import web.commands.product.ProductCommandFactory;
import web.commands.warehouse.WarehouseCommandFactory;

public class WarehouseController implements Controller {

	@Override
	public String control(HttpServletRequest req, HttpServletResponse res) {
		WarehouseCommandFactory pcf = new WarehouseCommandFactory();
		Map<String, String> event = EventAndUIStatusFinder.find(req, res);
		Command[] commands = pcf.getCommand(event);
		
		for (Command c : commands) {
			int result = c.execute(req, res);
			if (result == 1 && event.containsKey("id")) {
				return "show_product";
			}
		}
		
		
		/*
		 * Mis juhtub n��d, kui command on tehtud v�i mitte
		 * v�i kui event on �ldse mingi lamp. (Returnida)
		 */
		
		
		
		return null;
	}
}
