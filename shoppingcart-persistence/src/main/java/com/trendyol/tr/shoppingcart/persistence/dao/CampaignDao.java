package com.trendyol.tr.shoppingcart.persistence.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import com.trendyol.tr.shoppingcart.persistence.entity.Campaign;
import com.trendyol.tr.shoppingcart.persistence.entity.Campaign_;

@Dao
public class CampaignDao extends AbstractDao {
	
	public Campaign getCampaign(@NotNull Integer id) {	
		return entityManager.find(Campaign.class, id);
	}
	
	public List<Campaign> getCampaignList() {		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Campaign> query = cb.createQuery(Campaign.class);
		
		List<Campaign> resultList = entityManager.createQuery(query)
				.getResultList();
		
		return resultList;
	}

}
