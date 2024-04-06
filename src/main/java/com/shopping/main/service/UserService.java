package com.shopping.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.main.entity.User;
import com.shopping.main.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	public User createUser(User user) {
		return userRepo.save(user);
		
	}
	
	public User getUserById(long userId) {
		return userRepo.findById(userId).orElse(null);
	}
}
