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

import com.app.dto.StudentDto;
import com.app.entities.Student;
import com.app.service.StudentService;

@RestController
@RequestMapping("/sms/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// create new student

	@PostMapping
	public ResponseEntity<?> addStudent(@RequestBody StudentDto student) {
		try {
			studentService.addStudent(student);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// update student info

	@PutMapping("/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody StudentDto student) {
		try {
			// check if the student is present or not
			StudentDto oldStudent = studentService.showStudentById(id);
			if (oldStudent == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found student");

			// pass from request body the required fields for updatation
			String firstName = student.getFirstName();
			String lastName = student.getLastName();
			String email = student.getEmail();
			double scoreObtained = student.getScoreObtained();

			StudentDto updateStudent = studentService
					.updateStudent(new StudentDto(id, firstName, lastName, email, scoreObtained));

			return new ResponseEntity<>(updateStudent, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}

	// read student info

	@GetMapping
	public ResponseEntity<?> showStudents() {
		try {
			List<StudentDto> studentsList = studentService.showStudents();
			return new ResponseEntity<>(studentsList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> showStudentById(@PathVariable Integer id) {
		try {
			StudentDto student = studentService.showStudentById(id);
			return new ResponseEntity<>(student, HttpStatus.OK);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// delete student info

	@DeleteMapping
	public String deleteStudents() {
		try {
			studentService.deleteStudents();
			return "success";
		} catch (Exception e) {
			return "failure";
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudentById(@PathVariable Integer id) {
		try {
			studentService.deleteStudentById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("student not found");
		}
	}
}
