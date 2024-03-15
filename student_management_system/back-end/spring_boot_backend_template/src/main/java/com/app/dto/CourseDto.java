package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
	private Integer courseId;
	private String courseTitle;
	private String startDate;
	private String endDate;
	private double fees;
	private double minScore;
}
