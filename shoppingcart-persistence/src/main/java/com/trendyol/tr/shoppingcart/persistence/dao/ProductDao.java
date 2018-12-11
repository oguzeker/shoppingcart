package com.trendyol.tr.shoppingcart.persistence.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.constraints.NotNull;

import com.trendyol.tr.shoppingcart.persistence.entity.Product;

@Dao
public class ProductDao extends AbstractDao {
	
	public Product getProduct(@NotNull Integer id) {	
		return entityManager.find(Product.class, id);
	}
	
	public List<Product> getProductList() {		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = cb.createQuery(Product.class);
		
		List<Product> resultList = entityManager.createQuery(query)
				.getResultList();
		
		return resultList;
	}

}
