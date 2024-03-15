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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CourseDto;
import com.app.entities.Course;
import com.app.service.CourseService;

@RequestMapping("/sms")
@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	// create operation of course
	@PostMapping
	public String addCourse(@RequestBody Course course) {
		return courseService.addCourse(course);
	}

	// update operations

	// update by id

	// Read operations

	// get by id
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
		try {
			Course course = courseService.getCourseById(id);
			return new ResponseEntity<Course>(course, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// get all
	@GetMapping
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}

	// delete operation of course

	// delete by id
	@DeleteMapping("{id}")
	public String deleteCourseById() {
		return null;
	}

	// delete all
	@DeleteMapping
	public String deleteAllCourses() {
		return courseService.deleteAllCourses();
	}

}
