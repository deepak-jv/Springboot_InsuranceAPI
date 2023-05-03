package com.java.insurance.service;

import java.util.List;

import com.java.insurance.payloads.PaymentDto;

public interface PaymentService {

	List<PaymentDto> getAllPayments(Integer userId);
	
	PaymentDto createPayment(PaymentDto paymentDto,Integer userId,Integer pId);
	
	PaymentDto getPaymentsById(Integer userId,Integer pId,Integer payId);
	
}
