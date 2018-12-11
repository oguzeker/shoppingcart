package com.trendyol.tr.shoppingcart.persistence.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.constraints.NotNull;

import com.trendyol.tr.shoppingcart.persistence.entity.Cart;

@Dao
public class CartDao extends AbstractDao {
	
	public Cart getCart(@NotNull Integer id) {	
		return entityManager.find(Cart.class, id);
	}
	
	public List<Cart> getCartList() {		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cart> query = cb.createQuery(Cart.class);
		
		List<Cart> resultList = entityManager.createQuery(query)
				.getResultList();
		
		return resultList;
	}

}
