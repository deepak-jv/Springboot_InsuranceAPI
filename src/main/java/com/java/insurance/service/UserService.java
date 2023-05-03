package com.java.insurance.service;

import java.util.List;

import com.java.insurance.model.Claim;
import com.java.insurance.model.Policy;
import com.java.insurance.model.User;
import com.java.insurance.payloads.PolicyDto;
import com.java.insurance.payloads.UserClaimDto;
import com.java.insurance.payloads.UserDto;
import com.java.insurance.payloads.UserPolicyDto;

public interface UserService {

	UserDto registerUser(UserDto user);
	
	UserDto create_user(UserDto user);
	
	UserDto updateUser(UserDto user, Integer id);
	
	UserDto getUserById(Integer id);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer id);
	
	List<UserPolicyDto> getPolicyByUser(Integer userId);
	
	List <UserClaimDto> getClaimByUser(Integer userId);

}
