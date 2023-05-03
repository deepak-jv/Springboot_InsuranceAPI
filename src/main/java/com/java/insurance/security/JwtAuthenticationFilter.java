package com.java.insurance.security;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    @Autowired
	private UserDetailsService userDetailsService;
    
    @Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		
		
//		get token
		
		String requestToken = request.getHeader("Authorization");
		
//		Bearer
//		logger.info("token--------------------------"+requestToken);
		
		String username = null;
		
		String token = null;
		
		if(requestToken != null && requestToken.startsWith("Bearer")) {
			 token = requestToken.substring(7);
			 try {
				 
				 System.out.println("----------------------------------inside the if block in authfilter");
			 username = jwtTokenHelper.getUserNameFromToken(token);	
			 }catch (IllegalArgumentException e) {
				 throw new IllegalArgumentException( "Unable to get jwt token"+e);
			}catch (ExpiredJwtException e) {
				throw new ExpiredJwtException(null, null, "Jwt token has expired"+e);
			}catch (MalformedJwtException e) {
				throw new MalformedJwtException("Invalid Jwt token "+e);			}
			 
		}else {
			 System.out.println("-----------------------------------inside the else block in authfilter");
			 System.out.println("-----------------------------------"+requestToken);

			logger.error("Jwt does not contain Bearer");
		}
		
//		Validating the token
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if(jwtTokenHelper.validateToken(token, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken  = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}else {
				logger.error("Invalid Jwt token ");
			}
			
			
		}else {
			logger.error("Username is null or context is not null");
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}
