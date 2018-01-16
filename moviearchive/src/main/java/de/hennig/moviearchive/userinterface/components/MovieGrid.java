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

    private int MAX_DESC_LENGTH = 15;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public MovieGrid() {
        addColumn(Movie::getTitle).setCaption("Titel").setSortProperty("name");
        addColumn(Movie::getDirector).setCaption("Regisseur");
        addColumn(Movie::getYear).setCaption("Jahr");
        addColumn(Movie::getFolder).setCaption("Ordner");
        addColumn(Movie::getPage).setCaption("Seite");
        addColumn(Movie::getRunningTime).setCaption("Laufzeit");
        addColumn(Movie::getDescription).setCaption("Handlung").setWidth(300);
    }

    public void refresh(Movie movie) {
        getDataCommunicator().refresh(movie);
    }

    public Movie getSelectedRow() {
        return asSingleSelect().getValue();
    }

    private String truncateDesc(String description) {
        return description.substring(0, MAX_DESC_LENGTH) + " ...";
    }


}
