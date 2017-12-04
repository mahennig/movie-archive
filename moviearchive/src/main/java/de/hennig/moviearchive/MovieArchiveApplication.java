package de.hennig.moviearchive;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan("de.hennig.moviearchive.userinterface")
public class MovieArchiveApplication extends SpringBootServletInitializer {

	private static Logger logger = Logger.getLogger(MovieArchiveApplication.class);

	public static void main(String[] args) {
		logger.info("Starting movie archive application ...");
		SpringApplication.run(MovieArchiveApplication.class, args);
	}
}
