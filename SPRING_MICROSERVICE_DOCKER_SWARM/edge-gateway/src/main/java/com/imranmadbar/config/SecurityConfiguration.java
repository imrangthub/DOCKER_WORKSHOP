package com.imranmadbar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers(
                        "/v2/api-docs",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/*/swagger-resources/**",
                        "/*/v2/api-docs")
                .permitAll()
                .and()
                .authorizeExchange()
                .anyExchange()
                .permitAll();
        http.httpBasic().disable();
        http.csrf().disable();
        return http.build();
    }
}
