package de.hennig.moviearchive.userinterface.components;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import de.hennig.moviearchive.domain.Movie;

import lombok.extern.slf4j.Slf4j;

@SpringComponent
@UIScope
@Slf4j
public class MovieGrid extends Grid<Movie> {

    public MovieGrid() {
        setSelectionMode(SelectionMode.SINGLE);

        Column titleCol = addColumn(Movie::getTitle).setDescriptionGenerator(Movie::getTitle).setCaption("Titel").setSortable(true);
        addColumn(Movie::getDirectors).setCaption("Regisseur").setWidth(200);
        addColumn(Movie::getYear).setCaption("Jahr").setWidth(100);
        addColumn(Movie::getCountry).setCaption("Land").setWidth(100);
        addColumn(Movie::getCast).setCaption("Schauspieler").setWidth(400);
        addColumn(Movie::getGenres).setCaption("Genre").setWidth(300);
        addColumn(Movie::getFolder).setCaption("Ordner").setWidth(120);
        addColumn(Movie::getPage).setCaption("Seite").setWidth(120);

        sort(titleCol);
    }

    public void refresh(Movie movie) {
        getDataCommunicator().refresh(movie);
    }


}
