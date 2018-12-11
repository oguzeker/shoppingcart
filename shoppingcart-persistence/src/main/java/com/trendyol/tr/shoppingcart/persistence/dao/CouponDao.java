package com.trendyol.tr.shoppingcart.persistence.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.constraints.NotNull;

import com.trendyol.tr.shoppingcart.persistence.entity.Coupon;

@Dao
public class CouponDao extends AbstractDao {
	
	public Coupon getCoupon(@NotNull Integer id) {	
		return entityManager.find(Coupon.class, id);
	}
	
	public List<Coupon> getCouponList() {		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Coupon> query = cb.createQuery(Coupon.class);
		
		List<Coupon> resultList = entityManager.createQuery(query)
				.getResultList();
		
		return resultList;
	}

}
