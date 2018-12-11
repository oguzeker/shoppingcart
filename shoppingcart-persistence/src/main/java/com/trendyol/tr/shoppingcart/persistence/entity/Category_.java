package com.trendyol.tr.shoppingcart.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-12-10T04:40:35.784+0300")
@StaticMetamodel(Category.class)
public class Category_ {
	public static volatile SingularAttribute<Category, Integer> categoryId;
	public static volatile SingularAttribute<Category, String> title;
	public static volatile ListAttribute<Category, Campaign> campaigns;
	public static volatile SingularAttribute<Category, Category> category;
	public static volatile ListAttribute<Category, Category> categories;
	public static volatile ListAttribute<Category, Product> products;
}
