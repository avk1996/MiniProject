package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CourseDao;
import com.app.entities.Course;

@Transactional
@Service
public class CourseServiceImple implements CourseService{

	@Autowired
	private CourseDao courseDao;

	@Override
	public String addCourse(Course course) {
		if (courseDao.save(course) != null)
			return "success";
		else
			return "failure";
	}

	@Override
	public Course getCourseById(int id) {
		return courseDao.getReferenceById(id);
	}

	@Override
	public List<Course> getAllCourses() {
		return courseDao.findAll();
	}

}
