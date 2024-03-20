package com.app.service;

import java.util.List;

import com.app.dto.CourseDto;
import com.app.entities.Course;


public interface CourseService {
	// basic curd operations
	
	// create operation for new course
	public String addCourse(Course course);
	
	// update operation for existing course
	public Course updateCourseById(Course course);
	
	// read operation for course per id and all courses
	public Course getCourseById(Integer id);
	
	public List<Course> getAllCourses();
	
	// delete operation for all courses
	
	public void deleteAllCourses();
	
	public void deleteCourseById(Integer id);
}
