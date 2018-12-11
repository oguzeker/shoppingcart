
package com.trendyol.tr.shoppingcart.schema;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for coupon complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="coupon"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="couponId" type="{http://trendyol.com/tr/shoppingcart/schema}idType" minOccurs="0"/&gt;
 *         &lt;element name="cart" type="{http://trendyol.com/tr/shoppingcart/schema}cart" minOccurs="0"/&gt;
 *         &lt;element name="minCartAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "coupon", propOrder = {
    "couponId",
    "cart",
    "minCartAmount"
})
public class Coupon {

    @DecimalMin("1")
    protected Integer couponId;
    @Valid
    protected Cart cart;
    protected Double minCartAmount;

    /**
     * Gets the value of the couponId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCouponId() {
        return couponId;
    }

    /**
     * Sets the value of the couponId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCouponId(Integer value) {
        this.couponId = value;
    }

    /**
     * Gets the value of the cart property.
     * 
     * @return
     *     possible object is
     *     {@link Cart }
     *     
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Sets the value of the cart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cart }
     *     
     */
    public void setCart(Cart value) {
        this.cart = value;
    }

    /**
     * Gets the value of the minCartAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinCartAmount() {
        return minCartAmount;
    }

    /**
     * Sets the value of the minCartAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinCartAmount(Double value) {
        this.minCartAmount = value;
    }

}
