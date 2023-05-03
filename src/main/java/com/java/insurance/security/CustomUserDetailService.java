package com.java.insurance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.java.insurance.exceptions.ResourceNotFoundException;
import com.java.insurance.model.User;
import com.java.insurance.repository.UserRepository;

@Component
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username).orElseThrow(()-> new ResourceNotFoundException("User","name",username));
		return user;
	} 

}
