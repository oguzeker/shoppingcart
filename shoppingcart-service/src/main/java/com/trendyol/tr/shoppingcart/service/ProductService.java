package com.trendyol.tr.shoppingcart.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trendyol.tr.shoppingcart.persistence.dao.ProductDao;
import com.trendyol.tr.shoppingcart.schema.Product;
import com.trendyol.tr.shoppingcart.service.util.DozerMapperUtil;

@Stateless
public class ProductService extends AbstractJpaService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Inject
	private ProductDao galeriDao;
		
	public List<Product> getProductList() {
		logger.debug("ENTERED...");
		return DozerMapperUtil.mapProductList(galeriDao.getProductList());
	}
	
}
