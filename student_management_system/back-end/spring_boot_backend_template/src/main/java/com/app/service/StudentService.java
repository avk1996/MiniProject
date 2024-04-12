package com.app.service;

import java.util.List;

import com.app.dto.StudentDto;

public interface StudentService {
	public void addStudent(StudentDto student);

	public StudentDto updateStudent(StudentDto student);

	public List<StudentDto> showStudents();

	public StudentDto showStudentById(Integer id);

	public void deleteStudents();

	public void deleteStudentById(Integer id);
}
