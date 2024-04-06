package com.shopping.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.main.entity.Product;
import com.shopping.main.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/createProduct")
	public Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}
	
	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable long productId) {
		
		return productService.getProductById(productId);
	}
	
	@GetMapping("/product/{id}availableQuantity")
	public int getAvailableQuantity(@PathVariable Long productId) {
		return productService.getAvailableQuantity(productId);
	}
}
;