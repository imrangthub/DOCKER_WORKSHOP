package com.imranmadbar.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Component;

import com.imranmadbar.role.RoleEntity;
import com.imranmadbar.user.UserEntity;
import com.imranmadbar.user.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private NoOpPasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		if(userName==null || password==null ) {
			return null;
		}
		UserEntity user = userService.findByUserName(userName);

		List<RoleEntity> roles = user.getRoles();

//		System.out.println("userName: " + userName);
//		System.out.println("password: " + password);
//		System.out.println("UserEntity" + user);
//		System.out.println("roles" + roles);

		if (user != null) {

			if (passwordEncoder.matches(password, user.getPassword())) {

				List<RoleEntity> userRoles = user.getRoles();
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

				for (RoleEntity role : userRoles) {
					authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
				}

				return new UsernamePasswordAuthenticationToken(user.getUsername(), password, authorities);
			}

		}

		return null;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
