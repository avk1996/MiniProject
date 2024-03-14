package com.app.service;

import java.util.List;

import com.app.entities.Course;


public interface CourseService {
	// basic curd operations
	
	// create operation for new course
	public String addCourse(Course course);
	
	// read operation for course per id and all courses
	public Course getCourseById(int id);
	
	public List<Course> getAllCourses();
}
