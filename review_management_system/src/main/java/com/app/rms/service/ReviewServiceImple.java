package com.app.rms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rms.dto.ReviewDTO;
import com.app.rms.entity.Review;
import com.app.rms.repository.ReviewRepository;

import ch.qos.logback.classic.Logger;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReviewServiceImple implements ReviewService {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(ReviewServiceImple.class);
	
	@Autowired
	private ReviewRepository reviewRepository;

	private final ModelMapper mapper = new ModelMapper();

	private Review mapReviewDTOToModel(ReviewDTO reviewDto) {
		return mapper.map(reviewDto, Review.class);
	}
	
	private ReviewDTO mapReviewToModelDTO(Review review) {
		return mapper.map(review, ReviewDTO.class);
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

	@Override
	public ReviewDTO getReviewById(Integer reviewId) {
		Review review = reviewRepository.getReferenceById(reviewId);
		return mapReviewToModelDTO(review);
	}

	@Override
	public ReviewDTO editReview(Review review) {
		Integer reviewId = review.getReviewId();
		logger.info("Id : "+reviewId);
		
		Review editReview = reviewRepository.findById(reviewId)
				.map(existingReview ->{
					existingReview.setTitleReview(review.getTitleReview());
					existingReview.setReviewContent(review.getReviewContent());
					existingReview.setRating(review.getRating());
					return reviewRepository.save(existingReview);
				}).orElseThrow(()-> null);
		
		reviewRepository.save(editReview);
		return mapReviewToModelDTO(editReview);
	}

	@Override
	public ReviewDTO deleteReview(Integer reviewId) {
		Review review = reviewRepository.getReferenceById(reviewId);
		if(review != null) {			
			reviewRepository.deleteById(reviewId);
			return mapReviewToModelDTO(review);
		}
		return null;
	}

}
