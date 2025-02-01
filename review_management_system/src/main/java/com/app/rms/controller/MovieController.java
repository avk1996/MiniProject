package com.app.rms.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rms.dto.MovieDTO;
import com.app.rms.entity.Movie;
import com.app.rms.service.MovieService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/api/v1/rms")
public class MovieController {

	@Autowired
	MovieService movieService;
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(MovieController.class);

	@PostMapping("/register_movie")
	public ResponseEntity<MovieDTO> registerMovie(@RequestBody Movie movie) {
		try {
			MovieDTO registerMovie = movieService.registerMovie(movie);
			return new ResponseEntity<>(registerMovie, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/get_movies")
	public ResponseEntity<List<MovieDTO>> getMovies() {
		try {
			List<MovieDTO> movies = movieService.getMovies();
			return new ResponseEntity<>(movies, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.info("Exception: "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/get_movie/{movieId}")
	public ResponseEntity<MovieDTO> getMovie(@PathVariable Integer movieId){
		try {
			MovieDTO movie = movieService.getMovie(movieId);
			return new ResponseEntity<>(movie,HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.info("Exception: "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
