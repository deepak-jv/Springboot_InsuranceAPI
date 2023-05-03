package com.java.insurance.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClaimDto {
	
	private int id;
	
	
	private boolean claimed = false;
	
	@NotBlank
	@Size(min = 10, message = "description length should not be less than 10 character")
	private String description;
	
	private UserDto user;
	
	private PolicyDto policy;

}
