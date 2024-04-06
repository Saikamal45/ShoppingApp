package com.shopping.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.main.entity.Order;
import com.shopping.main.entity.Product;
import com.shopping.main.repo.OrderRepo;
import com.shopping.main.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	
		
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}

	public Product getProductById(Long productId) {
		return productRepo.findById(productId).orElse(null);
	}
	
	public int getAvailableQuantity(Long productId) {
		Product product=productRepo.findById(productId).orElse(null);
		if(product != null) {
			return product.getAvailableQuantity();
		}
		else {
			return 0;
		}
	}
		
		public int getProductPrice(Long productId) {
			Product product=productRepo.findById(productId).orElse(null);
			if(product != null) {
				return product.getPrice();
			}
			else {
				return 0;
			}
		
	}

		public int getOrderedQuantity() {
			// Sum up the quantity from all orders
	        Iterable<Order> orders = orderRepo.findAll();
	        int totalOrderedQuantity = 0;
	        for (Order order : orders) {
	            totalOrderedQuantity += order.getQuantity();
	        }
	        return totalOrderedQuantity;
		}
}
