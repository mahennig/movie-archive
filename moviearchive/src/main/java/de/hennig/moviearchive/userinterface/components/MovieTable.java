package de.hennig.moviearchive.userinterface.components;

import com.google.common.collect.Lists;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.repositories.MovieRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringComponent
@UIScope
@SuppressWarnings("serial")
public class MovieTable extends Grid<Movie> {

	@Autowired
	MovieRepository movieRepository;

	Optional<Movie> selectedMovie;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public MovieTable() {
		loadApplicationContext();
		this.addColumn(Movie::getTitle).setCaption("Titel");
		this.addColumn(Movie::getYear).setCaption("Jahr");
		this.addColumn(Movie::getRunningTime).setCaption("Laufzeit");
		this.addColumn(Movie::getDescription).setCaption("Handlung");

		List<Movie> movies = new ArrayList();
		movies = Lists.newArrayList(movieRepository.findAll());
		movies.forEach(e -> logger.info(e.getTitle()));
		this.setItems(movies);

		addSelectionListener(event -> {
			selectedMovie = event.getFirstSelectedItem();
		});
	}

	public Optional<Movie> getSelectedMovie() {
		return selectedMovie;
	}

	private void loadApplicationContext() {
		this.movieRepository = WebApplicationContextUtils
				.getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
				.getBean(MovieRepository.class);
	}
}
