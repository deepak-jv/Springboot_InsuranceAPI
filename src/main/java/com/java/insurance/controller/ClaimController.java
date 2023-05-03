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

import com.java.insurance.model.Claim;
import com.java.insurance.payloads.ApiResponse;
import com.java.insurance.payloads.ClaimDto;
import com.java.insurance.service.ClaimService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ClaimController {
	
	@Autowired
	ClaimService claimService;
	
	// create
	@PostMapping("/claims/user/{userId}/policy/{pId}")
	public ResponseEntity<ClaimDto> createClaim(@Valid @RequestBody ClaimDto calimDto,@PathVariable Integer userId,@PathVariable Integer pId){
		ClaimDto claim = claimService.createClaim(calimDto,userId,pId);
		return new ResponseEntity<ClaimDto>(claim,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/claims/{id}")
	public ResponseEntity<ClaimDto> updateClaim(@Valid @RequestBody ClaimDto claimDto, @PathVariable Integer id){
		
		ClaimDto claim = claimService.updateClaim(claimDto, id);
		return new ResponseEntity<ClaimDto>(claim,HttpStatus.OK);
	}
	
	// delete
	@DeleteMapping("claims/{id}")
	public ResponseEntity<ApiResponse> deleteClaim(@PathVariable Integer id){
		claimService.deleteClaim(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfully",true),HttpStatus.OK);
	}
	
	// get by id
	@GetMapping("/claims/{id}")
	public ResponseEntity<ClaimDto> getById(@PathVariable Integer id){
		ClaimDto claimById = claimService.getClaimById(id);
		return new ResponseEntity<ClaimDto>(claimById,HttpStatus.OK);
	}
	
	// get all
	@GetMapping("/claims")
	public ResponseEntity<List<ClaimDto>> getAll(){
		List<ClaimDto> claims = claimService.getAllClaims();
		return ResponseEntity.ok(claims);
	}

}
