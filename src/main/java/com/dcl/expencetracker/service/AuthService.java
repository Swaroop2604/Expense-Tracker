package com.dcl.expencetracker.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dcl.expencetracker.dto.LoginRequestDTO;
import com.dcl.expencetracker.dto.LoginResponseDTO;
import com.dcl.expencetracker.entity.User;
import com.dcl.expencetracker.exception.InvalidCredentialsException;
import com.dcl.expencetracker.exception.ResourceNotFoundException;
import com.dcl.expencetracker.repository.UserRepository;

@Service
public class AuthService {
	 private final UserRepository userRepository;
	 private final PasswordEncoder passwordEncoder;
	 private final JwtService jwtService;

	    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtService jwtService) {
	        this.userRepository = userRepository;
	        this.passwordEncoder=passwordEncoder;
	        this.jwtService=jwtService;
	    }

	    public LoginResponseDTO login(LoginRequestDTO dto) {

	        User user = userRepository.findByEmail(dto.getEmail())
	                .orElseThrow(() ->
	                        new ResourceNotFoundException(
	                                "User not found with email: " + dto.getEmail()));

	        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
	            throw new InvalidCredentialsException("Invalid email or password");
	        }
	        
	        String token = jwtService.generateToken(user.getEmail());

	        return new LoginResponseDTO(
	                "Login successful",
	                user.getId(),
	                user.getName(),
	                user.getEmail(),
	                token
	        );
	    }

}
