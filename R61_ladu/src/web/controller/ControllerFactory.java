package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import middleware.MyLogger;

/***
 * Tuvastab mode ja saadab edasi vastavasse controllerisse.
 * @author rahrja
 *
 */
public class ControllerFactory {
	
	public Controller create (HttpServletRequest req, HttpServletResponse res) {
		String mode = req.getParameter("mode");
		
		if ("product".equals(mode)) {
			return new ProductController();
			
		} else if ("warehouse".equals(mode)) {
			return new WarehouseController();
			
		} else {
			MyLogger.error("ControllerFactory: no such mode: " + mode); 
			return null;
		}
	}

}
