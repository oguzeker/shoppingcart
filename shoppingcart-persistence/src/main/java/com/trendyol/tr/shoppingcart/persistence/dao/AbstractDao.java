/**
 * 
 */
package com.trendyol.tr.shoppingcart.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is the base class for all DAOs.
 * 
 * @author Oguz Erhan Eker
 */
public abstract class AbstractDao {

	@PersistenceContext
	protected EntityManager entityManager;
	
}
