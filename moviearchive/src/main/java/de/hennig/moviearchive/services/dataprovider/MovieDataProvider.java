package de.hennig.moviearchive.services.dataprovider;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.services.MovieService;

import java.util.stream.Stream;

public class MovieDataProvider extends AbstractBackEndDataProvider<Movie, String> {

    private final MovieService movieService;

    public MovieDataProvider(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public  Stream<Movie> fetchFromBackEnd(Query<Movie, String> query) {
        return movieService.fetchMovies(query.getFilter().orElse(null), query.getLimit(), query.getOffset(),
                query.getSortOrders()).stream();
    }

    @Override
    public int sizeInBackEnd(Query<Movie, String> query) {
        return movieService.countMovies(query.getFilter().orElse(null));
    }
}
