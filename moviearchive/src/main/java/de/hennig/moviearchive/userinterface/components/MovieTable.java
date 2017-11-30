package de.hennig.moviearchive.userinterface.components;

import com.google.common.collect.Lists;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@SpringComponent
@UIScope
public class MovieTable extends Grid<Movie> {

    @Autowired
    MovieRepository movieRepository;

    public MovieTable() {
        this.addColumn(Movie::getTitle).setCaption("Titel");
        this.addColumn(Movie::getYear).setCaption("Jahr");
        this.addColumn(Movie::getRunningTime).setCaption("Laufzeit");

        List<Movie> movies = new ArrayList();
        movies = Lists.newArrayList(movieRepository.findAll());
    }
}
