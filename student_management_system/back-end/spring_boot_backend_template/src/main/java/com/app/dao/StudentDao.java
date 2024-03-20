package com.app.dao;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.app.entities.Student;

public interface StudentDao extends JpaRepositoryImplementation<Student, Integer>{

}
