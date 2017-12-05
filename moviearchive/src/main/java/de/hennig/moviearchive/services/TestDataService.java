package de.hennig.moviearchive.services;

import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.Person;
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
import java.util.List;

@Component
public class TestDataService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    PersonRepository personRepository;

    private final int COUNT = 10;

    @PostConstruct
    public void init() {
        movieRepository.deleteAll();
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
