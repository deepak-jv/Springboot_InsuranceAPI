package com.java.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.insurance.payloads.ApiResponse;
import com.java.insurance.payloads.PaymentDto;
import com.java.insurance.payloads.PolicyDto;
import com.java.insurance.service.PaymentService;
import com.java.insurance.service.PolicyService;
import com.java.insurance.service.impl.PolicyServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PaymentController {
	
	
	@Autowired
	private PaymentService paymentService;
	
	// create
	
	@PostMapping("/payments/user/{userId}/policies/{pId}")
	public ResponseEntity<PaymentDto> createPolicy(@Valid @RequestBody PaymentDto policy, @PathVariable Integer userId, @PathVariable Integer pId){
		
		PaymentDto createPolicy = paymentService.createPayment(policy,userId,pId);
		
		return new ResponseEntity<PaymentDto>(createPolicy,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/payments/user/{id}")
	public ResponseEntity<List<PaymentDto>> getPaymentsByid(@PathVariable Integer id){
		List<PaymentDto> policy = paymentService.getAllPayments(id);
		return  ResponseEntity.ok(policy);
	}
	
	

	

}
