package de.hennig.moviearchive.services;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import de.hennig.moviearchive.domain.Movie;

import java.util.stream.Stream;

public class MovieDataProvider extends AbstractBackEndDataProvider<Movie, String> {

    private final MovieService movieService;

    public MovieDataProvider(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    protected Stream<Movie> fetchFromBackEnd(Query<Movie, String> query) {
        return movieService.fetchEmployees(query.getFilter().orElse(null), query.getLimit(), query.getOffset(),
                query.getSortOrders()).stream();
    }

    @Override
    protected int sizeInBackEnd(Query<Movie, String> query) {
        return movieService.countMovies(query.getFilter().orElse(null));
    }
}
