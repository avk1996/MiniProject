package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dao.CourseDao;
import com.app.entities.Course;

@Transactional
@Service
public class CourseServiceImple implements CourseService {

	@Autowired
	private static CourseDao courseDao;

	@Override
	public String addCourse(Course course) {
		if (courseDao.save(course) != null)
			return "success";
		else
			return "failure";
	}

	@Override
	public Course getCourseById(Integer id) {
		Course course = courseDao.findOne(id);
		System.out.println("in course service");
		if(course == null) {
			System.out.println("get data: "+course);
			return null;			
		}
		return course;
	}

	@Override
	public List<Course> getAllCourses() {
		return courseDao.findAll();
	}

}
