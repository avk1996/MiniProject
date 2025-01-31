package com.app.rms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rms.dto.ReviewDTO;
import com.app.rms.entity.Review;
import com.app.rms.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReviewServiceImple implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	private final ModelMapper mapper = new ModelMapper();

	private Review mapReviewDTOToModel(ReviewDTO reviewDto) {
		return mapper.map(reviewDto, Review.class);
	}

	@Override
	public ReviewDTO postReview(ReviewDTO reviewDto) {
		Review newReview = mapReviewDTOToModel(reviewDto);
		reviewRepository.save(newReview);
		return reviewDto;
	}

	@Override
	public List<ReviewDTO> getReviews() {
		List<ReviewDTO> availableReviews = reviewRepository.findAll().stream()
				.map(Review -> mapper.map(Review, ReviewDTO.class)).collect(Collectors.toList());
		return availableReviews;
	}

}
