package de.hennig.moviearchive;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieArchiveApplication {

	private Logger logger = Logger.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(MovieArchiveApplication.class, args);
	}


}
