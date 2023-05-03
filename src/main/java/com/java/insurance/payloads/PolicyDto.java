package com.java.insurance.payloads;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.java.insurance.model.Claim;
import com.java.insurance.model.User;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter	
public class PolicyDto {
	
		private int id;
		
		@NotBlank(message ="policy name should not br blank")
		private String policyName;
		
		@NotBlank
		@Size(min = 10, message = "description length should not be less than 10 character")
		private String description;
		
		private UserDto user;
		
//		private Set<PaymentDto> payments = new HashSet<PaymentDto>();
		
//		TODO check the api in postman
//		private List<ClaimDto> claims = new ArrayList<ClaimDto>();
		

}
