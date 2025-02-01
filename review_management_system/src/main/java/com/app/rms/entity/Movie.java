package com.app.rms.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import com.app.rms.enums.MovieRating;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private Integer movieId;
	
	@Column(name = "name")
	private String movieName;
	
	@Column(name = "certification_type")
	@Enumerated(EnumType.STRING)
	private MovieRating ratings;
	
	@Column(name = "genre")
	private String[] genre;
	
	@Column(name = "release_date")
	private LocalDate releaseDate;
	
	private BigInteger budget;
	
	private BigInteger gross;
	
}
