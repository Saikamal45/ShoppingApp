package com.shopping.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.main.entity.Coupon;
import com.shopping.main.entity.Order;
import com.shopping.main.service.OrderService;

@RestController
public class StatusController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/status/{userId}")
	public ResponseEntity<List<OrderResponse>> getAllOrdersByUserId(@PathVariable Long userId) {
        Optional<List<Order>> orders = orderService.getAllOrdersByUserId(userId);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        List<OrderResponse> orderResponses = convertToOrderResponse(orders);
        
        return new ResponseEntity<>(orderResponses, HttpStatus.OK);
    }
	
	private List<OrderResponse> convertToOrderResponse(Optional<List<Order>> ordersOptional) {
	    List<OrderResponse> orderResponses = new ArrayList<>();
	    if (ordersOptional.isPresent()) {
	        List<Order> orders = ordersOptional.get();
	        for (Order order : orders) {
	            OrderResponse orderResponse = new OrderResponse();
	            orderResponse.setOrderId(order.getOrderId());
	            orderResponse.setAmount(order.getAmount());
	            System.out.println("Order " + order.getOrderId() + " - Coupon Object: " + order.getCoupon());
	            String couponCode = order.getCoupon() != null ? ((Coupon) order.getCoupon()).getCode() : null;
	            orderResponse.setCoupon(couponCode);
	            orderResponses.add(orderResponse);
	        }
	    }
	    return orderResponses;
	}

	
	static class OrderResponse{
		private Long orderId;
        private double amount;
        private String coupon;
		public Long getOrderId() {
			return orderId;
		}
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
	
		public String getCoupon() {
			return coupon;
		}
		public void setCoupon(String coupon) {
			this.coupon = coupon;
		}
        
        
	}
}
