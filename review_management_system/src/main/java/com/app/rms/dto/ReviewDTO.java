package com.app.rms.dto;

import lombok.Data;

@Data
public class ReviewDTO {
	private String titleReview;
	private String reviewContent;
	private int rating;
}
