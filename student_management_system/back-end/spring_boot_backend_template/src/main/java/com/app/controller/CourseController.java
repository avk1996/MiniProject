package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Course;
import com.app.service.CourseServiceImple;

@RequestMapping("/sms")
@RestController
public class CourseController {
	
	@Autowired
	private CourseServiceImple courseService;
	
	// create operation of course
	@PostMapping
	public String addCourse(@RequestBody Course course) {
		courseService.addCourse(course);
		return "success";
	}
	
	// update operations
	
	// update by id
	
	// Read operations
	
	// get by id
	@GetMapping("{id}")
	public Course getCourseById(@RequestParam int id){
		return courseService.getCourseById(id);		
	}
	
	// get all
	@GetMapping
	public List<Course> getAllCourses(){
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
		return null;
	}
	
	
	
}
