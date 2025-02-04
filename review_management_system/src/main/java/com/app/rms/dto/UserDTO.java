package com.app.rms.dto;

import com.app.rms.enums.Gender;

import lombok.Data;

@Data
public class UserDTO {
	private String name;
	private Gender gender;
}
