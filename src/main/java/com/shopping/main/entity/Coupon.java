package com.shopping.main.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long couponId;
	private String code;
	private double discount;
	private LocalDate expiryDate;

	
	private int used;
	
	@OneToOne
	private Order order;
	
	private Long usedByUserId;
	
	public boolean isExpired() {
		return LocalDate.now().isAfter(expiryDate);
	}

	
	
	
}
