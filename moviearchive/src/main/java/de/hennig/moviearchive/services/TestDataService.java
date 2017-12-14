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
import org.hibernate.jpa.criteria.expression.function.AggregationFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
public class TestDataService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    GenreRepository genreRepository;

    Set<Genre> genres;

    private final int COUNT = 10;

    private final int MAX_GENRES = 2;

    private final int DESC_LENGTH = 70;

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
        genres = new HashSet<Genre>();
        for (GenreCode genre : GenreCode.values()) {
            Genre entity = new Genre();
            entity.setName(genre.getName());
            entity.setActiveFlag(true);
            genres.add(entity);
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
            movie.setGenres(fetchRandomGenres());
            movie.setDescription(createDescription());
            movies.add(movie);
            movieRepository.save(movie);
        }
    }

    private String createDescription() {
        return RandomData.name(DESC_LENGTH);
    }

    private Set<Genre> fetchRandomGenres() {
        HashSet<Genre> genreSet = new HashSet();
        int size = genres.size();
        int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
        int i = 0;
        for (int j = 1; j <= MAX_GENRES; j++) {
            for (Genre obj : genres)
                if (i == item)
                    genreSet.add(obj);
            i++;
        }
        return genreSet;
    }


}
