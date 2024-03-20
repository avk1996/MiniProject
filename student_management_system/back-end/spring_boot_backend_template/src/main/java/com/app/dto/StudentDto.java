package com.app.dto;

import com.app.entities.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	private Integer studentsId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private double scoreObtained;
}
