package de.hennig.moviearchive;

import de.hennig.moviearchive.services.TestDataBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieArchiveApplication {

	private Logger logger = Logger.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(MovieArchiveApplication.class, args);
	}


}
