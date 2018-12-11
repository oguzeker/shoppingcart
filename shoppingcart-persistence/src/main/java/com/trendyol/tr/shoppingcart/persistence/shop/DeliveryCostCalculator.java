package com.trendyol.tr.shoppingcart.persistence.shop;

import java.util.HashSet;
import java.util.Set;

import com.trendyol.tr.shoppingcart.persistence.entity.Cart;
import com.trendyol.tr.shoppingcart.persistence.entity.Category;
import com.trendyol.tr.shoppingcart.persistence.entity.Product;
import com.trendyol.tr.shoppingcart.persistence.entity.Purchase;

/**
* This class was implemented to 
* simply calculate the delivery costs.
*
* @author  Oguz Erhan Eker
* @version 1.0.0-SNAPSHOT
* @since   2018-12-09 
*/
public class DeliveryCostCalculator {
	private double unitDeliveryCost; 
	private double unitProductCost; 
	private double fixedCost;
	
	public DeliveryCostCalculator(double unitDeliveryCost, double unitProductCost, double fixedCost) {
		this.unitDeliveryCost = unitDeliveryCost;
		this.unitProductCost = unitProductCost;
		this.fixedCost = fixedCost;
	}
	
	public double calculateFor(Cart cart) {
		int[] distinctCounts = getDistinctCounts(cart);
		
		return unitDeliveryCost * distinctCounts[0] + 
				unitProductCost * distinctCounts[1] +
				fixedCost;
	}
	
	private int[] getDistinctCounts(Cart cart) {
		int[] distinctCounts = new int[2];
		
		Set<Product> distinctProducts = new HashSet<Product>();
		Set<Category> distinctCategories = new HashSet<Category>();
		
		for (Purchase purchase : cart.getPurchases()) {
			if (!distinctProducts.contains(purchase.getProduct())) {
				distinctProducts.add(purchase.getProduct());
			}
			if (!distinctCategories.contains(purchase.getProduct().getCategory())) {
				distinctCategories.add(purchase.getProduct().getCategory());
			}
		}
		
		distinctCounts = new int[]{ distinctCategories.size(), distinctProducts.size() };
		return distinctCounts;
	}
	
}
