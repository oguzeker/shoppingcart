package com.trendyol.tr.shoppingcart.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trendyol.tr.shoppingcart.persistence.dao.CampaignDao;
import com.trendyol.tr.shoppingcart.schema.Campaign;
import com.trendyol.tr.shoppingcart.service.util.DozerMapperUtil;

@Stateless
public class CampaignService extends AbstractJpaService {

	private static final Logger logger = LoggerFactory.getLogger(CampaignService.class);

	@Inject
	private CampaignDao galeriDao;
		
	public List<Campaign> getCampaignList() {
		logger.debug("ENTERED...");
		return DozerMapperUtil.mapCampaignList(galeriDao.getCampaignList());
	}
	
}
