package com.trendyol.tr.shoppingcart.persistence.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.trendyol.tr.shoppingcart.persistence.entity.Cart;
import com.trendyol.tr.shoppingcart.persistence.entity.Category;
import com.trendyol.tr.shoppingcart.persistence.entity.Product;
import com.trendyol.tr.shoppingcart.persistence.entity.Purchase;
import com.trendyol.tr.shoppingcart.persistence.shop.DeliveryCostCalculator;
import com.trendyol.tr.shoppingcart.persistence.shop.Result;

/**
* Utility methods for the ShoppingCart class for
* simply transforming and printing relevant data.
*
* @author  Oguz Erhan Eker
* @version 1.0.0-SNAPSHOT
* @since   2018-12-09 
*/
public final class ShoppingCartUtil {
	
	public static final double PERCENT = 100.0;
	public static final double UNIT_DELIVERY_COST = 10;
	public static final double UNIT_PRODUCT_COST = 8.25;
	public static final double FIXED_COST = 2.99;
	public static final DecimalFormat FORMATTER = new DecimalFormat("#0.00");
	public static final String FORMAT_OUTER = "%-40s%s%n";
	public static final String FORMAT_INNER = "%n%-15s%s%-15s%s%-15s%s%-15s";
	
	/**
	 * Utility classes should not have a public constructor.
	 * Added a private constructor in order to hide the implicit public one.
	 * Therefore, preventing instantiation.
	 */
	private ShoppingCartUtil() {
	}
	
	public static Result printCart(Cart cart, DeliveryCostCalculator deliveryCostCalculator, 
			Map<Category, Double> discountMapByCategory, double rawTotal) {
		Result result = new Result();
		
		deliveryCostCalculator = new DeliveryCostCalculator(UNIT_DELIVERY_COST, UNIT_PRODUCT_COST, 
				FIXED_COST);
		
		Map<Category, Map<Product, Integer>> transformedCart = transformCart(cart);
		
		double categoryTotal;
		for (Entry<Category, Map<Product, Integer>> categoryEntry : transformedCart.entrySet()) {
			categoryTotal = 0;
			System.out.println("::::: " + categoryEntry.getKey().getTitle() + " :::::");
			
			for (Entry<Product, Integer> productEntry : categoryEntry.getValue().entrySet()) {
				categoryTotal += productEntry.getValue() * productEntry.getKey().getUnitPrice();
				printInner(productEntry.getKey().getTitle(), "Quantity: ", productEntry.getValue(), 
						"Unit-price: ", productEntry.getKey().getUnitPrice(), "Total-price: ", 
						productEntry.getValue() * productEntry.getKey().getUnitPrice());
			}
			
			System.out.println();
			System.out.println();
			double categoryCampaignDiscount = 
					categoryTotal - discountMapByCategory.get(categoryEntry.getKey());
			result.getCampaignDiscounts().add(categoryCampaignDiscount);
			printOuter(">> Campaign discount (by category): ", categoryCampaignDiscount);
			System.out.println();
			System.out.println("----------------------------------------------------------------");
		}
		
		System.out.println();
		calculateTotalCampaignDiscount(result);
		printOuter(">> Campaign discount (TOTAL): ", result.getTotalCampaignDiscount());
		
		double couponDiscountTotal = cart.getActualAmount() - cart.getFinalAmount();
		result.setCouponDiscount(couponDiscountTotal);
		printOuter(">> Coupon discount (TOTAL): ", couponDiscountTotal);
		System.out.println();
		
		result.setRawTotal(rawTotal);
		printOuter("Raw Total: ", rawTotal);
		
		double actualAmount = cart.getActualAmount();
		result.setTotalBeforeCoupons(actualAmount);
		printOuter("Total Before Coupons: ", actualAmount);
		
		double cartFinalAmount = cart.getFinalAmount();
		result.setTotalAfterCoupons(cartFinalAmount);
		printOuter("Total After Coupons: ", cartFinalAmount);
		System.out.println();
		
		double deliveryCost = deliveryCostCalculator.calculateFor(cart);
		result.setDeliveryCost(deliveryCost);
		printOuter("Delivery Cost: ", deliveryCost);
		System.out.println();
		printOuter("UNIT_DELIVERY_COST: ", UNIT_DELIVERY_COST);
		printOuter("UNIT_PRODUCT_COST: ", UNIT_PRODUCT_COST);
		printOuter("FIXED_COST: ", FIXED_COST);
		
		System.out.println("==============================================");
		System.out.println();
		
		return result;
	}

	private static void calculateTotalCampaignDiscount(Result result) {
		for (Double discount : result.getCampaignDiscounts()) {
			result.setTotalCampaignDiscount(result.getTotalCampaignDiscount() + discount);
		}
	}

	/**
	 * Transforms shopping cart contents. 
	 * In this method, element order is important.
	 * So, we are using LinkedHashMap to maintain 
	 * order of insertion in the result. 
	 * @param cart Shopping cart to operate on.
	 * @return Transformed version of the shopping cart.
	 */
	private static Map<Category, Map<Product, Integer>> transformCart(Cart cart) {
		Product product;
		Category category;
		// Product, quantity
		Map<Product, Integer> products = new HashMap<>();
		Map<Category, Map<Product, Integer>> categories = new LinkedHashMap<>();
		for (Purchase purchase : cart.getPurchases()) {
			product = purchase.getProduct();
			category = product.getCategory();
			
			if (categories.containsKey(category)) {
				products = categories.get(category);
			} else {
				products = new HashMap<Product, Integer>();
				categories.put(category, products);
			}
			
			addToProductMap(product, products);
		}
		return categories;
	}
	
	private static void printOuter(Object object1, Object object2) {
		System.out.printf(FORMAT_OUTER, object1, FORMATTER.format(object2));
	}
	
	private static void printInner(Object object1, Object object2, Object object3, Object object4, 
			Object object5, Object object6, Object object7) {
		System.out.printf(FORMAT_INNER, object1, object2, object3, object4, FORMATTER.format(object5), 
				object6, FORMATTER.format(object7));
	}
	
	private static Map<Product, Integer> addToProductMap(Product product, Map<Product, Integer> products) {
		if (!products.containsKey(product)) {
			products.put(product, 1);
		} else {
			products.put(product, products.get(product) + 1);
		}
		
		return products;
	}
	
}
