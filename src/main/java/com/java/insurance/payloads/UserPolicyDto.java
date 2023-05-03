package com.java.insurance.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserPolicyDto {
	
	private int id;
	
	@NotBlank(message ="policy name should not br blank")
	private String policyName;
	
	@NotBlank
	@Size(min = 10, message = "description length should not be less than 10 character")
	private String description;

}
