package de.hennig.moviearchive;

import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.repositories.MovieRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ServletComponentScan("de.hennig.moviearchive.userinterface")
@EnableJpaRepositories
@ComponentScan
@Configuration
public class MovieArchiveApplication extends SpringBootServletInitializer {

    private static Logger logger = Logger.getLogger(MovieArchiveApplication.class);

    @Autowired
    MovieRepository movieRepository;

    public static void main(String[] args) {
        logger.info("Starting movie archive application ...");
        SpringApplication.run(MovieArchiveApplication.class, args);
    }
}
