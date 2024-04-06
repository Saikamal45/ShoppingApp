package com.shopping.main.controller;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
	 @PostMapping("/{userId}/{orderId}/pay")
	    public ResponseEntity<PaymentResponse> processPayment(
	            @RequestParam("amount") double amount,
	            @PathVariable Long userId,
	            @PathVariable Long orderId) {
	        // Mocking random response from payment server
	        int statusCode = getRandomStatusCode();
	        PaymentResponse paymentResponse = generatePaymentResponse(statusCode, userId, orderId);
	        return new ResponseEntity<>(paymentResponse, HttpStatus.valueOf(statusCode));
	    }


	private int getRandomStatusCode() {
		int [] statusCodes ={200,400,504,405};
		return statusCodes[new Random().nextInt(statusCodes.length)];
}
	private PaymentResponse generatePaymentResponse(int statusCode,Long userId, Long orderId) {
		PaymentResponse paymentResponse=new PaymentResponse();
		paymentResponse.setUserId(userId);
		paymentResponse.setOrderId(orderId);
		paymentResponse.setTransactionId("tran01010000" + (new Random().nextInt(6) + 1));
		
		switch(statusCode) {
		case 200:
			paymentResponse.setStatus("successful");
			break;
		case 400:
            paymentResponse.setStatus("failed");
            paymentResponse.setDescription(getRandomFailureDescription());
            break;
        case 504:
            paymentResponse.setStatus("failed");
            paymentResponse.setDescription("No response from payment server");
            break;
        case 405:
            paymentResponse.setStatus("failed");
            paymentResponse.setDescription("Order is already paid for");
            break;
        default:
            paymentResponse.setStatus("failed");
            paymentResponse.setDescription("Unknown error occurred");
            break;
		}
		return paymentResponse;
	}
	
	private String getRandomFailureDescription() {
		String[] descriptions= {
				"Payment Failed as amount is invalid",
                "Payment Failed from bank",
                "Payment Failed due to invalid order id"
		};
		return descriptions[new Random().nextInt(descriptions.length)];
	}
	static class PaymentResponse{
		private Long userId;
        private Long orderId;
        private String transactionId;
        private String status;
        private String description;
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public Long getOrderId() {
			return orderId;
		}
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}
		public String getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
        
        
	}
	}
