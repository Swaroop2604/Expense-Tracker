package com.dcl.expencetracker.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dcl.expencetracker.dto.UserRequestDTO;
import com.dcl.expencetracker.dto.UserResponseDTO;
import com.dcl.expencetracker.entity.User;
import com.dcl.expencetracker.exception.ResourceNotFoundException;
import com.dcl.expencetracker.mapper.UserMapper;
import com.dcl.expencetracker.repository.UserRepository;
@Service
public class UserService {
	 private final UserRepository userRepository;
	 private final PasswordEncoder passwordEncoder;

	    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }

	    public UserResponseDTO createUser(UserRequestDTO dto) {

	        User user = UserMapper.toEntity(dto);
	        
	        user.setPassword(passwordEncoder.encode(user.getPassword()));

	        User savedUser = userRepository.save(user);

	        return UserMapper.toDTO(savedUser);
	    }

	    public List<UserResponseDTO> getAllUsers() {
	        return userRepository.findAll()
	        		 .stream()
	                 .map(UserMapper::toDTO)
	                 .toList();
	    }

	    public UserResponseDTO getUserById(Long id) {
	    	User user = userRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "User not found with id: " + id));

	        return UserMapper.toDTO(user);
	    }

	    public void deleteUser(Long id) {
	    	 User user = userRepository.findById(id)
	    	            .orElseThrow(() -> new ResourceNotFoundException(
	    	                    "User not found with id: " + id));

	    	    userRepository.delete(user);

}
	    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {

	        User user = userRepository.findById(id)
	                .orElseThrow(() ->
	                    new ResourceNotFoundException("User not found with id: " + id));

	        user.setName(dto.getName());
	        user.setEmail(dto.getEmail());
	        user.setPassword(passwordEncoder.encode(dto.getPassword()));

	        User updatedUser = userRepository.save(user);

	        return UserMapper.toDTO(updatedUser);
	    }
	    
	    public User updateUser(Long id, User user) {

	        User existingUser = userRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "User not found with id: " + id));

	        existingUser.setName(user.getName());
	        existingUser.setEmail(user.getEmail());
	        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));

	        return userRepository.save(existingUser);
	    }
}
