package com.app.rms.service;

import java.util.List;

import com.app.rms.dto.MovieDTO;
import com.app.rms.entity.Movie;

public interface MovieService {

	public MovieDTO registerMovie(Movie movie);

	public List<MovieDTO> getMovies();
	
	public MovieDTO getMovie(Integer movieId);

	public MovieDTO editMovieDetails(Movie movie);

	public MovieDTO deleteMovieDetails(Integer movieId);

}
