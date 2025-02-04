package com.app.rms.controller;

import java.util.List;

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
import com.app.rms.entity.User;
import com.app.rms.service.UserService;

@RestController
@RequestMapping("/api/v1/rms")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register_user")
	public ResponseEntity<UserDTO> registerUser(@RequestBody User user) {
		try {
			UserDTO newUser = userService.registerUser(user);
			return new ResponseEntity<>(newUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
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
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/edit_user_details/{userId}")
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
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
