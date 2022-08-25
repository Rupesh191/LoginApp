package com.project.backend.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.backend.AuthUtils;
import com.project.backend.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;



@Component
public class JwtAuthenticationFilter 
//extends OncePerRequestFilter
{
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthUtils authUtils;

//	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String requestToken=request.getHeader("Authorization");
		String id=null;
		String token=null;
		System.out.println("in Jwt authentication filter");

		
		
		if(requestToken !=null && requestToken.startsWith("Bearer"))
		{
		     token = requestToken.substring(7);
		     try
		     {
		     id = this.authUtils.getIdFromToken(token);
		     }
		     catch(IllegalArgumentException e)
		     {
		    	 System.out.println("Unable to get Jwt token");
		     }
		     catch(ExpiredJwtException e)
		     {
		    	 System.out.println("Jwt token has expired");
		     }
		     catch(MalformedJwtException e)
		     {
		    	 System.out.println("invalid jwt");
		     }
		
	}else 
		{
		System.out.print("Jwt token does mot begin with bearer");
		}
		
		
//		if (null != username &&SecurityContextHolder.getContext().getAuthentication() == null) {
//	         UserService userService = this.userService.loadUserByUsername(username);
//	         if (authUtils.validateJwtToken(token, user)) {
//	            UsernamePasswordAuthenticationToken
//	            authenticationToken = new UsernamePasswordAuthenticationToken(
//	            user, null,
//	            user.getAuthorities());
//	            authenticationToken.setDetails(new
//	            WebAuthenticationDetailsSource().buildDetails(request));
//	            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//	         }
//	
//		else
//		{
//			System.out.println("invalid jwt token");
//		}
		
		filterChain.doFilter(request, response);
	}
	}

	
	

