package com.app.rms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rms.dto.MovieDTO;
import com.app.rms.entity.Movie;
import com.app.rms.repository.MovieRepository;

@Service
public class MovieServiceImple implements MovieService {

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
		return mapMovieToDTO(movieRepository.getReferenceById(movieId));
	}
}
