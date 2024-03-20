package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.StudentDao;
import com.app.entities.Student;

@Transactional
@Service
public class StudentServiceImple implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public String addStudent(Student student) {
		if (studentDao.save(student) != null)
			return "success";
		else
			return "failure";
	}

	@Override
	public Student updateStudent(Student student) {
		return studentDao.save(student);
	}

	@Override
	public List<Student> showStudents() {
		return studentDao.findAll();
	}

	@Override
	public Student showStudentById(Integer id) {
		return studentDao.findById(id).get();
	}

	@Override
	public void deleteStudents() {
		studentDao.deleteAll();
	}

	@Override
	public void deleteStudentById(Integer id) {
		studentDao.deleteById(id);
	}

}
