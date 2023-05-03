package com.java.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.insurance.model.Claim;

public interface ClaimRespository extends JpaRepository<Claim, Integer> {

}
