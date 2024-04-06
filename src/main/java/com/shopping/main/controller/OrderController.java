package com.shopping.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.main.entity.Order;
import com.shopping.main.service.OrderService;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestParam("userId") Long userId, 
                                              @RequestParam("productId") Long productId,
                                              @RequestParam("qty") int quantity,
                                              @RequestParam(value="coupon", required=false) String couponCode) {
        Order order = orderService.createOrder(userId, productId, quantity, couponCode);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
