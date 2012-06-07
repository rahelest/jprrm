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
		 * Mis juhtub n��d, kui command on tehtud v�i mitte
		 * v�i kui event on �ldse mingi lamp. (Returnida)
		 */
		
		if (result.equals("something")) {
			return "go there";
		}
		
		return null;
	}

}
