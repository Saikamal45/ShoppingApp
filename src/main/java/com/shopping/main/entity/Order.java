package com.shopping.main.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "`order`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private Long userId;
	private int quantity;
	private double amount;
	
	@OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
	private Coupon coupon;
	
	@ManyToOne
	private Product product;
	
	public Coupon getCoupon() {
	    return coupon;
	}
	public String getCouponCode() {
	    if (coupon != null) {
	        return coupon.getCode();
	    } else {
	        return null; // No coupon associated with this order
	    }
	}


	public void setProduct(Product product) {
		this.product=product;
		
	}

	
	
	
	
	

}
