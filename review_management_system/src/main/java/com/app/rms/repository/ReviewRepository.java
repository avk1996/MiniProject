package com.app.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rms.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
}
