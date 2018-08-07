package de.hennig.moviearchive.services;

import de.hennig.moviearchive.domain.Genre;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.Person;
import de.hennig.moviearchive.domain.core.GenreData;
import de.hennig.moviearchive.repositories.GenreRepository;
import de.hennig.moviearchive.repositories.MovieRepository;
import de.hennig.moviearchive.repositories.PersonRepository;
import de.hennig.moviearchive.util.RandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class TestDataService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    GenreRepository genreRepository;

    private final int COUNT = 50;

    private final int MAX_GENRES = 2;

    private final int DESC_LENGTH = 150;

    Random r = new Random();

    @PostConstruct
    public void init() {
        cleanRepositories();
        createMovies();
    }

    private void cleanRepositories() {
        movieRepository.deleteAll();
        personRepository.deleteAll();
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
            movie.setGenres(fetchRandomGenres());
            movie.setDescription(createDescription());
            movies.add(movie);
            movieRepository.save(movie);
        }
    }

    private String createDescription() {
        return RandomData.name(DESC_LENGTH);
    }

    private Set<String> fetchRandomGenres() {
        HashSet<String> genreSet = new HashSet();

        for (int i = 0; i<GenreData.values().length; i++) {
            if (r.nextBoolean()){
                genreSet.add(GenreData.class.getEnumConstants()[i].getName());
            }
        }
        return genreSet;
    }


}
