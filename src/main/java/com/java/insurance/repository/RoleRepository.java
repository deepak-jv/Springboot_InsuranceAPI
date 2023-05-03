package com.java.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.insurance.model.Role;

public interface RoleRepository  extends JpaRepository<Role, Integer>{
	
	

}
