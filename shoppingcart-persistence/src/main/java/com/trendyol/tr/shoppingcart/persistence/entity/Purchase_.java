package com.trendyol.tr.shoppingcart.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-12-08T23:18:28.250+0300")
@StaticMetamodel(Purchase.class)
public class Purchase_ {
	public static volatile SingularAttribute<Purchase, PurchasePK> id;
	public static volatile SingularAttribute<Purchase, Integer> quantity;
	public static volatile SingularAttribute<Purchase, Cart> cart;
	public static volatile SingularAttribute<Purchase, Product> product;
}
