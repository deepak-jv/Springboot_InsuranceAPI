package com.java.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.insurance.model.User;
import com.java.insurance.payloads.UserDto;

import jakarta.transaction.Transactional;

@Service
public class JwtService {
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional(rollbackOn = Exception.class) 
	public String saveDto(UserDto userDto) { 
//	    userDto.setPassword(bCryptPasswordEncoder
//	           .encode(userDto.getPassword())); 
//	    return save(new User(userDto)).getId();
	    return null;
	}
}
