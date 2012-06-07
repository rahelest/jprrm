package backend.service_layer;

import javax.servlet.*;

import backend.data_access_layer.HibernateUtil;

public class HibernateListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}/*
	
	public void contextInitialized(ServletContextEvent event) {
		HibernateUtil.getSessionFactory();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getSessionFactory().close();
		
	}
	
*/
}
