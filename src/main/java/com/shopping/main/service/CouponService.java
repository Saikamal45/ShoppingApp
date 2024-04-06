package com.shopping.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.main.entity.Coupon;
import com.shopping.main.entity.Order;
import com.shopping.main.exception.CouponNotValidException;
import com.shopping.main.exception.OrderNotFoundException;
import com.shopping.main.repo.CouponRepo;
import com.shopping.main.repo.OrderRepo;

@Service
public class CouponService {

    @Autowired
    private CouponRepo couponRepo;
    
    @Autowired
    private OrderRepo orderRepo;

    public boolean isValidCoupon(String code) {
        Optional<Coupon> optionalCoupon = couponRepo.findByCode(code);
        return optionalCoupon.isPresent() && !optionalCoupon.get().isExpired();
    }

    public List<Coupon> getAllCoupons() {
        return couponRepo.findAll();
    }

    public Coupon applyCouponToOrder(Long userId, Long orderId, String couponCode) {
        Optional<Coupon> optionalCoupon = couponRepo.findByCode(couponCode);
        if (optionalCoupon.isPresent()) {
            Coupon coupon = optionalCoupon.get();
            // Check if the coupon has been used by the specific user
            if (coupon.getUsedByUserId() != null && coupon.getUsedByUserId().equals(userId)) {
                throw new CouponNotValidException("Coupon has already been used by this user.");
            }
            // Check if the coupon has expired
            if (coupon.isExpired()) {
                throw new CouponNotValidException("Coupon has expired.");
            }
            // Check if the coupon has already been applied to the order
            Order order = orderRepo.findById(orderId)
                                    .orElseThrow(() -> new OrderNotFoundException("Order not found"));
            if (order.getCoupon() != null) {
                throw new CouponNotValidException("Coupon has already been applied to this order.");
            }
            // Update the coupon's usage status to indicate it has been used by this user
            coupon.setUsedByUserId(userId);
            couponRepo.save(coupon);
            // Associate the coupon with the order and save the order
            order.setCoupon(coupon);
            orderRepo.save(order);
            return coupon;
        } else {
            throw new CouponNotValidException("Coupon not found.");
        }
    }


    public Optional<Coupon> getCouponByCode(String code) {
        return couponRepo.findByCode(code);
    }

    public Coupon createCoupon(Coupon coupon) {
        return couponRepo.save(coupon);
    }
}
