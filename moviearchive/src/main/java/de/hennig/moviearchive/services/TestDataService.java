package de.hennig.moviearchive.services;

import de.hennig.moviearchive.domain.Genre;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.Person;
import de.hennig.moviearchive.domain.core.GenreCode;
import de.hennig.moviearchive.repositories.GenreRepository;
import de.hennig.moviearchive.repositories.MovieRepository;
import de.hennig.moviearchive.repositories.PersonRepository;
import de.hennig.moviearchive.util.RandomData;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TestDataService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    GenreRepository genreRepository;

    private final int COUNT = 10;

    @PostConstruct
    public void init() {
        cleanRepositories();
        initGenres();
        createMovies();
    }

    private void cleanRepositories() {
        movieRepository.deleteAll();
        personRepository.deleteAll();
        genreRepository.deleteAll();
    }

    private void initGenres() {
        for (GenreCode genre : GenreCode.values()) {
            Genre entity = new Genre();
            entity.setName(genre.getName());
            entity.setActiveFlag(true);
            genreRepository.save(entity);
        }
    }

    private void createMovies() {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            Person director = new Person();
            director.setName(RandomData.name());
            personRepository.save(director);

            Movie movie = new Movie();
            movie.setTitle(RandomData.name());
            movie.setDirector(director);
            movie.setYear(RandomData.year());
            movies.add(movie);
            movieRepository.save(movie);
        }
    }


}
