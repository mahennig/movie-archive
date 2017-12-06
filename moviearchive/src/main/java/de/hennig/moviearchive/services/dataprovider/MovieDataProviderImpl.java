package de.hennig.moviearchive.services.dataprovider;

import com.vaadin.data.provider.DataProviderListener;
import com.vaadin.data.provider.Query;
import com.vaadin.shared.Registration;
import de.hennig.moviearchive.domain.Movie;

import java.util.stream.Stream;

public class MovieDataProviderImpl implements MovieDataProvider {

    @Override
    public void save(Movie movie) {

    }

    @Override
    public void delete(Movie movie) {

    }

    @Override
    public boolean isInMemory() {
        return false;
    }

    @Override
    public int size(Query<Movie, String> query) {
        return 0;
    }

    @Override
    public Stream<Movie> fetch(Query<Movie, String> query) {
        return null;
    }

    @Override
    public void refreshItem(Movie item) {

    }

    @Override
    public void refreshAll() {

    }

    @Override
    public Registration addDataProviderListener(DataProviderListener<Movie> listener) {
        return null;
    }
}
