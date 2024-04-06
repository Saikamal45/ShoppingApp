package com.shopping.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.main.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

}
