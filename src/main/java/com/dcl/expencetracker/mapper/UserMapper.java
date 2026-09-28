package com.dcl.expencetracker.mapper;

import com.dcl.expencetracker.dto.UserRequestDTO;
import com.dcl.expencetracker.dto.UserResponseDTO;
import com.dcl.expencetracker.entity.User;

public class UserMapper {
	public static UserResponseDTO toDTO(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
	public static User toEntity(UserRequestDTO dto) {

	    User user = new User();

	    user.setName(dto.getName());
	    user.setEmail(dto.getEmail());
	    user.setPassword(dto.getPassword());

	    return user;
	}

}
