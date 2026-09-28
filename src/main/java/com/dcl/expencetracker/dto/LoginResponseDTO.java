package com.dcl.expencetracker.dto;

public class LoginResponseDTO {
	 private String message;
	    private Long userId;
	    private String name;
	    private String email;
	    private String token;

	    public LoginResponseDTO() {
	    }

	    public LoginResponseDTO(String message, Long userId,
	                            String name, String email,String token) {
	        this.message = message;
	        this.userId = userId;
	        this.name = name;
	        this.email = email;
	        this.token=token;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public Long getUserId() {
	        return userId;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getEmail() {
	        return email;
	    }
	    public String getToken() {
	        return token;
	    }

}
