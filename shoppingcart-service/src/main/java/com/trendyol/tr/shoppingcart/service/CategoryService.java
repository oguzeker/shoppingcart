package com.trendyol.tr.shoppingcart.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trendyol.tr.shoppingcart.persistence.dao.CategoryDao;
import com.trendyol.tr.shoppingcart.schema.Category;
import com.trendyol.tr.shoppingcart.service.util.DozerMapperUtil;

@Stateless
public class CategoryService extends AbstractJpaService {

	private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

	@Inject
	private CategoryDao galeriDao;
		
	public List<Category> getCategoryList() {
		logger.debug("ENTERED...");
		return DozerMapperUtil.mapCategoryList(galeriDao.getCategoryList());
	}
	
}
