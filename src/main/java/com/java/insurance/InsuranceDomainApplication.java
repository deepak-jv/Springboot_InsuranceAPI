package com.java.insurance;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.java.insurance.config.Constants;
import com.java.insurance.model.Role;
import com.java.insurance.repository.RoleRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class InsuranceDomainApplication implements CommandLineRunner{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(InsuranceDomainApplication.class, args);
	}

	
	
	
	
	@Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }





	@Override
	public void run(String... args) throws Exception {
		try {
			
			Role role = new Role();
			role.setId(Constants.ADMIN_USER);
			role.setName("ADMIN_USER");
			
			Role role1 = new Role();
			role1.setId(Constants.NORMAL_USER);
			role1.setName("NORMAL_USER");
			
			Role role2 = new Role();
			role2.setId(Constants.AGENT_USER);
			role2.setName("AGENT_USER");
			
			List<Role> roles = List.of(role,role1,role2);			
			List<Role> result = roleRepository.saveAll(roles);
//			result.forEach(r-> {
//				System.out.println(r.getId());
//				System.out.println(r.getName());
//			});
			
			
		}catch (Exception e) {

			e.printStackTrace();
		}
		
		
	}
		
}
