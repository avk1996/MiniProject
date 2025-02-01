package com.app.rms.dto;

import java.math.BigInteger;
import java.time.LocalDate;

import lombok.Data;

@Data
public class MovieDTO {

	private String movieName;

	private String ratings;

	private String[] genre;

	private LocalDate releaseDate;

	private BigInteger budget;

	private BigInteger gross;

}
