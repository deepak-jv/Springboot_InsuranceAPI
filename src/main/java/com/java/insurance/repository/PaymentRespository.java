package com.java.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.insurance.model.Payment;

public interface PaymentRespository extends JpaRepository<Payment, Integer> {

}
