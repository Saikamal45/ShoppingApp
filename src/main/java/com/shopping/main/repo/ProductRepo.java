package com.shopping.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.main.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

}
