package de.hennig.moviearchive.services;

import de.hennig.moviearchive.domain.Genre;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.Person;
import de.hennig.moviearchive.domain.core.CountryData;
import de.hennig.moviearchive.domain.core.GenreData;
import de.hennig.moviearchive.repositories.GenreRepository;
import de.hennig.moviearchive.repositories.MovieRepository;
import de.hennig.moviearchive.repositories.PersonRepository;
import de.hennig.moviearchive.util.RandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
    private final int MIN_PAGE = 1;
    private final int MAX_PAGE = 100;
    private final int MIN_FOLDER = 1;
    private final int MAX_FOLDER = 20;

    Random r = new Random();

    //@PostConstruct
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
            movie.setCast(createCast());
            movie.setFolder(createRandomFolderAndPage(MIN_FOLDER, MAX_FOLDER));
            movie.setPage(createRandomFolderAndPage(MIN_PAGE, MAX_PAGE));
            movie.setYear(RandomData.year());
            movie.setCountry(fetchRandomCountries());
            movie.setGenres(fetchRandomGenres());
            movie.setDescription(createDescription());
            movies.add(movie);
            movieRepository.save(movie);
        }
    }

    private Set<Person> createCast() {
        Set<Person> persons = new HashSet<>();
        persons.add(createRandomPerson());
        persons.add(createRandomPerson());
        persons.add(createRandomPerson());
        return persons;
    }

    private Person createRandomPerson(){
        Person p = new Person();
        p.setName(RandomData.name());
        personRepository.save(p);
        return p;
    }

    private String createDescription() {
        return RandomData.name(DESC_LENGTH);
    }

    private int createRandomFolderAndPage(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    private Set<String> fetchRandomCountries() {
        HashSet<String> genreSet = new HashSet();

        for (int i = 0; i < CountryData.values().length; i++) {
            if (r.nextBoolean()) {
                genreSet.add(CountryData.class.getEnumConstants()[i].getName());
            }
        }
        return genreSet;
    }

    private Set<String> fetchRandomGenres() {
        HashSet<String> genreSet = new HashSet();

        for (int i = 0; i < GenreData.values().length; i++) {
            if (r.nextBoolean()) {
                genreSet.add(GenreData.class.getEnumConstants()[i].getName());
            }
        }
        return genreSet;
    }


}
