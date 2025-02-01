package com.app.rms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rms.dto.MovieDTO;
import com.app.rms.entity.Movie;
import com.app.rms.service.MovieService;

@RestController
@RequestMapping("/api/v1/rms")
public class MovieController {

	@Autowired
	MovieService movieService;

	@PostMapping("/register_movie")
	public ResponseEntity<MovieDTO> registerMovie(@RequestBody Movie movie) {
		try {
			MovieDTO registerMovie = movieService.registerMovie(movie);
			return new ResponseEntity<>(registerMovie, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<MovieDTO>(HttpStatus.BAD_REQUEST);
		}
	}
}
