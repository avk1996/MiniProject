package com.app.rms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rms.controller.MovieController;
import com.app.rms.dto.MovieDTO;
import com.app.rms.entity.Movie;
import com.app.rms.repository.MovieRepository;

import ch.qos.logback.classic.Logger;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MovieServiceImple implements MovieService {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(MovieServiceImple.class);

	@Autowired
	MovieRepository movieRepository;

	private final ModelMapper modelMapper = new ModelMapper();

	private MovieDTO mapMovieToDTO(Movie movie) {
		return modelMapper.map(movie, MovieDTO.class);
	}

	@Override
	public MovieDTO registerMovie(Movie movie) {
		return mapMovieToDTO(movieRepository.save(movie));
	}

	@Override
	public List<MovieDTO> getMovies() {
		return movieRepository.findAll().stream().map(Movie -> mapMovieToDTO(Movie)).collect(Collectors.toList());
	}

	@Override
	public MovieDTO getMovie(Integer movieId) {
		Movie movie = movieRepository.getReferenceById(movieId);
		return mapMovieToDTO(movie);
	}

	@Override
	public MovieDTO editMovieDetails(Movie movie) {
		Integer movieId = movie.getMovieId();

		Movie editMovie = movieRepository.findById(movieId).map(exisitingMovie -> {
			exisitingMovie.setMovieName(movie.getMovieName());
			exisitingMovie.setRatings(movie.getRatings());
			exisitingMovie.setGenre(movie.getGenre());
			exisitingMovie.setBudget(movie.getBudget());
			exisitingMovie.setReleaseDate(movie.getReleaseDate());
			return movieRepository.save(exisitingMovie);
		}).orElseThrow(() -> null);

		return mapMovieToDTO(movieRepository.save(editMovie));
	}

	@Override
	public MovieDTO deleteMovieDetails(Integer movieId) {
		Movie movie = movieRepository.findById(movieId).orElseThrow(() -> null);
		if (movie != null)
			movieRepository.deleteById(movieId);
		return mapMovieToDTO(movie);
	}
}
