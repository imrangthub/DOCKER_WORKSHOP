package com.imranmadbar.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class WebFilterSpring extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		System.out.println("Request URI is: " + httpServletRequest.getRequestURI());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		String userRoles = auth.getAuthorities().toString();

		System.out.println("Current UserName:" + username);
		System.out.println("Role:  " + userRoles);

		filterChain.doFilter(httpServletRequest, httpServletResponse);

	}
}