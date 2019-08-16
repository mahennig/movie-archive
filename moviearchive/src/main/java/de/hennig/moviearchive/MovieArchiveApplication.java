package de.hennig.moviearchive;

import de.hennig.moviearchive.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ServletComponentScan("de.hennig.moviearchive.userinterface")
@EnableJpaRepositories
@Configuration
@Slf4j
public class MovieArchiveApplication extends SpringBootServletInitializer {

    @Autowired
    MovieService movieService;

    public static void main(String[] args) {
        log.info("Starting movie archive application ...");
        SpringApplication.run(MovieArchiveApplication.class, args);
    }
}
