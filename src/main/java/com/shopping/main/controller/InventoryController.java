package com.shopping.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shopping.main.service.ProductService;

@RestController
public class InventoryController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/inventory")
	public ResponseEntity<InventoryResponse> getInventory(){
		
		Long productId=(long) 1;
		
		int availableQuantity=productService.getAvailableQuantity(productId);
		int orderedQuantity=productService.getOrderedQuantity();	
	    int price=productService.getProductPrice(productId);
	    
	    InventoryResponse inventoryResponse=new InventoryResponse(orderedQuantity,price,availableQuantity);
	    return new ResponseEntity<>	(inventoryResponse,HttpStatus.OK);
}
	
	static class InventoryResponse{
		
		private int ordered;
		private int price;
		private int available;
		
		public InventoryResponse(int orderedQuantity, int price, int availableQuantity) {
			this.ordered=orderedQuantity;
			this.price=price;
			this.available=availableQuantity;
		}

		public int getOrdered() {
			return ordered;
		}

		public void setOrdered(int ordered) {
			this.ordered = ordered;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public int getAvailable() {
			return available;
		}

		public void setAvailable(int available) {
			this.available = available;
		}
		
	}
}
