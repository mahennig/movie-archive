package de.hennig.moviearchive.userinterface.components;

import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import de.hennig.moviearchive.domain.Movie;

import de.hennig.moviearchive.services.MovieService;
import de.hennig.moviearchive.services.dataprovider.MovieDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Optional;

@SpringComponent
@UIScope
public class MovieGrid extends Grid<Movie> {

	@Autowired
	MovieService movieService;

	Optional<Movie> selectedMovie;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public MovieGrid() {
		loadApplicationContext();
		addColumn(Movie::getTitle).setCaption("Titel");
		addColumn(Movie::getDirector).setCaption("Regisseur");
		addColumn(Movie::getYear).setCaption("Jahr");
		addColumn(Movie::getFolder).setCaption("Ordner");
		addColumn(Movie::getPage).setCaption("Seite");
		addColumn(Movie::getRunningTime).setCaption("Laufzeit");
		addColumn(Movie::getDescription).setCaption("Handlung");
		//this.setDataProvider(new MovieDataProvider(movieService));
	}

	public Optional<Movie> getSelectedMovie() {
		return selectedMovie;
	}

	private void loadApplicationContext() {
		this.movieService = WebApplicationContextUtils
				.getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
				.getBean(MovieService.class);
	}
}
