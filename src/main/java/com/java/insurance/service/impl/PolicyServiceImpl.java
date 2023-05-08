package com.java.insurance.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.insurance.exceptions.ResourceNotFoundException;
import com.java.insurance.model.Claim;
import com.java.insurance.model.Policy;
import com.java.insurance.model.User;
import com.java.insurance.payloads.PolicyDto;
import com.java.insurance.payloads.UserDto;
import com.java.insurance.repository.ClaimRespository;
import com.java.insurance.repository.PolicyRepository;
import com.java.insurance.repository.UserRepository;
import com.java.insurance.service.PolicyService;
import com.java.insurance.service.UserService;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyRepository policyRepo;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	ClaimRespository claimRespository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Logger logger;
	
	@Override
	public PolicyDto createPolicy(PolicyDto policyDto,Integer userId) {
	
		logger.info("creating the Ploicy for the customer with request body :"+policyDto+", user Id :"+userId);
	
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		Policy policy = modelMapper.map(policyDto, Policy.class);
		
		policy.setUser(user);
		
		Policy addPolicy = policyRepo.save(policy);
		
		return modelMapper.map(addPolicy,PolicyDto.class);
		
	}

	@Override
	public PolicyDto updatePolicy(PolicyDto policyDto, Integer pId) {
		
		logger.info("updaitng the policy with policy id : "+pId);
		Policy policy = policyRepo.findById(pId).orElseThrow(()-> new ResourceNotFoundException("Policy","id",pId));
		
		policy.setPolicyName(policyDto.getPolicyName());
		policy.setDescription(policyDto.getDescription());
		
		Policy updatedPolicy = policyRepo.save(policy);
		
		return modelMapper.map(updatedPolicy, PolicyDto.class);

	}

	@Override
	public void deletePolicy(Integer pId) {
		logger.info("deleting the policy with policy id :"+pId);

		Policy policy = policyRepo.findById(pId).orElseThrow(()->new ResourceNotFoundException("Policy","id",pId));
		policyRepo.delete(policy);
	}

	@Override
	public PolicyDto getPolicyById(Integer pId) {
		
		logger.info("getting the policy with policy id :"+pId);
		Policy policy = policyRepo.findById(pId).orElseThrow(()-> new ResourceNotFoundException("Policy", "id", pId));
		
		return modelMapper.map(policy,PolicyDto.class);
	}

	@Override
	public List<PolicyDto> getAllPolicy() {
		logger.info("getting all the policies");

		List<Policy> policies = policyRepo.findAll();
		List<PolicyDto> policyDtos = policies.stream().map(pol-> modelMapper.map(pol,PolicyDto.class)).collect(Collectors.toList());
		
		
		return policyDtos;
	}



}
