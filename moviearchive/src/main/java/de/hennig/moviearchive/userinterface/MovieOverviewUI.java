package de.hennig.moviearchive.userinterface;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import de.hennig.moviearchive.repositories.MovieRepository;
import de.hennig.moviearchive.userinterface.components.MovieGrid;
import de.hennig.moviearchive.userinterface.layouts.BodyLayout;
import org.springframework.beans.factory.annotation.Autowired;

@Title("Film Datenbank")
public class MovieOverviewUI extends UI {

    @Autowired
    MovieRepository movieRepository;

    @Override
    protected void init(VaadinRequest request) {

        MovieGrid grid = new MovieGrid("Meine Filme", movieRepository);
        grid.setSizeFull();

        BodyLayout newMovieLayout = new BodyLayout();
        newMovieLayout.setWidth("70%");

        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setMargin(new MarginInfo(true, true, false, true));
        mainLayout.addComponent(grid);
        mainLayout.setComponentAlignment(grid, Alignment.TOP_LEFT);
        mainLayout.addComponent(newMovieLayout);
        mainLayout.setComponentAlignment(newMovieLayout, Alignment.TOP_RIGHT);

        setContent(mainLayout);

    }
}
