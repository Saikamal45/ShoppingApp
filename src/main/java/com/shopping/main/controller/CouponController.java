package com.shopping.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.main.entity.Coupon;
import com.shopping.main.service.CouponService;

@RestController
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	@GetMapping("/coupons")
	public ResponseEntity<List<Coupon>> getAllCoupons(){
		List<Coupon> coupons=couponService.getAllCoupons();
		return new ResponseEntity<>(coupons,HttpStatus.OK);
	}
	
	@GetMapping("/coupon/{code}")
	public ResponseEntity<Coupon> getCouponByCode(@PathVariable String code){
		Optional<Coupon> coupon=couponService.getCouponByCode(code);
		if(coupon.isPresent()) {
			Coupon coupon2=coupon.get();
			return new ResponseEntity<>(coupon2,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/createCoupon")
	public Coupon createProduct(@RequestBody Coupon coupon){
		return couponService.createCoupon(coupon);
	}
}
