package com.java.insurance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.insurance.model.Claim;
import com.java.insurance.model.Policy;
import com.java.insurance.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByName(String name);

}
