package com.trendyol.tr.shoppingcart.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-12-10T04:40:35.781+0300")
@StaticMetamodel(Cart.class)
public class Cart_ {
	public static volatile SingularAttribute<Cart, Integer> cartId;
	public static volatile SingularAttribute<Cart, Double> actualAmount;
	public static volatile SingularAttribute<Cart, Double> finalAmount;
	public static volatile ListAttribute<Cart, Coupon> coupons;
	public static volatile ListAttribute<Cart, Purchase> purchases;
}
