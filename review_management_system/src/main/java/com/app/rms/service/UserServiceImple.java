package com.app.rms.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.rms.dto.UserDTO;
import com.app.rms.entity.User;
import com.app.rms.repository.UserRepository;

public class UserServiceImple implements UserService {

	@Autowired
	private UserRepository userRepository;

	private final ModelMapper mapper = new ModelMapper();

	private UserDTO mapUserToUserDTO(User user) {
		return mapper.map(user, UserDTO.class);
	}

	@Override
	public UserDTO registerUser(User user) {
		return mapUserToUserDTO(userRepository.save(user));
	}

	@Override
	public List<UserDTO> getUsers() {
		List<UserDTO> users = userRepository.findAll().stream().map(user -> mapUserToUserDTO(user)).toList();
		return users;
	}

	@Override
	public UserDTO getUser(Integer userId) {
		User user = userRepository.getReferenceById(userId);
		return mapUserToUserDTO(user);
	}

	@Override
	public UserDTO editUserDetails(User user) {
		Integer userId = user.getUserId();
		User editUser = userRepository.findById(userId).map(existingUser -> {
			existingUser.setDateOfBirth(user.getDateOfBirth());
			existingUser.setEmail(user.getEmail());
			existingUser.setName(user.getName());
			existingUser.setGender(user.getGender());
			existingUser.setPhoneNumber(user.getPhoneNumber());
			return userRepository.save(existingUser);
		}).orElseThrow(() -> null);
		userRepository.save(editUser);
		return mapUserToUserDTO(editUser);
	}

	@Override
	public UserDTO deleteUserDetail(Integer userId) {
		User user = userRepository.getReferenceById(userId);
		if (user != null)
			userRepository.deleteById(userId);
		return mapUserToUserDTO(user);
	}

}
