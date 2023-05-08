package com.java.insurance.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.insurance.exceptions.ResourceNotFoundException;
import com.java.insurance.model.Claim;
import com.java.insurance.model.Policy;
import com.java.insurance.model.User;
import com.java.insurance.payloads.ClaimDto;
import com.java.insurance.repository.ClaimRespository;
import com.java.insurance.repository.PolicyRepository;
import com.java.insurance.repository.UserRepository;
import com.java.insurance.service.ClaimService;

import ch.qos.logback.classic.Logger;

@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	private ClaimRespository claimRespository;
	
	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Logger logger;
	
	
	@Override
	public ClaimDto createClaim(ClaimDto claimDto, Integer userId, Integer pId) {
		
		logger.info("creating the Claims for the customer with request body :"+claimDto+", user Id :"+userId+" and policy id :"+pId);
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		Policy policy = policyRepository.findById(pId).orElseThrow(()->new ResourceNotFoundException("Policy","id",pId));
	
		Claim claim = modelMapper.map(claimDto,Claim.class);
	
		claim.setUser(user);
		claim.setPolicy(policy);
		Claim createClaim = claimRespository.save(claim);
		
		return modelMapper.map(createClaim, ClaimDto.class);
	}

	@Override
	public ClaimDto updateClaim(ClaimDto claimDto, Integer cId) {
		
		logger.info("updating the Claims for the customer with request body :"+claimDto+" and user Id :"+cId);

		Claim updateClaim = claimRespository.findById(cId).orElseThrow(()-> new ResourceNotFoundException("Claim","Id",cId));
		
		updateClaim.setDescription(claimDto.getDescription());
		updateClaim.setClaimed(claimDto.isClaimed());
		
		Claim claimUpdated = claimRespository.save(updateClaim);

		
		return modelMapper.map(claimUpdated, ClaimDto.class);
	}

	@Override
	public void deleteClaim(Integer cId) {
		logger.info("deleting the Claims for the customer with claim Id :"+cId);

		Claim claim = claimRespository.findById(cId).orElseThrow(()-> new ResourceNotFoundException("Claim","Id",cId));
		claimRespository.delete(claim);		
	}

	@Override
	public ClaimDto getClaimById(Integer cId) {
		logger.info("getting the Claims for the customer with customer Id :"+cId);

		Claim claim = claimRespository.findById(cId).orElseThrow(()-> new ResourceNotFoundException("Claim","Id",cId));
		return modelMapper.map(claim, ClaimDto.class);
	}

	@Override
	public List<ClaimDto> getAllClaims() {
		logger.info("getting list of the Claims");

		List<Claim> claims = claimRespository.findAll();
		List<ClaimDto> allClaims = claims.stream().map(claim -> modelMapper.map(claim, ClaimDto.class)).collect(Collectors.toList());
		return allClaims;
	}

}
