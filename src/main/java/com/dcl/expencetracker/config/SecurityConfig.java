package com.dcl.expencetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dcl.expencetracker.service.JwtService;

@Configuration
public class SecurityConfig {
	   private final JwtService jwtService;

	    public SecurityConfig(JwtService jwtService) {
	        this.jwtService = jwtService;
	    }

	    @Bean
	    public JwtAuthenticationFilter jwtAuthenticationFilter() {
	        return new JwtAuthenticationFilter(jwtService);
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http)
	            throws Exception {

	        http
	            .csrf(csrf -> csrf.disable())
	            .formLogin(form -> form.disable())
	            .httpBasic(basic -> basic.disable())
	            .sessionManagement(session ->
	                session.sessionCreationPolicy(
	                    SessionCreationPolicy.STATELESS
	                )
	            )
	            
	            .authorizeHttpRequests(auth -> auth
	            	    .requestMatchers(
	            	        "/swagger-ui/**",
	            	        "/swagger-ui.html",
	            	        "/v3/api-docs/**"
	            	    ).permitAll()

	            	    .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
	            	    .requestMatchers(HttpMethod.POST, "/api/users").permitAll()

	            	    .anyRequest().authenticated()
	            	)
	            .addFilterBefore(
	                jwtAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class
	            );

	        return http.build();
	    }
}
