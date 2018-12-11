package com.trendyol.tr.shoppingcart.persistence.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.constraints.NotNull;

import com.trendyol.tr.shoppingcart.persistence.entity.Category;

@Dao
public class CategoryDao extends AbstractDao {
	
	public Category getCategory(@NotNull Integer id) {	
		return entityManager.find(Category.class, id);
	}
	
	public List<Category> getCategoryList() {		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Category> query = cb.createQuery(Category.class);
		
		List<Category> resultList = entityManager.createQuery(query)
				.getResultList();
		
		return resultList;
	}

}
