package com.trendyol.tr.shoppingcart.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trendyol.tr.shoppingcart.persistence.dao.PurchaseDao;
import com.trendyol.tr.shoppingcart.schema.Purchase;
import com.trendyol.tr.shoppingcart.service.util.DozerMapperUtil;

@Stateless
public class PurchaseService extends AbstractJpaService {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseService.class);

	@Inject
	private PurchaseDao galeriDao;
		
	public List<Purchase> getPurchaseList() {
		logger.debug("ENTERED...");
		return DozerMapperUtil.mapPurchaseList(galeriDao.getPurchaseList());
	}
	
}
