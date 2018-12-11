package com.trendyol.tr.shoppingcart.persistence.shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trendyol.tr.shoppingcart.persistence.entity.Campaign;
import com.trendyol.tr.shoppingcart.persistence.entity.Cart;
import com.trendyol.tr.shoppingcart.persistence.entity.Category;
import com.trendyol.tr.shoppingcart.persistence.entity.Coupon;
import com.trendyol.tr.shoppingcart.persistence.entity.Product;
import com.trendyol.tr.shoppingcart.persistence.entity.Purchase;
import com.trendyol.tr.shoppingcart.persistence.util.ShoppingCartUtil;

/**
* The ShoppingCart class implements a rudimentary shopping cart that
* simply calculates and prints relevant prices and costs.
*
* @author  Oguz Erhan Eker
* @version 1.0.0-SNAPSHOT
* @since   2018-12-09 
*/
public class ShoppingCart {
	
	private Cart cart;
	private DeliveryCostCalculator deliveryCostCalculator;
	private Map<Category, Double> discountMapByCategory;
	private double actualAmountBeforeCoupons;
	private double finalAmount;
	private double rawTotal;
	
	/**
	 * This method applies the campaigns first and then applies
	 * the coupons. 
	 * @param cart This is the entity class to perform calculations on.
	 */
	public ShoppingCart(Cart cart) {
		this.cart = cart; 
		this.deliveryCostCalculator = new DeliveryCostCalculator(ShoppingCartUtil.UNIT_DELIVERY_COST, 
				ShoppingCartUtil.UNIT_PRODUCT_COST, ShoppingCartUtil.FIXED_COST);
		
		this.actualAmountBeforeCoupons = getActualAmountBeforeCoupons();
		cart.setActualAmount(actualAmountBeforeCoupons);
		
		this.finalAmount = applyCoupons();
		cart.setFinalAmount(finalAmount);
		
		this.rawTotal = getRawTotal();
	}
	
	public ShoppingCart() { }
	
	public Result printCart() {
		return ShoppingCartUtil.printCart(cart, deliveryCostCalculator, discountMapByCategory, rawTotal);
	}
	
	private double getRawTotal() {
		double rawTotal = 0;
		for (Purchase purchase : this.cart.getPurchases()) {
			rawTotal += purchase.getProduct().getUnitPrice();
		}
		
		return rawTotal;
	}
	
	private double getActualAmountBeforeCoupons() {
		double actualAmountBeforeCoupons = 0;

		discountMapByCategory = applyCampaigns();
		for (Map.Entry<Category, Double> entry : discountMapByCategory.entrySet()) {
			actualAmountBeforeCoupons += entry.getValue();
		}

		return actualAmountBeforeCoupons;
	}
	
	private Map<Category, Double> applyCampaigns() {
		Map<Category, Integer> categoryOccurenceCount = getCategoryOccurenceCounts();
		Map<Category, Double> categoryTotalAmounts = getCategoryTotalAmounts();
		
		List<Double> campaignAmountsOfCategory;
		for (Category category : categoryOccurenceCount.keySet()) {
			
			campaignAmountsOfCategory = new ArrayList<>();
			for (Campaign campaign : category.getCampaigns()) {
				campaignAmountsOfCategory.add(applyCampaign(campaign, categoryOccurenceCount.get(category), categoryTotalAmounts.get(category)) );
			}
			
			categoryTotalAmounts.put(category, Collections.min(campaignAmountsOfCategory));
			
		}
		return categoryTotalAmounts;
	}
	
	private double applyCampaign(Campaign campaign, int occurenceCount, double categoryTotalAmount) {
		if (occurenceCount >= campaign.getMinItemCount()) {
			
			return applyCampaignByType(campaign, categoryTotalAmount);
		} 
		
		return categoryTotalAmount;
	}

	private double applyCampaignByType(Campaign campaign, double categoryTotalAmount) {
		double returnValue = 0;
		
		switch (CampaignType.fromValue(String.valueOf(campaign.getType()))) {
		case PERCENTAGE_DISCOUNT:
			returnValue = applyPercentageDiscount(campaign.getDiscount(), categoryTotalAmount);
			break;
		case AMOUNT_DISCOUNT:
			returnValue = applyAmountDiscount(campaign.getDiscount(), categoryTotalAmount);
			break;
		default:
			break;
		}

		return returnValue; 
	}

	private double applyAmountDiscount(double amount, double totalAmount) {
		return Math.max(0, totalAmount - amount);
	}

	private double applyPercentageDiscount(double percentage, double totalAmount) {
		return totalAmount * ((ShoppingCartUtil.PERCENT - percentage) / ShoppingCartUtil.PERCENT);
	}
	
	private double applyCoupons() {
		double finalAmount = this.cart.getActualAmount();
		
		for (Coupon coupon : this.cart.getCoupons()) {
			finalAmount = applyCoupon(coupon, finalAmount);
		}
		return finalAmount;
	}
	
	private double applyCoupon(Coupon coupon, double finalAmount) {
		double newFinalAmount = finalAmount;
		if (finalAmount >= coupon.getMinCartAmount()) {
			
			newFinalAmount = applyPercentageDiscount(coupon.getDiscount(), newFinalAmount);
			
		}
		
		return newFinalAmount;
	}

	private Map<Category, Integer> getCategoryOccurenceCounts() {
		// Category, occurrenceCount
		Map<Category, Integer> occurenceCounts = new HashMap<>();
		
		List<Product> productsInCart = new ArrayList<>();
		for (Purchase purchase : this.cart.getPurchases()) {
			productsInCart.add(purchase.getProduct());
		}
		
		Category category;
		for (Product product : productsInCart) {
			Integer previousCount = 1;
			category = product.getCategory();
			
			if (occurenceCounts.containsKey(category)) {
				previousCount = occurenceCounts.get(category);
				occurenceCounts.put(category, ++previousCount);
			} else {
				occurenceCounts.put(category, previousCount);
			}
		}
		
		return occurenceCounts;
	}
	
	private Map<Category, Double> getCategoryTotalAmounts() {
		// Category, totalAmount
		Map<Category, Double> totalAmounts = new HashMap<>();
		
		List<Product> productsInCart = new ArrayList<>();
		for (Purchase purchase : this.cart.getPurchases()) {
			productsInCart.add(purchase.getProduct());
		}
		
		Category category;
		for (Product product : productsInCart) {
			double previousTotal = product.getUnitPrice();
			category = product.getCategory();
			
			if (totalAmounts.containsKey(category)) {
				previousTotal = totalAmounts.get(category);
				totalAmounts.put(category, previousTotal + product.getUnitPrice());
			} else {
				totalAmounts.put(category, previousTotal);
			}
		}
		
		return totalAmounts;
	}
	
}
