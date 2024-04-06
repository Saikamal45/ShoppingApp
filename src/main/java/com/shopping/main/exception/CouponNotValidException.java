package com.shopping.main.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class CouponNotValidException extends RuntimeException{

	public CouponNotValidException(String message) {
		super(message);
	}
}
