package com.java.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.insurance.model.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Integer> {
}
