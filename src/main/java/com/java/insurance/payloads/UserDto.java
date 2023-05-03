package com.java.insurance.payloads;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min=4,message = "User must contain atleast 4 character")
	private String name;
	
	@NotEmpty(message = "Age should not be empty")
	private String age;
	
	@NotEmpty
	@Size(min = 5,message = "Address Should contain more than or equal to 5 characters")
	private String address;
	
	@Email(message = "invalid email")
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull()
	@Size(min=5,message = "Password should contain atleast 5 character")
	private String password;
	
	private Set<RoleDto> roles = new HashSet<>();

}
