package de.hennig.moviearchive.services;

import com.google.common.collect.Lists;
import de.hennig.moviearchive.domain.Folder;
import de.hennig.moviearchive.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hennig.moviearchive.domain.Movie;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepo;


    public void insertMovie(Movie movie) {

    }

    public List<Movie> findAll(){
        return Lists.newArrayList(movieRepo.findAll());
    }

    public List<Movie> getMoviesByFolder(Folder folder) {
        return movieRepo.findByFolder(folder);
    }

}
