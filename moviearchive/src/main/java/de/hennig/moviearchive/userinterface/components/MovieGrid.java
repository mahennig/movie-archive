package de.hennig.moviearchive.userinterface.components;

import com.vaadin.server.SerializableComparator;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.HeaderRow;
import de.hennig.moviearchive.domain.Movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;


@SpringComponent
@UIScope
public class MovieGrid extends Grid<Movie> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public MovieGrid() {

        Column<Movie, String> nameCol = addColumn(Movie::getTitle).setCaption("Titel");
        addColumn(Movie::getDirector).setCaption("Regisseur").setWidth(300);
        addColumn(Movie::getYear).setCaption("Jahr").setWidth(100);
        addColumn(Movie::getFolder).setCaption("Ordner").setWidth(70);
        addColumn(Movie::getPage).setCaption("Seite").setWidth(70);
        addColumn(Movie::getRunningTime).setCaption("Laufzeit").setWidth(100);

        nameCol.setComparator((mov1, mov2) -> {
            return mov1.getTitle().compareTo(mov2.toString());
        });
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
