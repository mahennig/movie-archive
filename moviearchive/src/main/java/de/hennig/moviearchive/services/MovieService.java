package de.hennig.moviearchive.services;

import com.google.common.collect.Lists;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import de.hennig.moviearchive.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hennig.moviearchive.domain.Movie;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@Slf4j
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> findAll() {
        return Lists.newArrayList(movieRepository.findAll());
    }

    public ArrayList<Movie> fetchMovies(String filter, int limit, int offset, List<QuerySortOrder> sortOrders) {
        ArrayList<Movie> movies = new ArrayList<>();
        movies = Lists.newArrayList(movieRepository.findAll());
        return movies;
    }

    public int countMovies(String filter) {
        return Lists.newArrayList(movieRepository.findAll()).size();
    }

    public void deleteMovie(Movie movie) {
        log.info(String.format("Delete Movie [%s]", movie));
        movieRepository.delete(movie);
    }

    public Optional<Movie> getMovie(long movieId) {
        return movieRepository.findById(movieId);
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie getRandomMovie() {
        return new Movie("Harry Potter");
    }
}
