package com.app.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rms.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
