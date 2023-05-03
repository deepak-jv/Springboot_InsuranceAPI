package com.java.insurance.service.impl;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.insurance.config.Constants;
import com.java.insurance.exceptions.ResourceNotFoundException;
import com.java.insurance.model.Claim;
import com.java.insurance.model.Policy;
import com.java.insurance.model.Role;
import com.java.insurance.model.User;
import com.java.insurance.payloads.PolicyDto;
import com.java.insurance.payloads.UserClaimDto;
import com.java.insurance.payloads.UserDto;
import com.java.insurance.payloads.UserPolicyDto;
import com.java.insurance.repository.RoleRepository;
import com.java.insurance.repository.UserRepository;
import com.java.insurance.service.ClaimService;
import com.java.insurance.service.PolicyService;
import com.java.insurance.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	

	public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	@Override
	public UserDto create_user(UserDto userDto) {
		User user = mapper.map(userDto, User.class);
		User savedUser = userRepository.save(user);
		return mapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
//		user.builder().name(userDto.getName()).email(userDto.getEmail())
//					.password(userDto.getPassword()).age(userDto.getAge()).address(userDto.getAddress()).build();
		
		user.setName(userDto.getName());
		user.setAddress(userDto.getAddress());
		user.setEmail(userDto.getEmail());
		user.setAge(userDto.getAge());
		user.setPassword(userDto.getPassword());
		
		User updateUser = userRepository.save(user);
		return mapper.map(updateUser, UserDto.class);
		
	}


	@Override
	public UserDto getUserById(Integer id) {
		
		User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
		return mapper.map(user, UserDto.class);
		
		
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User>users = userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user->mapper.map(user,UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer id) {

		User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", id));
		userRepository.delete(user);
		
	}




	@Override
	public List<UserPolicyDto> getPolicyByUser(Integer userId) {

		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		List<Policy> userPolicies = user.getPolicies();
		
		List<UserPolicyDto> policiesDto = userPolicies.stream().map(uPolicy-> mapper.map(uPolicy, UserPolicyDto.class)).collect(Collectors.toList());
		return policiesDto;
	}

	@Override
	public List<UserClaimDto> getClaimByUser(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		List<Claim> userClaims = user.getClaims();
		
		List<UserClaimDto> userClaimsDto = userClaims.stream().map(uClaim-> mapper.map(uClaim, UserClaimDto.class)).collect(Collectors.toList());
		return userClaimsDto;
	}

	@Override
	public UserDto registerUser(UserDto userDto) {

		User user = mapper.map(userDto,User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Role role = roleRepository.findById(Constants.NORMAL_USER).get();
		user.getRoles().add(role);
		
		User newUser = userRepository.save(user);
		
		return mapper.map(newUser, UserDto.class);
	}


}
