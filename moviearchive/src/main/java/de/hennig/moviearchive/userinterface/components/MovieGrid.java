package de.hennig.moviearchive.userinterface.components;

import com.google.common.base.Function;
import com.vaadin.data.converter.StringToBooleanConverter;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.renderers.ImageRenderer;
import de.hennig.moviearchive.domain.Movie;

import de.hennig.moviearchive.domain.core.CountryData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


@SpringComponent
@UIScope
public class MovieGrid extends Grid<Movie> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public MovieGrid() {
        Column<Movie, String> nameCol = addColumn(Movie::getTitle).setDescriptionGenerator(Movie::getTitle).setCaption("Titel");
        addColumn(Movie::getDirectors).setCaption("Regisseur").setWidth(200);
        addColumn(Movie::getYear).setCaption("Jahr").setWidth(100);
        addColumn(Movie::getCountry).setCaption("Land").setWidth(100);
        addColumn(Movie::getCast).setCaption("Schauspieler").setWidth(400);
        addColumn(Movie::getGenres).setCaption("Genre").setWidth(300);
        addColumn(Movie::getFolder).setCaption("Ordner").setWidth(120);
        addColumn(Movie::getPage).setCaption("Seite").setWidth(120);

        sort(nameCol, SortDirection.DESCENDING);
    }

    public void refresh(Movie movie) {
        getDataCommunicator().refresh(movie);
    }

    public Movie getSelectedRow() {
        Movie movie = asSingleSelect().getValue();
        logger.info("Selected: {}", movie);
        return movie;
    }

}
