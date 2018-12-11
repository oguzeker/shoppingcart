package com.trendyol.tr.shoppingcart.persistence.shop;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * ShoppingCart test class.
 * @author Oguz Erhan Eker
 */
@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartTest extends ShoppingCartTestBase {
	
	double d;
	
	@Before
	public void setup() {
		populateCarts();
	}
	
	@Test
	public void test_CampaignEligibleOnly() {
		suspect = new ShoppingCart(this.campaignEligibleOnly);
		Result result = suspect.printCart();
		
		assertTrue((Double)result.getCampaignDiscounts().get(0) == 72.0);
		assertTrue((Double)result.getCampaignDiscounts().get(1) == 37.0);
		assertTrue(result.getCouponDiscount() == 0.0);
		assertTrue(result.getRawTotal() == 800.0);
		assertTrue(result.getTotalBeforeCoupons() == 691.0);
		assertTrue(result.getTotalAfterCoupons() == 691.0);
		
		assertTrue(result.getTotalBeforeCoupons() == result.getTotalAfterCoupons());
		
		assertTrue(result.getDeliveryCost() == 47.74);
		assertTrue(result.getTotalCampaignDiscount() + result.getTotalBeforeCoupons() == result.getRawTotal());
	}
	
	@Test
	public void test_CouponEligibleOnly() {
		suspect = new ShoppingCart(this.couponEligibleOnly);
		Result result = suspect.printCart();
		
		assertTrue((Double)result.getCampaignDiscounts().get(0) == 0.0);
		assertTrue((Double)result.getCampaignDiscounts().get(1) == 0.0);
		assertTrue(result.getTotalCampaignDiscount() == 0.0);
		
		assertTrue(Double.compare((Double)result.getCampaignDiscounts().get(0).doubleValue(), (Double)result.getCampaignDiscounts().get(1).doubleValue()) == 0);
		
		assertTrue(result.getCouponDiscount() == 283.20000000000005);
		assertTrue(result.getRawTotal() == 800.0);
		assertTrue(result.getTotalBeforeCoupons() == 800.0);
		assertTrue(result.getTotalAfterCoupons() == 516.8);
		assertTrue(result.getDeliveryCost() == 47.74);
		assertTrue(result.getCouponDiscount() + result.getTotalAfterCoupons() == result.getRawTotal());
	}
	
	@Test
	public void test_All() {
		suspect = new ShoppingCart(this.all);
		Result result = suspect.printCart();
		
		assertTrue((Double)result.getCampaignDiscounts().get(0) == 120.00);
		assertTrue((Double)result.getCampaignDiscounts().get(1) == 0.00);
		assertTrue(result.getTotalCampaignDiscount() == 120.00);
		assertTrue(result.getCouponDiscount() == 240.71999999999997);
		assertTrue(result.getRawTotal() == 800.00);
		assertTrue(result.getTotalBeforeCoupons() == 680.0);
		assertTrue(result.getTotalAfterCoupons() == 439.28000000000003);
		assertTrue(result.getDeliveryCost() == 47.74);
	}
	
}
