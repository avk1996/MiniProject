package com.app.service;

import java.util.List;

import com.app.dto.CourseDto;
import com.app.entities.Course;


public interface CourseService {
	// basic curd operations
	
	// create operation for new course
	public void addCourse(CourseDto course);
	
	// update operation for existing course
	public CourseDto updateCourseById(CourseDto course);
	
	// read operation for course per id and all courses
	public CourseDto getCourseById(Integer id);
	
	public List<CourseDto> getAllCourses();
	
	// delete operation for all courses
	
	public void deleteAllCourses();
	
	public void deleteCourseById(Integer id);
}
