package com.trendyol.tr.shoppingcart.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trendyol.tr.shoppingcart.persistence.dao.CouponDao;
import com.trendyol.tr.shoppingcart.schema.Coupon;
import com.trendyol.tr.shoppingcart.service.util.DozerMapperUtil;

@Stateless
public class CouponService extends AbstractJpaService {

	private static final Logger logger = LoggerFactory.getLogger(CouponService.class);

	@Inject
	private CouponDao galeriDao;
		
	public List<Coupon> getCouponList() {
		logger.debug("ENTERED...");
		return DozerMapperUtil.mapCouponList(galeriDao.getCouponList());
	}
	
}
