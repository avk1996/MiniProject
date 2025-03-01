package com.app.rms.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.rms.dto.UserDTO;
import com.app.rms.dto.UserPasswordDTO;
import com.app.rms.entity.User;
import com.app.rms.repository.UserRepository;

import ch.qos.logback.classic.Logger;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImple implements UserService {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(UserServiceImple.class);
	
	@Autowired
	private UserRepository userRepository;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private final ModelMapper mapper = new ModelMapper();

	private UserDTO mapUserToUserDTO(User user) {
		return mapper.map(user, UserDTO.class);
	}

	@Override
	public UserDTO registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
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
			return existingUser;
		}).orElseThrow(() -> null);
		userRepository.save(editUser);
		return mapUserToUserDTO(editUser);
	}

	@Override
	public UserDTO deleteUserDetail(Integer userId) {
		User user = userRepository.getReferenceById(userId);
		userRepository.deleteById(userId);
		return mapUserToUserDTO(user);
	}

	@Override
	public void resetPassword(UserPasswordDTO passwordsDTO){
		Integer userId = passwordsDTO.getUserId();
		User isUserExists = userRepository.getReferenceById(userId);
		logger.info("New password: {}, Old password: {}", passwordsDTO.getNewPassword(), passwordsDTO.getOldPassword());
		logger.info("New password: {}, Old password: {}", passwordEncoder.encode(passwordsDTO.getOldPassword()),
				isUserExists.getPassword());
		boolean isPasswordMatch = passwordEncoder.matches(passwordsDTO.getOldPassword(),
				isUserExists.getPassword());
		if (isPasswordMatch) {
			User resetPass = userRepository.findById(userId).map(reset -> {
				reset.setPassword(passwordEncoder.encode(passwordsDTO.getNewPassword()));
				return reset;
			}).orElse(null);
			userRepository.save(resetPass);
		}else {
			throw new IllegalArgumentException("Invalid password. Please try again.");
		}
	}

}
