package com.app.rms.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rms.dto.UserDTO;
import com.app.rms.dto.UserPasswordDTO;
import com.app.rms.entity.User;
import com.app.rms.service.UserService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/api/v1/rms")
public class UserController {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register_user")
	public ResponseEntity<UserDTO> registerUser(@RequestBody User user) {
		try {
			logger.info(user.toString());
			UserDTO newUser = userService.registerUser(user);
			return new ResponseEntity<>(newUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error("Error adding user {}",e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get_users")
	public ResponseEntity<List<UserDTO>> getUsers() {
		try {
			List<UserDTO> users = userService.getUsers();
			return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get_user/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {
		try {
			UserDTO user = userService.getUser(userId);
			return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/reset_user_password")
	public ResponseEntity<Boolean> resetPassword(@RequestBody UserPasswordDTO passwords){
		try {
			logger.info(passwords.toString());
			userService.resetPassword(passwords);
			return new ResponseEntity<>(Boolean.TRUE,HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error("Error reseting password: {}",e.getMessage());
			return new ResponseEntity<>(Boolean.FALSE,HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/edit_user_details")
	public ResponseEntity<UserDTO> editUserDetails(@RequestBody User user) {
		try {
			UserDTO editExisitinUser = userService.editUserDetails(user);
			return new ResponseEntity<>(editExisitinUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}	

	@DeleteMapping("/delete_user_detail/{userId}")
	public ResponseEntity<UserDTO> deleteUserDetail(@PathVariable Integer userId) {
		try {
			UserDTO deleteUser = userService.deleteUserDetail(userId);
			return new ResponseEntity<>(deleteUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error("Error: {}",e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
