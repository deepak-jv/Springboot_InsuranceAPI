package com.java.insurance.service;

import com.java.insurance.payloads.ClaimDto;
import java.util.*;

public interface ClaimService {

//	create
	ClaimDto createClaim(ClaimDto claimDto,Integer userId,Integer pId);
	
//	update
	ClaimDto updateClaim(ClaimDto claimDto, Integer cId);
	
//	delete
	void deleteClaim(Integer cId);
	
//	get by id
	ClaimDto getClaimById(Integer cId);
	
//	get all
	List<ClaimDto> getAllClaims();
}
