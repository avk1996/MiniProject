package com.app.rms.service;

import java.util.List;

import com.app.rms.dto.ReviewDTO;
import com.app.rms.entity.Review;

public interface ReviewService {

	public ReviewDTO postReview(ReviewDTO review);

	public List<ReviewDTO> getReviews();
	
	public ReviewDTO getReviewById(Integer reviewId);
	
	public ReviewDTO editReview(Review review);

	public ReviewDTO deleteReview(Integer reviewId);
}
