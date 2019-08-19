package de.hennig.moviearchive.userinterface.components;

import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import de.hennig.moviearchive.domain.Movie;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

import static com.vaadin.shared.data.sort.SortDirection.ASCENDING;

@SpringComponent
@UIScope
@Slf4j
public class MovieGrid extends Grid<Movie> {

    public MovieGrid() {
        setSelectionMode(SelectionMode.SINGLE);

        Column titleCol = addColumn(Movie::getTitle)
                .setDescriptionGenerator(Movie::getTitle)
                .setCaption("Titel")
                .setSortable(true)
                .setSortOrderProvider(
                        direction -> Stream.of(
                                new QuerySortOrder("title", direction),
                                new QuerySortOrder("year", direction)));

        addColumn(Movie::getDirectors).setCaption("Regisseur").setWidth(200);
        addColumn(Movie::getYear).setCaption("Jahr").setWidth(100);
        addColumn(Movie::getCountry).setCaption("Land").setWidth(100);
        addColumn(Movie::getCast).setCaption("Schauspieler").setWidth(400);
        addColumn(Movie::getGenres).setCaption("Genre").setWidth(300);
        addColumn(Movie::getFolder).setCaption("Ordner").setWidth(120);
        addColumn(Movie::getPage).setCaption("Seite").setWidth(120);

        sort(titleCol, ASCENDING);
    }

    public void refresh(Movie movie) {
        getDataCommunicator().refresh(movie);
    }


}
