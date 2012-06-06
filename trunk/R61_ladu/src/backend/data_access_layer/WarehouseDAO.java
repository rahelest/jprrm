package backend.data_access_layer;

import java.util.List;

import middleware.interfaces.DAOInterface;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import db.DbConnection;
import backend.model.Product;

public class WarehouseDAO implements DAOInterface {
	private Product[] productArray;
	private Product oneProduct;
	private List<Product> productList;
	private static Logger MyLogger = Logger.getLogger(WarehouseDAO.class);
	
	public List<Product> getAutod_fromDB() {

		productList = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			productList = session.createQuery("from Auto as a").list();

		} catch (Exception ex) {
			MyLogger.error("");
		} finally {
			session.close();
		}
		return productList;
	}
}
