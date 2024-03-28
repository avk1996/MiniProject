package com.app.service;

import java.util.List;

import com.app.dto.StudentDto;
import com.app.entities.Student;

public interface StudentService {
	public void addStudent(StudentDto student);
	
	public StudentDto updateStudent(StudentDto student);
	
	public List<Student> showStudents();

	public StudentDto showStudentById(Integer id);

	public void deleteStudents();

	public void deleteStudentById(Integer id);
}
