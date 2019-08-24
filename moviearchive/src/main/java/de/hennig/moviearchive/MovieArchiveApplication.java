package de.hennig.moviearchive;

import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ServletComponentScan("de.hennig.moviearchive.userinterface")
@EnableJpaRepositories
public class MovieArchiveApplication extends SpringBootServletInitializer {

    @Autowired
    MovieService service;

    public static void main(String[] args) {
        SpringApplication.run(MovieArchiveApplication.class, args);
    }

    //@PostConstruct
    public void initTestData() {
        service.saveMovie(generateMovie("Harry Potter", "Tom Bla", "Richard, Emma", "Fantasy"));
        service.saveMovie(generateMovie("The Boys", "Amazon", "Jerry, Emma", "Fantasy, Komödie"));
        service.saveMovie(generateMovie("Hitman Reborn", "Anime", "Irgendwer", "Action"));
    }

    private Movie generateMovie(String title, String director, String cast, String gerne) {
        Movie movie = new Movie(title);
        movie.setGenres(gerne);
        movie.setCast(cast);
        movie.setDirectors(director);
        return movie;
    }
}
