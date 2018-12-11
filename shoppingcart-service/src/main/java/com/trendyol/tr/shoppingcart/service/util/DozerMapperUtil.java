package com.trendyol.tr.shoppingcart.service.util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;

import com.trendyol.tr.shoppingcart.schema.Product;
import com.trendyol.tr.shoppingcart.schema.Category;
import com.trendyol.tr.shoppingcart.schema.Campaign;
import com.trendyol.tr.shoppingcart.schema.Coupon;
import com.trendyol.tr.shoppingcart.schema.Cart;
import com.trendyol.tr.shoppingcart.schema.Purchase;


public abstract class DozerMapperUtil {
	
	private static DozerBeanMapper beanMapper;
	
	private DozerMapperUtil() {
	}

	public static void init() {
		beanMapper = (DozerBeanMapper) DozerBeanMapperSingletonWrapper.getInstance();
	}
	
	// PRODUCT
	public static com.trendyol.tr.shoppingcart.persistence.entity.Product mapProduct(Product schema) {
		com.trendyol.tr.shoppingcart.persistence.entity.Product entity = 
				beanMapper.map(schema, com.trendyol.tr.shoppingcart.persistence.entity.Product.class);
		return entity;
	}
	
	public static List<Product> mapProductList(List<com.trendyol.tr.shoppingcart.persistence.entity.Product> entityList) {
		List<Product> schemaList = new ArrayList<Product>();
		for (com.trendyol.tr.shoppingcart.persistence.entity.Product entity : entityList) {
			schemaList.add(mapProduct(entity));
		}
		return schemaList;
	}
	
	public static Product mapProduct(com.trendyol.tr.shoppingcart.persistence.entity.Product entity) {
		return beanMapper.map(entity, Product.class);
	}
	
	// CATEGORY
	public static com.trendyol.tr.shoppingcart.persistence.entity.Category mapCategory(Category schema) {
		com.trendyol.tr.shoppingcart.persistence.entity.Category entity = 
				beanMapper.map(schema, com.trendyol.tr.shoppingcart.persistence.entity.Category.class);
		return entity;
	}
	
	public static List<Category> mapCategoryList(List<com.trendyol.tr.shoppingcart.persistence.entity.Category> entityList) {
		List<Category> schemaList = new ArrayList<Category>();
		for (com.trendyol.tr.shoppingcart.persistence.entity.Category entity : entityList) {
			schemaList.add(mapCategory(entity));
		}
		return schemaList;
	}
	
	public static Category mapCategory(com.trendyol.tr.shoppingcart.persistence.entity.Category entity) {
		return beanMapper.map(entity, Category.class);
	}
	
	// CAMPAIGN
	public static com.trendyol.tr.shoppingcart.persistence.entity.Campaign mapCampaign(Campaign schema) {
		com.trendyol.tr.shoppingcart.persistence.entity.Campaign entity = 
				beanMapper.map(schema, com.trendyol.tr.shoppingcart.persistence.entity.Campaign.class);
		return entity;
	}
	
	public static List<Campaign> mapCampaignList(List<com.trendyol.tr.shoppingcart.persistence.entity.Campaign> entityList) {
		List<Campaign> schemaList = new ArrayList<Campaign>();
		for (com.trendyol.tr.shoppingcart.persistence.entity.Campaign entity : entityList) {
			schemaList.add(mapCampaign(entity));
		}
		return schemaList;
	}
	
	public static Campaign mapCampaign(com.trendyol.tr.shoppingcart.persistence.entity.Campaign entity) {
		return beanMapper.map(entity, Campaign.class);
	}
	
	// COUPON
	public static com.trendyol.tr.shoppingcart.persistence.entity.Coupon mapCoupon(Coupon schema) {
		com.trendyol.tr.shoppingcart.persistence.entity.Coupon entity = 
				beanMapper.map(schema, com.trendyol.tr.shoppingcart.persistence.entity.Coupon.class);
		return entity;
	}
	
	public static List<Coupon> mapCouponList(List<com.trendyol.tr.shoppingcart.persistence.entity.Coupon> entityList) {
		List<Coupon> schemaList = new ArrayList<Coupon>();
		for (com.trendyol.tr.shoppingcart.persistence.entity.Coupon entity : entityList) {
			schemaList.add(mapCoupon(entity));
		}
		return schemaList;
	}
	
	public static Coupon mapCoupon(com.trendyol.tr.shoppingcart.persistence.entity.Coupon entity) {
		return beanMapper.map(entity, Coupon.class);
	}
	
	// CART
	public static com.trendyol.tr.shoppingcart.persistence.entity.Cart mapCart(Cart schema) {
		com.trendyol.tr.shoppingcart.persistence.entity.Cart entity = 
				beanMapper.map(schema, com.trendyol.tr.shoppingcart.persistence.entity.Cart.class);
		return entity;
	}
	
	public static List<Cart> mapCartList(List<com.trendyol.tr.shoppingcart.persistence.entity.Cart> entityList) {
		List<Cart> schemaList = new ArrayList<Cart>();
		for (com.trendyol.tr.shoppingcart.persistence.entity.Cart entity : entityList) {
			schemaList.add(mapCart(entity));
		}
		return schemaList;
	}
	
	public static Cart mapCart(com.trendyol.tr.shoppingcart.persistence.entity.Cart entity) {
		return beanMapper.map(entity, Cart.class);
	}
	
	// PURCHASE
	public static com.trendyol.tr.shoppingcart.persistence.entity.Purchase mapPurchase(Purchase schema) {
		com.trendyol.tr.shoppingcart.persistence.entity.Purchase entity = 
				beanMapper.map(schema, com.trendyol.tr.shoppingcart.persistence.entity.Purchase.class);
		return entity;
	}
	
	public static List<Purchase> mapPurchaseList(List<com.trendyol.tr.shoppingcart.persistence.entity.Purchase> entityList) {
		List<Purchase> schemaList = new ArrayList<Purchase>();
		for (com.trendyol.tr.shoppingcart.persistence.entity.Purchase entity : entityList) {
			schemaList.add(mapPurchase(entity));
		}
		return schemaList;
	}
	
	public static Purchase mapPurchase(com.trendyol.tr.shoppingcart.persistence.entity.Purchase entity) {
		return beanMapper.map(entity, Purchase.class);
	}
	
}
