package de.hennig.moviearchive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("de.hennig.moviearchive")
@EntityScan("de.hennig.moviearchive.domain")
public class MoviearchiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviearchiveApplication.class, args);
	}
}
