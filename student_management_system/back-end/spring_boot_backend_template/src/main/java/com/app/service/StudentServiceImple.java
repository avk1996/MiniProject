package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.StudentDao;
import com.app.dto.StudentDto;
import com.app.entities.Student;

@Transactional
@Service
public class StudentServiceImple implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void addStudent(StudentDto student) {
		Student newStudent = mapper.map(student, Student.class);
		studentDao.save(newStudent);
	}

	@Override
	public StudentDto updateStudent(StudentDto student) {
		Student existingStudent = studentDao.findById(student.getStudentsId()).get();

		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setScoreObtained(student.getScoreObtained());

		return mapStudentToDTO(existingStudent);
	}

	@Override
	public List<StudentDto> showStudents() {
		return studentDao.findAll().stream().map(this::mapStudentToDTO).collect(Collectors.toList());
	}

	@Override
	public StudentDto showStudentById(Integer id) {
		Student student = studentDao.findById(id).get();
		return mapStudentToDTO(student);
	}

	private StudentDto mapStudentToDTO(Student student) {
		StudentDto studentDto = mapper.map(student, StudentDto.class);
		return studentDto;
	}

	@Override
	public void deleteStudents() {
		studentDao.deleteAll();
	}

	@Override
	public void deleteStudentById(Integer id) {
		Student existingStudent = studentDao.findById(id).orElseThrow();
		studentDao.delete(existingStudent);
	}

}
