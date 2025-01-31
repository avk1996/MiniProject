package com.app.rms.dto;

import lombok.Data;

@Data
public class ReviewDTO {
	private Integer reviewId;
	private String reviewContent;
	private int rating;
}
