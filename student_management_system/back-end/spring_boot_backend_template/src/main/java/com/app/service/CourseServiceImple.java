package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CourseDao;
import com.app.dto.CourseDto;
import com.app.entities.Course;

@Transactional
@Service
public class CourseServiceImple implements CourseService {

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private ModelMapper mapper;

	private CourseDto mapModelToDTO(Course course) {
		return mapper.map(course, CourseDto.class);
	}

	@Override
	public void addCourse(CourseDto course) {
		Course newCourse = mapper.map(course, Course.class);
		courseDao.save(newCourse);
	}

	@Override
	public CourseDto getCourseById(Integer id) {
		Course course = courseDao.findById(id).get();
		return mapModelToDTO(course);
	}

	@Override
	public List<CourseDto> getAllCourses() {
		return courseDao.findAll().stream().map(this::mapModelToDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteAllCourses() {
		courseDao.deleteAll();
	}

	@Override
	public void deleteCourseById(Integer id) {
		Course existingCourse = courseDao.findById(id).orElseThrow();
		courseDao.delete(existingCourse);
	}

	@Override
	public CourseDto updateCourseById(CourseDto course) {
		Course existingCourse = courseDao.findById(course.getCourseId()).get();

//		System.out.println("In course service: " + course.getCourseId() + " " + course.getCourseTitle() + " "
//				+ course.getStartDate() + " " + course.getEndDate() + " " + course.getFees() + " "
//				+ course.getMinScore());

		existingCourse.setCourseTitle(course.getCourseTitle());
		existingCourse.setStartDate(course.getStartDate());
		existingCourse.setEndDate(course.getEndDate());
		existingCourse.setFees(course.getFees());
		existingCourse.setMinScore(course.getMinScore());

		return mapModelToDTO(existingCourse);
	}
}
