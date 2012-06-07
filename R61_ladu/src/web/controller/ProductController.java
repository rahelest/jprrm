package web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.commands.Command;
import web.commands.EventAndUIStatusFinder;
import web.commands.product.ProductCommandFactory;

public class ProductController implements Controller {

	@Override
	public String control(HttpServletRequest req, HttpServletResponse res) {
		
		ProductCommandFactory pcf = new ProductCommandFactory();
		Map<String, String> event = EventAndUIStatusFinder.find(req, res);
		Command[] commands = pcf.getCommand(event);
		
		for (Command c : commands) {
			int result = c.execute(req, res);
			if (result == 1 && event.containsKey("id")) {
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
