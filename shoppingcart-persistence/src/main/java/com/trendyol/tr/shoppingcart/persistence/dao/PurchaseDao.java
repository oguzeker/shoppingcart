package com.trendyol.tr.shoppingcart.persistence.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.constraints.NotNull;

import com.trendyol.tr.shoppingcart.persistence.entity.Purchase;

@Dao
public class PurchaseDao extends AbstractDao {
	
	public Purchase getPurchase(@NotNull Integer id) {	
		return entityManager.find(Purchase.class, id);
	}
	
	public List<Purchase> getPurchaseList() {		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Purchase> query = cb.createQuery(Purchase.class);
		
		List<Purchase> resultList = entityManager.createQuery(query)
				.getResultList();
		
		return resultList;
	}

}
