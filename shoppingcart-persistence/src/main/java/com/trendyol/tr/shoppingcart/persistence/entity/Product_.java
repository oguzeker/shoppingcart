package com.trendyol.tr.shoppingcart.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-12-10T04:40:35.792+0300")
@StaticMetamodel(Product.class)
public class Product_ {
	public static volatile SingularAttribute<Product, Integer> productId;
	public static volatile SingularAttribute<Product, String> title;
	public static volatile SingularAttribute<Product, Double> unitPrice;
	public static volatile SingularAttribute<Product, Category> category;
	public static volatile ListAttribute<Product, Purchase> purchases;
}
