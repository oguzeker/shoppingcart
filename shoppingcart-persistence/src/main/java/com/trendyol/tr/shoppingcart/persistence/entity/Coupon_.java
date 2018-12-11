package com.trendyol.tr.shoppingcart.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-12-10T04:40:35.788+0300")
@StaticMetamodel(Coupon.class)
public class Coupon_ {
	public static volatile SingularAttribute<Coupon, Integer> couponId;
	public static volatile SingularAttribute<Coupon, Double> discount;
	public static volatile SingularAttribute<Coupon, Double> minCartAmount;
	public static volatile SingularAttribute<Coupon, Cart> cart;
}
