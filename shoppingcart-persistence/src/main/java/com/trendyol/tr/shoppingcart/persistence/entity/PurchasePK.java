package com.trendyol.tr.shoppingcart.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the purchase database table.
 * 
 */
@Embeddable
public class PurchasePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cart_id", insertable=false, updatable=false)
	private int cartId;

	@Column(name="product_id", insertable=false, updatable=false)
	private int productId;

	public PurchasePK() {
	}
	public int getCartId() {
		return this.cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getProductId() {
		return this.productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PurchasePK)) {
			return false;
		}
		PurchasePK castOther = (PurchasePK)other;
		return 
			(this.cartId == castOther.cartId)
			&& (this.productId == castOther.productId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cartId;
		hash = hash * prime + this.productId;
		
		return hash;
	}
}