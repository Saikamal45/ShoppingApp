package com.shopping.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	private String name;
	private int price;
	private int availableQuantity;
	
	
	
	public void decreaseQuantity(int quantity) {
		if(availableQuantity >= quantity) {
			availableQuantity -=quantity;
		}
		else {
			throw new IllegalArgumentException("Ordered quantity exceeds available quantity");
		}
	}
}
