package com.java.insurance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.java.insurance.security.CustomUserDetailService;
import com.java.insurance.security.JwtAuthenticationEntryPoint;
import com.java.insurance.security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableWebMvc
public class SecurityConfig{

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;


	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception {
			http
			.csrf()
			.disable()
			.authorizeHttpRequests((authorize)-> authorize.requestMatchers("/api/auth/**").permitAll()
					.requestMatchers(HttpMethod.GET,"/api/policies/**").permitAll()
					.requestMatchers(HttpMethod.POST,"/api/policies/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.PUT,"/api/policies/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST,"/api/policies/**").hasRole("AGENT")
					.requestMatchers(HttpMethod.PUT,"/api/policies/**").hasRole("AGENT")
					.requestMatchers(HttpMethod.GET,"/api/claims/**").permitAll()
					.requestMatchers(HttpMethod.POST,"/api/claims/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.PUT,"/api/claims/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST,"/api/claims/**").hasRole("AGENT")
					.requestMatchers(HttpMethod.PUT,"/api/claims/**").hasRole("AGENT")
					.requestMatchers("/api/payments/**").permitAll()
					.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
					.requestMatchers("/v3/api-docs").permitAll()
					.requestMatchers("/swagger-ui/**").permitAll()
					.anyRequest().authenticated())
			.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			http.addFilterBefore( jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
			http.authenticationProvider(daoAuthenticationProvider());
			
			DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
			return defaultSecurityFilterChain;
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;		
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {

		
		return configuration.getAuthenticationManager();
		
	}

	
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(); 
	}

	
	

}
