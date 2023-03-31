package com.imranmadbar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(authProvider());
    	auth.authenticationProvider(customAuthenticationProvider);
    }
    
  @Bean
  public DaoAuthenticationProvider authProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
      return authProvider;
  }
  
  @Bean
  @Override
  public UserDetailsService userDetailsService() {
      // By default super adming setup
      UserDetails theUser = User.withUsername("imranmadbar")
                      .password("imranmadbar").roles("SUPERADMIN").build();
      InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
      userDetailsManager.createUser(theUser);
      return userDetailsManager;
  }
  


	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/book/**").permitAll()
        .antMatchers("/admin/**").hasAnyRole("ADMIN")
        .antMatchers("/manager/").hasAnyRole("MANAGER")
        .antMatchers("/super-admin/**").hasAnyRole("SUPERADMIN")
        .antMatchers("/role/**").hasAnyRole("SUPERADMIN")
        .antMatchers("/user/**").hasAnyRole("SUPERADMIN")
        .antMatchers("/user-role/**").hasAnyRole("SUPERADMIN")
        .antMatchers("/anonymous*").anonymous()
        .antMatchers("/login/").permitAll()
        .antMatchers("/resources/**", "/static/**","/webjars/**").permitAll()
        .anyRequest().authenticated()
        .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(customAuthenticationSuccessHandler)
			.failureHandler(customAuthenticationFailureHandler)
        .and()
            .logout()
            .logoutSuccessUrl("/home")
            .invalidateHttpSession(true)
            .permitAll()
        .and()
        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
        .and()
            .csrf()
            .disable();
    }
	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	 return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	

}
