package de.hennig.moviearchive.userinterface.components;

import com.vaadin.ui.Grid;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class MovieGrid extends Grid<Movie> {

    public MovieGrid(String caption, MovieRepository movieRepository) {

        this.setCaption(caption);
        loadMovies();

        this.addColumn(Movie::getTitle).setCaption("Titel");
        this.addColumn(Movie::getYear).setCaption("Jahr");
        this.addColumn(Movie::getRunningTime).setCaption("Laufzeit");

    }

    private void loadMovies() {
        Movie m1 = new Movie();
        m1.setTitle("Test 1");
        m1.setYear(Year.now());
        m1.setRunningTime(1234L);
        Movie m2 = new Movie();
        m2.setTitle("Test 2");
        m1.setYear(Year.now());
        m1.setRunningTime(1254L);
        List<Movie> movies = new ArrayList();
        movies.add(m1);
        movies.add(m1);
        this.setItems(movies);
    }


}
