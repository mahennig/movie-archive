package de.hennig.moviearchive;

import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.Person;
import de.hennig.moviearchive.services.MovieService;
import de.hennig.moviearchive.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@SpringBootApplication
@ServletComponentScan("de.hennig.moviearchive.userinterface")
@EnableJpaRepositories
@Configuration
@Slf4j
public class MovieArchiveApplication extends SpringBootServletInitializer {

    @Autowired
    MovieService movieService;

    @Autowired
    PersonService personService;

    public static void main(String[] args) {
        log.info("Starting movie archive application ...");
        SpringApplication.run(MovieArchiveApplication.class, args);
    }

    @PostConstruct
    public void initTestData(){
        Person p1 = new Person();
        p1.setName("Hans Koripf");
        personService.savePerson(p1);

        Person p2 = new Person();
        p2.setName("Thomas Smith");
        personService.savePerson(p2);

        HashSet<Person> people = new HashSet<>();
        people.add(p1);
        people.add(p2);

        Movie movie = new Movie();
        movie.setTitle("Test Film");
        movie.setCast(people);
        movieService.saveMovie(movie);
    }
}
