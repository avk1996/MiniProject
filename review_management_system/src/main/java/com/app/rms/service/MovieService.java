package com.app.rms.service;

import com.app.rms.dto.MovieDTO;
import com.app.rms.entity.Movie;

public interface MovieService {

	MovieDTO registerMovie(Movie movie);

}
