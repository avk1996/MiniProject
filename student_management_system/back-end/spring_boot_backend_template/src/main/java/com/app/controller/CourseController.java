package com.app.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.CourseDao;
import com.app.dto.CourseDto;
import com.app.entities.Course;
import com.app.service.CourseService;

@RestController
@RequestMapping("/sms")
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
	@PutMapping("/{id}")
	public Course updateCourseById(@RequestBody Course course, @PathVariable Integer id) {
		course.setCourseId(id);
		Course updateCourse = courseService.updateCourseById(course, id);
		if (updateCourse != null)
			return updateCourse;
		else
			return null;
	}

	// Read operations

	// get by id
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
		try {
			Course course = courseService.getCourseById(id);
			return new ResponseEntity<Course>(course, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}
	}

	// get all
	@GetMapping
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}

	// delete operation of course

	// delete by id
	@DeleteMapping("/{id}")
	public String deleteCourseById(@PathVariable Integer id) {
		try {
			courseService.deleteCourseById(id);
			return "success deleting " + id;
		} catch (NoSuchElementException e) {
			return "failure deleting " + id + " error: " + e.getLocalizedMessage();
		}
	}

	// delete all
	@DeleteMapping
	public String deleteAllCourses() {
		try {
			courseService.deleteAllCourses();
			return "success";
		} catch (Exception e) {
			return "failed to delete all courses";
		}
	}

}
