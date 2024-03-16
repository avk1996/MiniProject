package com.app.modules;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movies")
public class Movie {
	
	@Id
	@Column(name="movie_id")
	private Long id;
	
	@Column(name="imdb_id")
	private String imdbId;
	
	@Column
	private String title;
	
	@Column(name="release_date")
	private String releaseDate;
	
	@Column(name="trailer_link")
	private String trailerLink;
	
	@Column
	private String poster;
	
	
	private List<String> genres;
	
	
	private List<String> backDrops;
}
	