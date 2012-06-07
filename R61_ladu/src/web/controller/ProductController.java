package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.commands.Command;
import web.commands.EventAndUIStatusFinder;
import web.commands.product.ProductCommandFactory;

public class ProductController implements Controller {

	@Override
	public String control(HttpServletRequest req, HttpServletResponse res) {
		
		ProductCommandFactory pcf = new ProductCommandFactory();
		Command command = pcf.getCommand(EventAndUIStatusFinder.find(req, res));
		
		String result = command.execute();
		/*
		 * Mis juhtub nüüd, kui command on tehtud või mitte
		 * või kui event on üldse mingi lamp. (Returnida)
		 */
		
		if (result.equals("something")) {
			return "go there";
		}
		
		return null;
	}

}
