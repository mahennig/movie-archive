package de.hennig.moviearchive.services.dataprovider;

import com.vaadin.data.provider.DataProvider;
import de.hennig.moviearchive.domain.Movie;

import java.util.stream.Stream;

public interface MovieDataProvider extends DataProvider<Movie, String> {

    /**
     * Store given movie to the repository.
     *
     * @param movie the updated or new movie
     */
    public void save(Movie movie);

    /**
     * Delete given movie from the repository.
     *
     * @param movie the movie to be deleted
     */
    public void delete(Movie movie);

}
