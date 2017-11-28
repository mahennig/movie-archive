package de.hennig.moviearchive;

import com.google.common.collect.Lists;
import com.vaadin.spring.annotation.EnableVaadin;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.repositories.MovieRepository;
import de.hennig.moviearchive.services.Loader.CountryLoader;
import de.hennig.moviearchive.services.Loader.GenreLoader;
import org.apache.log4j.Logger;
import org.h2.engine.Session;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@ServletComponentScan("de.hennig.moviearchive.userinterface")
public class MovieArchiveApplication extends SpringBootServletInitializer {

    private static Logger logger = Logger.getLogger(MovieArchiveApplication.class);

    public static void main(String[] args) {
        logger.info("Starting movie archive application ...");
        ConfigurableApplicationContext context = SpringApplication.run(MovieArchiveApplication.class, args);
    }
}
