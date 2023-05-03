package com.java.insurance.service;

import java.util.List;

import com.java.insurance.model.Policy;
import com.java.insurance.payloads.PolicyDto;

public interface PolicyService {
	
//	create
	PolicyDto createPolicy(PolicyDto policyDto, Integer userId);
	
//	update
	PolicyDto updatePolicy(PolicyDto policyDto,Integer pId);
	
//	delete
	void deletePolicy(Integer pId);
	
//	get by id
	PolicyDto getPolicyById(Integer pId);
	
//	get all
	
	List<PolicyDto> getAllPolicy();

	
	
		

}
