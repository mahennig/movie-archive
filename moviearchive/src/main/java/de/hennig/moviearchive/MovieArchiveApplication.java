package de.hennig.moviearchive;

import de.hennig.moviearchive.repositories.MovieRepository;
import de.hennig.moviearchive.services.Loader.CountryLoader;
import de.hennig.moviearchive.services.Loader.GenreLoader;
import org.apache.log4j.Logger;
import org.h2.engine.Session;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("de.hennig.moviearchive")
public class MovieArchiveApplication {

    private static Logger logger = Logger.getLogger(MovieArchiveApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MovieArchiveApplication.class, args);
    }
}
