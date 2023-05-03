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
import com.java.insurance.payloads.PolicyDto;
import com.java.insurance.service.PolicyService;
import com.java.insurance.service.impl.PolicyServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PolicyController {
	
	
	@Autowired
	PolicyService policyService;
	
	
	// create
	
	@PostMapping("/policies/user/{userId}")
	public ResponseEntity<PolicyDto> createPolicy(@Valid @RequestBody PolicyDto policy, @PathVariable Integer userId){
		
		PolicyDto createPolicy = policyService.createPolicy(policy,userId);
		
		return new ResponseEntity<PolicyDto>(createPolicy,HttpStatus.CREATED);
		
	}
	
	//update
	@PutMapping("/policies/{id}")
	public ResponseEntity<PolicyDto> updatePolicy(@Valid @RequestBody PolicyDto policy, @PathVariable Integer id){
		
		PolicyDto updatedPolicy = policyService.updatePolicy(policy, id);
		
		return new ResponseEntity<PolicyDto>(updatedPolicy,HttpStatus.OK);
	}
	
	
	
	// delete
	
	@DeleteMapping("/policies/{id}")
	public ResponseEntity<ApiResponse> deletePolicy(@PathVariable Integer id){
		 policyService.deletePolicy(id);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("Policy deleted successfully !!",true),HttpStatus.OK);
	}
	
	// get by id
	
	@GetMapping("/policies/{id}")
	public ResponseEntity<PolicyDto> getPolicyByid(@PathVariable Integer id){
		PolicyDto policy = policyService.getPolicyById(id);
		return new ResponseEntity<PolicyDto>(policy,HttpStatus.OK);
	}
	
	
	// get all
	@GetMapping("/policies")
	public ResponseEntity<List<PolicyDto>> getallPolicies(){
		
		List<PolicyDto> policies = policyService.getAllPolicy();
		return ResponseEntity.ok(policies);
	}
	

}
