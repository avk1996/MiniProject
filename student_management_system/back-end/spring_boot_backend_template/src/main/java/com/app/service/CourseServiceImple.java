package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CourseDao;
import com.app.entities.Course;

@Transactional
@Service
public class CourseServiceImple {

	@Autowired
	private CourseDao courseDao;

	public String addCourse(Course course) {
		if (courseDao.save(course) != null)
			return "success";
		else
			return "failure";
	}

}
