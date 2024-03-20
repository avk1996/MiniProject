package com.app.controller;

import java.util.List;
import java.util.NoSuchElementException;

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

import com.app.entities.Student;
import com.app.service.StudentService;

@RestController
@RequestMapping("/sms")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	// create new student 
	
	@PostMapping("/student")
	public String addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}
	
	// update student info
	
	@PutMapping("/student/{id}")
	public Student updateStudent(@RequestBody Student student, @PathVariable Integer id){
		student.setStudentsId(id);
		return studentService.updateStudent(student);
	}
	
	// read student info
	
	@GetMapping("/student/")
	public List<Student> showStudents() {
		return studentService.showStudents();
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> showStudentById(@PathVariable Integer id) {
		try {
			Student student = studentService.showStudentById(id);
			return new ResponseEntity<Student>(student,HttpStatus.OK);
			
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	// delete student info
	
	@DeleteMapping("/student/")
	public String deleteStudents() {
		try {
			studentService.deleteStudents();
			return "success";
		} catch (Exception e) {
			return "failure";
		}
	}
	
	@DeleteMapping("/student/{id}")
	public String deleteStudentById(@PathVariable Integer id) {
		try {
			if(showStudentById(id) == null)
				throw new NoSuchElementException("No such student present");
			studentService.deleteStudentById(id);
			return "success";
		} catch (Exception e) {
			return "failure";
		}
	}
}
