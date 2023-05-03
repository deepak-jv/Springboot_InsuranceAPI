package com.java.insurance.payloads;

import java.util.Set;

import lombok.Data;


@Data
public class PaymentDto {

	private int id;
	private int payment;
	private PolicyDto policy;
}
