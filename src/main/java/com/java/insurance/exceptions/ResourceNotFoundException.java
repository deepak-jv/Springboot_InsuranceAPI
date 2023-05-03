package com.java.insurance.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	 String resourceName;
	 String fieldName;
	 Integer fieldValueint;
	 String fieldValuestring;
	 
	public ResourceNotFoundException(String resourceName, String fieldName, Integer id) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,id));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValueint = id;
	}
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValuestring) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValuestring));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValuestring = fieldValuestring;
	}
	 


}
