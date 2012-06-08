package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.commands.Command;
import web.commands.EventAndUIStatusFinder;
import web.commands.product.ProductCommandFactory;

/***
 * Product modega seotud info.
 * @author rahrja
 *
 */
public class ProductController implements Controller {

	@Override
	public String control(HttpServletRequest req, HttpServletResponse res) {
		
		ProductCommandFactory pcf = new ProductCommandFactory();
		String event = EventAndUIStatusFinder.find(req, res);
		Command[] commands = pcf.getCommand(event);
		
		for (Command c : commands) {
			int result = c.execute(req, res);
			
			/*
			 * Tagastab koha, kuhu vaja tagasi minna
			 */
			if ((event.equals("addProduct") || event.equals("getproduct"))) {
				return "show_product";
			} else {
				return "start";
			}
		}
		
		
		/*
		 * Mis juhtub n��d, kui command on tehtud v�i mitte
		 * v�i kui event on �ldse mingi lamp. (Returnida)
		 */
		
		
		
		return "error";
	}

}
