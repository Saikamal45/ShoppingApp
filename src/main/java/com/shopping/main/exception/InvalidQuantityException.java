package com.shopping.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidQuantityException extends RuntimeException{

	public InvalidQuantityException(String message) {
		super(message);
	}
}
