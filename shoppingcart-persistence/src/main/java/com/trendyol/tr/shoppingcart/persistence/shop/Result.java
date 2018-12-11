package com.trendyol.tr.shoppingcart.persistence.shop;

import java.util.ArrayList;
import java.util.List;

public class Result {

	List<Double> campaignDiscounts;
	double totalCampaignDiscount;
	double couponDiscount;
	double rawTotal;
	double totalBeforeCoupons;
	double totalAfterCoupons;
	double deliveryCost;
	
	public List<Double> getCampaignDiscounts() {
		if (campaignDiscounts == null) {
			setCampaignDiscounts(new ArrayList<Double>());
		}
		return campaignDiscounts;
	}
	public void setCampaignDiscounts(List<Double> campaignDiscounts) {
		this.campaignDiscounts = campaignDiscounts;
	}
	public double getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(double couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public double getRawTotal() {
		return rawTotal;
	}
	public void setRawTotal(double rawTotal) {
		this.rawTotal = rawTotal;
	}
	public double getTotalBeforeCoupons() {
		return totalBeforeCoupons;
	}
	public void setTotalBeforeCoupons(double totalBeforeCoupons) {
		this.totalBeforeCoupons = totalBeforeCoupons;
	}
	public double getTotalAfterCoupons() {
		return totalAfterCoupons;
	}
	public void setTotalAfterCoupons(double totalAfterCoupons) {
		this.totalAfterCoupons = totalAfterCoupons;
	}
	public double getDeliveryCost() {
		return deliveryCost;
	}
	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}
	public double getTotalCampaignDiscount() {
		return totalCampaignDiscount;
	}
	public void setTotalCampaignDiscount(double totalCampaignDiscount) {
		this.totalCampaignDiscount = totalCampaignDiscount;
	}
	
}
