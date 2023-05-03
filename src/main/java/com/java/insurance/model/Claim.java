package com.java.insurance.model;

import org.springframework.stereotype.Service;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CLAIMS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private boolean claimed = false;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "policy_id")
	private Policy policy;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
}
