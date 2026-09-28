package com.dcl.expencetracker.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.expencetracker.dto.LoginRequestDTO;
import com.dcl.expencetracker.dto.LoginResponseDTO;
import com.dcl.expencetracker.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	 private final AuthService authService;

	    public AuthController(AuthService authService) {
	        this.authService = authService;
	    }

	    @PostMapping("/login")
	    public LoginResponseDTO login(
	            @Valid @RequestBody LoginRequestDTO dto) {

	        return authService.login(dto);
	    }

}
