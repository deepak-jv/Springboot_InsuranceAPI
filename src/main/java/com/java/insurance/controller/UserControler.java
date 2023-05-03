package com.java.insurance.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.insurance.model.Policy;
import com.java.insurance.model.User;
import com.java.insurance.service.UserService;
import com.java.insurance.payloads.ApiResponse;
import com.java.insurance.payloads.ClaimDto;
import com.java.insurance.payloads.UserClaimDto;
import com.java.insurance.payloads.UserDto;
import com.java.insurance.payloads.UserPolicyDto;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class UserControler {


	
	@Autowired
	private UserService userService;
	

	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> get_customers(){			
	
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDto>  user_custormes_id(@PathVariable Integer id) {
		
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/user/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer id){
		UserDto updateUser = userService.updateUser(userDto, id);
		return ResponseEntity.ok(updateUser);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true),HttpStatus.OK);
		
	}
	
	
	@GetMapping("/user/{id}/policies")
	public ResponseEntity<List<UserPolicyDto>> customer_policy(@PathVariable int id) {
		
		List<UserPolicyDto> userPolicies = userService.getPolicyByUser(id);
		return ResponseEntity.ok(userPolicies);
	}
	
	@GetMapping("/user/{id}/claims")
	public ResponseEntity<List<UserClaimDto>> customer_claims(@PathVariable int id) {
		
		List <UserClaimDto> userClaims = userService.getClaimByUser(id);
		return ResponseEntity.ok(userClaims);
		
	}
	
	
	
}
