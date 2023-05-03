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
	
	
	@Override
	public ClaimDto createClaim(ClaimDto claimDto, Integer userId, Integer pId) {
		
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
		
		Claim updateClaim = claimRespository.findById(cId).orElseThrow(()-> new ResourceNotFoundException("Claim","Id",cId));
		
		updateClaim.setDescription(claimDto.getDescription());
		updateClaim.setClaimed(claimDto.isClaimed());
		
		Claim claimUpdated = claimRespository.save(updateClaim);

		
		return modelMapper.map(claimUpdated, ClaimDto.class);
	}

	@Override
	public void deleteClaim(Integer cId) {

		Claim claim = claimRespository.findById(cId).orElseThrow(()-> new ResourceNotFoundException("Claim","Id",cId));
		claimRespository.delete(claim);		
	}

	@Override
	public ClaimDto getClaimById(Integer cId) {

		Claim claim = claimRespository.findById(cId).orElseThrow(()-> new ResourceNotFoundException("Claim","Id",cId));
		return modelMapper.map(claim, ClaimDto.class);
	}

	@Override
	public List<ClaimDto> getAllClaims() {

		List<Claim> claims = claimRespository.findAll();
		List<ClaimDto> allClaims = claims.stream().map(claim -> modelMapper.map(claim, ClaimDto.class)).collect(Collectors.toList());
		return allClaims;
	}

}
