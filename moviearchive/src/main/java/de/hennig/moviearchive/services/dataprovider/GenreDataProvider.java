package de.hennig.moviearchive.services.dataprovider;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import de.hennig.moviearchive.domain.Genre;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.services.GenreService;

import java.util.stream.Stream;

public class GenreDataProvider extends AbstractBackEndDataProvider<Genre, String> {

    private final GenreService genreService;

    public GenreDataProvider(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    protected Stream<Genre> fetchFromBackEnd(Query<Genre, String> query) {
        return genreService.fetchGenres(query.getFilter().orElse(null), query.getLimit(), query.getOffset(),
                query.getSortOrders()).stream();
    }

    @Override
    protected int sizeInBackEnd(Query<Genre, String> query) {
        return genreService.countGenres(query.getFilter().orElse(null));
    }
}
