package com.trendyol.tr.shoppingcart.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trendyol.tr.shoppingcart.persistence.dao.CartDao;
import com.trendyol.tr.shoppingcart.schema.Cart;
import com.trendyol.tr.shoppingcart.service.util.DozerMapperUtil;

@Stateless
public class CartService extends AbstractJpaService {

	private static final Logger logger = LoggerFactory.getLogger(CartService.class);

	@Inject
	private CartDao galeriDao;
		
	public List<Cart> getCartList() {
		logger.debug("ENTERED...");
		return DozerMapperUtil.mapCartList(galeriDao.getCartList());
	}

	public Cart getCart(int cartId){
		return DozerMapperUtil.mapCart(galeriDao.getCart(cartId));
	}
	
}
