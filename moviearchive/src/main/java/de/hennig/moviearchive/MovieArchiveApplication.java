package de.hennig.moviearchive;

import de.hennig.moviearchive.services.Loader.CountryLoader;
import de.hennig.moviearchive.services.Loader.GenreLoader;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MovieArchiveApplication {

	private Logger logger = Logger.getLogger(getClass());

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MovieArchiveApplication.class, args);
		context.getBean(CountryLoader.class).load();
	}


}
