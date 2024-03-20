package com.app.service;

import java.util.List;


import com.app.entities.Student;

public interface StudentService {
	public String addStudent(Student student);
	
	public Student updateStudent(Student student);
	
	public List<Student> showStudents();

	public Student showStudentById(Integer id);

	public void deleteStudents();

	public void deleteStudentById(Integer id);
}
