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
public class MovieArchiveApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MovieArchiveApplication.class, args);
    }
}
