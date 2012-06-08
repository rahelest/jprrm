package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.commands.Command;
import web.commands.EventAndUIStatusFinder;
import web.commands.warehouse.WarehouseCommandFactory;

public class WarehouseController implements Controller {

	@Override
	public String control(HttpServletRequest req, HttpServletResponse res) {
		WarehouseCommandFactory pcf = new WarehouseCommandFactory();
		String event = EventAndUIStatusFinder.find(req, res);
		Command[] commands = pcf.getCommand(event);
		
		for (Command c : commands) {
			int result = c.execute(req, res);
			if (result == 1 && event.equals("id")) {
				return "show_product";
			}
		}
		
		
		/*
		 * Mis juhtub nüüd, kui command on tehtud või mitte
		 * või kui event on üldse mingi lamp. (Returnida)
		 */
		
		
		
		return null;
	}
}
