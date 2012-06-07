package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	
	public String control(HttpServletRequest req, HttpServletResponse res);
}
