package de.hennig.moviearchive.services;

import com.google.common.collect.Lists;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import de.hennig.moviearchive.domain.Person;
import de.hennig.moviearchive.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hennig.moviearchive.domain.Movie;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MovieService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public Movie getMovie(long movieId) {
        return movieRepository.findOne(movieId);
    }

    public void removeActor(Movie movie, Person actor) {
        Set<Person> persons = movie.getCast();
        if (persons.contains(actor)) {
            persons.remove(actor);
            movie.setCast(persons);
            logger.info(String.format("Remove Actor [%s] from Movie [%s]", actor, movie));
        } else {
            logger.info(String.format("Actor [%s] not part of selected Movie", actor));
        }


    }

    public void addActor(Movie movie, Person actor) {
        movie.addActor(actor);
        logger.info(String.format("Add Actor [%s] to Movie[%s]", actor, movie));
    }
}
