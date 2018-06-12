/**
 * 
 */
package com.walmart.service;

/**
 * @author p0p00bj
 *
 */

import java.util.List;


import com.walmart.dao.CartItemsDao;
import com.walmart.model.CartItems;

public class CartItemsService {
	private static CartItemsDao CartItemsDao;

	public CartItemsService() {
		CartItemsDao = new CartItemsDao();
	}

	public void persist(CartItems entity) {
		CartItemsDao.openCurrentSessionwithTransaction();
		CartItemsDao.persist(entity);
		CartItemsDao.closeCurrentSessionwithTransaction();
	}

	public void update(CartItems entity) {
		CartItemsDao.openCurrentSessionwithTransaction();
		CartItemsDao.update(entity);
		CartItemsDao.closeCurrentSessionwithTransaction();
	}

	public CartItems findById(String id) {
		CartItemsDao.openCurrentSession();
		CartItems cartitems = CartItemsDao.findById(id);
		CartItemsDao.closeCurrentSession();
		return cartitems;
	}

	public void delete(String id) {
		CartItemsDao.openCurrentSessionwithTransaction();
		CartItems cartitems = CartItemsDao.findById(id);
		CartItemsDao.delete(cartitems);
		CartItemsDao.closeCurrentSessionwithTransaction();
	}

	public List<CartItems> findAll() {
		CartItemsDao.openCurrentSession();
		List<CartItems> books = CartItemsDao.findAll();
		CartItemsDao.closeCurrentSession();
		return books;
	}

	public void deleteAll() {
		CartItemsDao.openCurrentSessionwithTransaction();
		CartItemsDao.deleteAll();
		CartItemsDao.closeCurrentSessionwithTransaction();
	}

	public CartItemsDao bookDao() {
		return CartItemsDao;
	}

}
