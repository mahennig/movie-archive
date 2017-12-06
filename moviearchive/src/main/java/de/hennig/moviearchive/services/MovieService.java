package de.hennig.moviearchive.services;

import com.google.common.collect.Lists;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import de.hennig.moviearchive.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hennig.moviearchive.domain.Movie;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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

    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

    public Movie getProduct(long movieId) {
        return movieRepository.findOne(movieId);
    }
}
