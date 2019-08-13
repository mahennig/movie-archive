package de.hennig.moviearchive.services;

import com.google.common.collect.Lists;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import de.hennig.moviearchive.domain.Person;
import de.hennig.moviearchive.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hennig.moviearchive.domain.Movie;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public void removeActor(Movie movie, Person actor) {
        Set<Person> persons = movie.getCast();
        if (persons.contains(actor)) {
            persons.remove(actor);
            movie.setCast(persons);
            log.info(String.format("Remove Actor [%s] from Movie [%s]", actor, movie));
        } else {
            log.info(String.format("Actor [%s] not part of selected Movie", actor));
            new Notification("Schauspieler '" + actor.getName() + "' aktuell nicht teil des Films.",
                    Notification.Type.WARNING_MESSAGE).show(Page.getCurrent());
        }
    }

    public void addActor(Movie movie, Person actor) {
        if (movie == null) {
            log.error("No movie selected");
            return;
        }
        movie.addActor(actor);
        log.info(String.format("Add Actor [%s] to Movie[%s]", actor, movie));
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
