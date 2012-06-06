package backend.service_layer;

import javax.servlet.*;

import backend.data_access_layer.HibernateUtil;

public class HibernateListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent event) {
		HibernateUtil.getSessionFactory();
	}
	
	public void cintextDestroyed(ServletContextEvent event) {
		HibernateUtil.getSessionFactory().close();
	}
	

}
