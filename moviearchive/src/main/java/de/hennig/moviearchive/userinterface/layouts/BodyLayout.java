package de.hennig.moviearchive.userinterface.layouts;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.userinterface.components.MovieGrid;

public class BodyLayout extends HorizontalLayout {

    MovieGrid movieGrid;
    AddMovieLayout addMovieLayout;

    public BodyLayout() {
        prepareMovieGrid();
        prepareAddMovieLayout();
        prepareLayout();
    }

    private void prepareMovieGrid() {
        movieGrid = new MovieGrid("Meine Filme");
        movieGrid.setSizeFull();
    }

    private void prepareAddMovieLayout() {
        addMovieLayout = new AddMovieLayout();
    }

    private void prepareLayout() {
        this.setMargin(new MarginInfo(true, true, false, true));
        this.addComponent(movieGrid);
        this.setComponentAlignment(movieGrid, Alignment.TOP_LEFT);
        this.addComponent(addMovieLayout);
        this.setComponentAlignment(addMovieLayout, Alignment.TOP_RIGHT);
    }
}
