package com.app.rms.service;

import java.util.List;

import com.app.rms.dto.ReviewDTO;

public interface ReviewService {

	public ReviewDTO postReview(ReviewDTO review);

	public List<ReviewDTO> getReviews();
}
