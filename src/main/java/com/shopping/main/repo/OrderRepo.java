package com.shopping.main.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopping.main.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{

	Optional<List<Order>> findAllByUserId(Long userId);
	
	

}
