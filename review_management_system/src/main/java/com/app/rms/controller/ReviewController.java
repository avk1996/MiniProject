package com.app.rms.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.rms.dto.ReviewDTO;
import com.app.rms.service.ReviewService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/api/v1/rms")
public class ReviewController {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	private ReviewService reviewService;

	@PostMapping("/post_review")
	public ResponseEntity<ReviewDTO> postReview(@RequestBody ReviewDTO reviewDto) {
		try {
			//logger.info("In controller: "+reviewDto.toString());
			ReviewDTO review = reviewService.postReview(reviewDto);
			return new ResponseEntity<>(review, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<ReviewDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get_reviews")
	public ResponseEntity<List<ReviewDTO>> getReviews() {
		try {
			List<ReviewDTO> reviews = reviewService.getReviews();
			if (reviews.isEmpty())
				return ResponseEntity.noContent().build();
			//logger.info("Retrived {} size", reviews.size());
			return ResponseEntity.ok(reviews);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/get_review/{reviewId}")
	public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Integer reviewId){
		try {
			ReviewDTO review = reviewService.getReviewById(reviewId);
			return new ResponseEntity<>(review,HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<ReviewDTO>(HttpStatus.BAD_REQUEST);
		}
	}
}