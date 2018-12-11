package com.trendyol.tr.shoppingcart.persistence.shop;


public enum CampaignType {

	PERCENTAGE_DISCOUNT("1"),
	AMOUNT_DISCOUNT("2");
    private final String value;

    CampaignType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CampaignType fromValue(String v) {
        for (CampaignType c: CampaignType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
