package com.trendyol.tr.shoppingcart.persistence.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.trendyol.tr.shoppingcart.persistence.entity.Campaign;
import com.trendyol.tr.shoppingcart.persistence.entity.Cart;
import com.trendyol.tr.shoppingcart.persistence.entity.Category;
import com.trendyol.tr.shoppingcart.persistence.entity.Coupon;
import com.trendyol.tr.shoppingcart.persistence.entity.Product;
import com.trendyol.tr.shoppingcart.persistence.entity.Purchase;

/**
 * ShoppingCart test class base.
 * @author Oguz Erhan Eker
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class ShoppingCartTestBase {
	
	protected enum CartType { CAMPAIGN_ELIGIBLE_ONLY, COUPON_ELIGIBLE_ONLY, ALL } 
	
	protected Cart all;
	protected Cart campaignEligibleOnly;
	protected Cart couponEligibleOnly;
	
	protected ShoppingCart suspect;
	
	public abstract void setup();
	
	protected void populateCarts() {
		populateCart(CartType.CAMPAIGN_ELIGIBLE_ONLY);
		populateCart(CartType.COUPON_ELIGIBLE_ONLY);
		populateCart(CartType.ALL);
	}
	
	protected void populateCart(CartType cartType) {
		switch (cartType) {
		case CAMPAIGN_ELIGIBLE_ONLY:
			populateCampaignEligibleOnlyCart();
			break;
		case COUPON_ELIGIBLE_ONLY:
			populateCouponEligibleOnlyCart();
			break;
		case ALL:
			populateAllCart();
			break;
		default:
			break;
		}
	}
	
	protected void populateCouponEligibleOnlyCart() {
		List<Coupon> coupons = new ArrayList<>();
		List<Purchase> purchases = new ArrayList<>();
		
		Campaign campaign1 = createCampaign();
		campaign1.setMinItemCount(77);
		campaign1.setDiscount(20);
		campaign1.setType(1);
		Campaign campaign2 = createCampaign();
		campaign2.setDiscount(30);
		campaign2.setMinItemCount(99);
		campaign2.setType(2);
		
		Category category1 = createCategory();
		category1.addCampaign(campaign1);
		category1.addCampaign(campaign2);
		Category category2 = createCategory();
		category2.addCampaign(campaign2);
		
		Coupon coupon1 = createCoupon();
		coupon1.setDiscount(15);
		Coupon coupon2 = createCoupon();
		coupon2.setDiscount(24);
		Coupon coupon3 = createCoupon();
		coupon3.setDiscount(29);
		coupon3.setMinCartAmount(750);
		coupons.add(coupon1);
		coupons.add(coupon2);
		coupons.add(coupon3);
		
		Product product1 = createProduct();
		product1.setCategory(category1);
		Product product2 = createProduct();
		product2.setCategory(category1);
		Product product3 = createProduct();
		product3.setCategory(category2);

		Purchase purchase1 = createPurchase();
		purchase1.setProduct(product1);
		Purchase purchase2 = createPurchase();
		purchase2.setProduct(product2);
		Purchase purchase3 = createPurchase();
		purchase3.setProduct(product3);
		Purchase purchase4 = createPurchase();
		purchase4.setProduct(product1);
		
		purchases.add(purchase1);
		purchases.add(purchase2);
		purchases.add(purchase3);
		purchases.add(purchase4);
		
		couponEligibleOnly = createCart(purchases, coupons);
	}
	
	protected void populateCampaignEligibleOnlyCart() {
		List<Coupon> coupons = new ArrayList<>();
		List<Purchase> purchases = new ArrayList<>();
		
		Campaign campaign1 = createCampaign();
		campaign1.setDiscount(18);
		campaign1.setType(1);
		Campaign campaign2 = createCampaign();
		campaign2.setDiscount(37);
		campaign2.setType(2);
		
		Category category1 = createCategory();
		category1.addCampaign(campaign1);
		category1.addCampaign(campaign2);
		Category category2 = createCategory();
		category2.addCampaign(campaign2);
		
		Coupon coupon1 = createCoupon();
		coupon1.setDiscount(15);
		coupon1.setMinCartAmount(5750);
		Coupon coupon2 = createCoupon();
		coupon2.setDiscount(24);
		coupon2.setMinCartAmount(5750);
		Coupon coupon3 = createCoupon();
		coupon3.setDiscount(29);
		coupon3.setMinCartAmount(5750);
		coupons.add(coupon1);
		coupons.add(coupon2);
		coupons.add(coupon3);
		
		Product product1 = createProduct();
		product1.setCategory(category1);
		Product product2 = createProduct();
		product2.setCategory(category2);
		Product product3 = createProduct();
		product3.setCategory(category2);
		
		Purchase purchase1 = createPurchase();
		purchase1.setProduct(product1);
		Purchase purchase2 = createPurchase();
		purchase2.setProduct(product2);
		Purchase purchase3 = createPurchase();
		purchase3.setProduct(product3);
		Purchase purchase4 = createPurchase();
		purchase4.setProduct(product1);
		
		purchases.add(purchase1);
		purchases.add(purchase2);
		purchases.add(purchase3);
		purchases.add(purchase4);
		
		campaignEligibleOnly = createCart(purchases, coupons);
	}
	
	protected void populateAllCart() {
		List<Coupon> coupons = new ArrayList<>();
		List<Purchase> purchases = new ArrayList<>();
		
		Campaign campaign1 = createCampaign();
		campaign1.setDiscount(20);
		campaign1.setType(1);
		Campaign campaign2 = createCampaign();
		campaign2.setDiscount(30);
		campaign2.setType(2);
		
		Category category1 = createCategory();
		category1.addCampaign(campaign1);
		category1.addCampaign(campaign2);
		Category category2 = createCategory();
		category2.addCampaign(campaign2);
		
		Coupon coupon1 = createCoupon();
		coupon1.setDiscount(15);
		Coupon coupon2 = createCoupon();
		coupon2.setDiscount(24);
		Coupon coupon3 = createCoupon();
		coupon3.setDiscount(29);
		coupon3.setMinCartAmount(750);
		coupons.add(coupon1);
		coupons.add(coupon2);
		coupons.add(coupon3);
		
		Product product1 = createProduct();
		product1.setCategory(category1);
		Product product2 = createProduct();
		product2.setCategory(category1);
		Product product3 = createProduct();
		product3.setCategory(category2);

		Purchase purchase1 = createPurchase();
		purchase1.setProduct(product1);
		Purchase purchase2 = createPurchase();
		purchase2.setProduct(product2);
		Purchase purchase3 = createPurchase();
		purchase3.setProduct(product3);
		Purchase purchase4 = createPurchase();
		purchase4.setProduct(product1);
		
		purchases.add(purchase1);
		purchases.add(purchase2);
		purchases.add(purchase3);
		purchases.add(purchase4);
		
		all = createCart(purchases, coupons);
	}
	
	protected Product createProduct() {
		Product product = new Product();
		product.setProductId(getRandomIdWithinRange(1,99));
		product.setTitle("Product-" + product.getProductId());
		product.setUnitPrice(200);
		
		return product;
	}
	
	protected Category createCategory() {
		Category category = new Category();
		category.setCategoryId(getRandomIdWithinRange(1,99));
		category.setCampaigns(new ArrayList<Campaign>());
		category.setTitle("Category-" + category.getCategoryId());
		
		return category;
	}
	
	protected Campaign createCampaign() {
		Campaign campaign = new Campaign();
		campaign.setCampaignId(getRandomIdWithinRange(1,99));
		campaign.setType(1);
		campaign.setDiscount(campaign.getCampaignId());
		campaign.setMinItemCount(2);
		
		return campaign;
	}

	protected Cart createCart(List<Purchase> purchases, 
			List<Coupon> coupons) {
		Cart cart = new Cart();
		cart.setCartId(getRandomIdWithinRange(1,99));
		cart.setPurchases(purchases);
		cart.setCoupons(coupons);
		
		return cart;
	}
	
	protected Coupon createCoupon() {
		Coupon coupon = new Coupon();
		coupon.setCouponId(getRandomIdWithinRange(1,99));
		coupon.setMinCartAmount(25);
		
		return coupon;
	}
	
	protected Purchase createPurchase() {
		Purchase purchase = new Purchase();
		
		return purchase;
	}
	
	protected int getRandomIdWithinRange(int min, int max) {
		Random r = new Random();
		return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
	}
	
}
