/**
 * 
 */
package com.walmart.dao;

/**
 * @author p0p00bj
 *
 */
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.walmart.model.CartItems;

public class CartItemsDao implements CartItemsDaoInterface<CartItems, String>{
	
	private Session currentSession;
	
	private Transaction currentTransaction;

	public CartItemsDao() {
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public void persist(CartItems entity) {
		getCurrentSession().save(entity);
	}

	public void update(CartItems entity) {
		getCurrentSession().update(entity);
	}

	public CartItems findById(String id) {
		CartItems book = (CartItems) getCurrentSession().get(CartItems.class, id);
		return book; 
	}

	public void delete(CartItems entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<CartItems> findAll() {
		List<CartItems> books = (List<CartItems>) getCurrentSession().createQuery("from Cart_Items").list();
		return books;
	}

	public void deleteAll() {
		List<CartItems> entityList = findAll();
		for (CartItems entity : entityList) {
			delete(entity);
		}
	}

}
