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
		String event = EventAndUIStatusFinder.find(req, res); //?action=viewProducts&type=9
		Command[] commands = pcf.getCommand(event);
		
		for (Command c : commands) {
			int result = c.execute(req, res);
			
			/*
			 * Tagastab koha, kuhu vaja tagasi minna
			 */
			if ((event.equals("addProduct") || event.equals("getProduct"))) {
				return "show_product";
			} else if (event.equals("viewProducts"))  {
				return event;
			} else {
				return "start";
			}
		}
		
		
		/*
		 * Mis juhtub nüüd, kui command on tehtud või mitte
		 * või kui event on üldse mingi lamp. (Returnida)
		 */
		
		
		
		return "error";
	}

}
