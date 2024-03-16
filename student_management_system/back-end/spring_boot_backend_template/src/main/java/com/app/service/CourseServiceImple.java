package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CourseDao;
import com.app.entities.Course;

@Transactional
@Service
public class CourseServiceImple implements CourseService {

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
	public Course getCourseById(Integer id) {
		return courseDao.findById(id).get();
	}

	@Override
	public List<Course> getAllCourses() {
		return courseDao.findAll();
	}

	@Override
	public void deleteAllCourses() {
		courseDao.deleteAll();
	}

	@Override
	public void deleteCourseById(Integer id) {
		courseDao.deleteById(id);
	}

	@Override
	public Course updateCourseById(Course course,Integer id) {
//		Course existingCourse = courseDao.findById(id).get();
//		
//		if(course.getCourseId() == null)
//			return null;
//		else {
//			existingCourse.setCourseTitle(course.getCourseTitle());
//			existingCourse.setEndDate(course.getEndDate());
//			existingCourse.setStartDate(course.getStartDate());
//			existingCourse.setFees(course.getFees());
//			existingCourse.setMinScore(course.getMinScore());
//			
			
			
			return courseDao.save(course);
//		}	
	}
}
