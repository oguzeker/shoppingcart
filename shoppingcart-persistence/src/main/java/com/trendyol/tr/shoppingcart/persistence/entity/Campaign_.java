package com.trendyol.tr.shoppingcart.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-12-10T04:40:35.745+0300")
@StaticMetamodel(Campaign.class)
public class Campaign_ {
	public static volatile SingularAttribute<Campaign, Integer> campaignId;
	public static volatile SingularAttribute<Campaign, Double> discount;
	public static volatile SingularAttribute<Campaign, Integer> minItemCount;
	public static volatile SingularAttribute<Campaign, Integer> type;
	public static volatile SingularAttribute<Campaign, Category> category;
}
