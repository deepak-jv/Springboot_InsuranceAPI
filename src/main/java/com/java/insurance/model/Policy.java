package com.java.insurance.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "POLICIES")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String policyName;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "policy",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Claim> claim = new ArrayList<Claim>();
	
	@OneToMany(mappedBy = "policy",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Payment> payment = new HashSet<Payment>();
}

