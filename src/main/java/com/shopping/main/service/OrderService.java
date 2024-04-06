package com.shopping.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.main.entity.Coupon;
import com.shopping.main.entity.Order;
import com.shopping.main.entity.Product;
import com.shopping.main.exception.InvalidQuantityException;
import com.shopping.main.repo.OrderRepo;
import com.shopping.main.repo.ProductRepo;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
   
    
    @Autowired
    private CouponService couponService;
    
    @Autowired
    private ProductRepo productRepo;
    
    public Order createOrder(Long userId, Long productId ,int quantity, String couponCode) {
        // Fetch the product based on the productId 
        Optional<Product> productOptional = productRepo.findById(productId);
        if (!productOptional.isPresent()) {
            throw new IllegalArgumentException("Product not found for user id: " + productId);
        }
        Product product = productOptional.get();
        
        // check quantity available or not
        if(quantity < 1 || quantity > product.getAvailableQuantity()) {
            throw new InvalidQuantityException("Invalid Quantity");
        }
        
        // calculating the product price for the total quantity selected by the user
        double price = product.getPrice();
        double totalAmount = price * quantity;
        
        // Apply Coupon if Provided
        Coupon coupon = null;
        if (couponCode != null && !couponCode.isEmpty()) {
            coupon = couponService.applyCouponToOrder(userId, productId, couponCode);
            totalAmount *= (1 - coupon.getDiscount() / 100);
        }
        
        Order order = new Order();
        order.setUserId(userId);
        order.setQuantity(quantity);
        order.setAmount(totalAmount);
        order.setProduct(product);
        order.setCoupon(coupon);
        
        // Update Available Quantity of the product 
        product.decreaseQuantity(quantity);
        productRepo.save(product);
        
        // Save and return the order
        return orderRepo.save(order);
    }
    
    public Optional<List<Order>> getAllOrdersByUserId(Long userId) {
        return orderRepo.findAllByUserId(userId);
    }
    
    
}
