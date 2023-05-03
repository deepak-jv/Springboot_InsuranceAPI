package com.java.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.insurance.exceptions.ResourceNotFoundException;
import com.java.insurance.model.Payment;
import com.java.insurance.model.Policy;
import com.java.insurance.model.User;
import com.java.insurance.payloads.PaymentDto;
import com.java.insurance.repository.PaymentRespository;
import com.java.insurance.repository.PolicyRepository;
import com.java.insurance.repository.UserRepository;
import com.java.insurance.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	
	@Autowired
	private PaymentRespository paymentRespository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PolicyRepository policyRepository;
	

	@Override
	public PaymentDto createPayment(PaymentDto paymentDto,Integer userId, Integer pId) {
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		Policy policy = policyRepository.findById(pId).orElseThrow(()->new ResourceNotFoundException("Policy","id",pId));
		
		Payment payment = modelMapper.map(paymentDto, Payment.class);
		payment.setUser(user);
		payment.setPolicy(policy);

		paymentRespository.save(payment);
		
		return modelMapper.map(payment,PaymentDto.class);
	}

	@Override
	public PaymentDto getPaymentsById(Integer userId,Integer pId, Integer payId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentDto> getAllPayments(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		
		List<Payment> payments = paymentRespository.findAll();
		List<Payment> userPayments = new ArrayList<Payment>();
		
		for (Payment payment : payments) {
			if (payment.getUser().getId() == user.getId()) {
				userPayments.add(payment);
			}
		}
		
		List<PaymentDto> paymentsDto = userPayments.stream().map(payment->modelMapper.map(payment, PaymentDto.class)).collect(Collectors.toList());
		
		for (PaymentDto paymentDto : paymentsDto) {
		}
		return paymentsDto;
	}

}
