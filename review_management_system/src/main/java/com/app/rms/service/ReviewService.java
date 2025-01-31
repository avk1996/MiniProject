package com.app.rms.service;

import java.util.List;

import com.app.rms.dto.ReviewDTO;

public interface ReviewService {

	public void postReview(ReviewDTO review);

	public List<ReviewDTO> getReviews();
}
