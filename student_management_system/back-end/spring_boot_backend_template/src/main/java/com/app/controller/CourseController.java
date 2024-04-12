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

import com.app.dto.CourseDto;
import com.app.entities.Course;
import com.app.service.CourseService;

@RestController
@RequestMapping("/sms/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	// create operation of course

	@PostMapping
	public ResponseEntity<?> addCourse(@RequestBody CourseDto course) {
		try {
			courseService.addCourse(course);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// update operations

	// update by id
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCourseById(@RequestBody CourseDto course, @PathVariable Integer id) {
		try {
			// check for the existance of course by id
			CourseDto existingCourse = courseService.getCourseById(id);
			if (existingCourse == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("course not found");

			String courseTitle = course.getCourseTitle();
			String startDate = course.getStartDate();
			String endDate = course.getEndDate();
			double fees = course.getFees();
			double minScore = course.getMinScore();
//			System.out.println("In course controller: " + id + " " + courseTitle + " " + startDate + " " + endDate + " "
//					+ fees + " " + minScore);
			CourseDto updateCourse = courseService
					.updateCourseById(new CourseDto(id, courseTitle, startDate, endDate, fees, minScore));
			return new ResponseEntity<>(updateCourse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Read operations

	// get by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getCourseById(@PathVariable Integer id) {
		try {
			CourseDto course = courseService.getCourseById(id);
			return new ResponseEntity<>(course, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}
	}

	// get all
	@GetMapping
	public ResponseEntity<?> getAllCourses() {
		try {
			List<CourseDto> courses = courseService.getAllCourses();
			return new ResponseEntity<>(courses, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// delete operation of course

	// delete by id
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourseById(@PathVariable Integer id) {
		try {
			courseService.deleteCourseById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
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
