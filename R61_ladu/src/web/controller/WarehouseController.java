package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.commands.warehouse.WarehouseCommandFactory;

public class WarehouseController implements Controller {

	@Override
	public String control(HttpServletRequest req, HttpServletResponse res) {
		new WarehouseCommandFactory();
		return null;
	}

}
