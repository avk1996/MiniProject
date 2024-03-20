package com.app.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class Course{
	
	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseId;
	
	@Column(name = "course_title")
	private String courseTitle;
	
	@Column(name = "start_date")
	private String startDate;
	
	@Column(name="end_date")
	private String endDate;
	
	@Column
	private double fees;
	
	@Column(name = "min_score")
	private double minScore;
	
	@ManyToOne
	private List<Student> students;
}
