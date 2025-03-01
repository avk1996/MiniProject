package com.app.rms.service;

import java.util.List;

import com.app.rms.dto.UserDTO;
import com.app.rms.dto.UserPasswordDTO;
import com.app.rms.entity.User;

public interface UserService {

	public UserDTO registerUser(User user);

	public List<UserDTO> getUsers();

	public UserDTO getUser(Integer userId);

	public UserDTO editUserDetails(User user);

	public UserDTO deleteUserDetail(Integer userId);

	public void resetPassword(UserPasswordDTO passwords);
}
