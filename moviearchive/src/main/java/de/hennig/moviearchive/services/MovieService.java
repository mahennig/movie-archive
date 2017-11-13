package de.hennig.moviearchive.services;

import de.hennig.moviearchive.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hennig.moviearchive.domain.Movie;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepo;
	
	public void insertMovie(Movie movie) {

	}

}
