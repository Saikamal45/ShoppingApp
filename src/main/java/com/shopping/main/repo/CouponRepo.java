package com.shopping.main.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.shopping.main.entity.Coupon;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Long>{

	 Optional<Coupon> findByCode(String code);
}
