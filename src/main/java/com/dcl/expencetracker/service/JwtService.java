package com.dcl.expencetracker.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	  private final SecretKey secretKey =
	            Keys.hmacShaKeyFor(
	                    "mySecretKeyForExpenseTrackerApplication123456"
	                            .getBytes()
	            );

	    public String generateToken(String email) {

	        return Jwts.builder()
	                .subject(email)
	                .issuedAt(new Date())
	                .expiration(
	                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
	                )
	                .signWith(secretKey)
	                .compact();
	    }

	    public String extractEmail(String token) {

	        return Jwts.parser()
	                .verifyWith(secretKey)
	                .build()
	                .parseSignedClaims(token)
	                .getPayload()
	                .getSubject();
	    }

	    public boolean isTokenValid(String token, String email) {

	        return extractEmail(token).equals(email);
	    }

}
