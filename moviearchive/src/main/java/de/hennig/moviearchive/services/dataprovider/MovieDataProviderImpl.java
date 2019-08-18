package de.hennig.moviearchive.services.dataprovider;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.core.FilterAttributes;
import de.hennig.moviearchive.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class MovieDataProviderImpl extends AbstractBackEndDataProvider<Movie, FilterAttributes> implements MovieDataProvider {

    @Autowired
    private MovieRepository movieRepository;


    @Transactional
    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
        refreshAll();
    }

    @Transactional
    @Override
    public void delete(Movie movie) {
        movieRepository.delete(movie);
        refreshAll();
    }

    @Override
    protected Stream<Movie> fetchFromBackEnd(Query<Movie, FilterAttributes> query) {
        return getItems(getFilter(query));
    }

    @Override
    protected int sizeInBackEnd(Query<Movie, FilterAttributes> query) {
        return (int) getItems(getFilter(query)).count();
    }

    private FilterAttributes getFilter(Query<Movie, FilterAttributes> t) {
        return t.getFilter().orElse(null);
    }

    private Stream<Movie> getItems(FilterAttributes filterText) {

        if (filterText == null) {
            return StreamSupport.stream(movieRepository.findAll().spliterator(), false);
        }

        if (!filterText.hasFilters()) {
            return StreamSupport.stream(movieRepository.findAll().spliterator(), false);
        }

        if (filterText.isSearchTextEmpty()) {
            if (!filterText.isCapitalEmpty()) {
                if (filterText.getCapital().equals("#")) {
                    return StreamSupport.stream(movieRepository.findAll().spliterator(), false).filter(m -> Character.isDigit(m.getTitle().charAt(0)));
                } else {
                    return StreamSupport.stream(movieRepository.findAll().spliterator(), false).filter(m -> m.getTitle().toLowerCase().startsWith(filterText.getCapital().toLowerCase()));
                }
            } else {
                return StreamSupport.stream(movieRepository.findAll().spliterator(), false);
            }

        } else {
            if (filterText.getCapital() != null && !filterText.getCapital().isEmpty()) {
                if (filterText.getCapital().equals("#")) {
                    return movieRepository.findDistinctByTitleContainingIgnoreCaseOrDirectorsContainingIgnoreCaseOrCastContainingIgnoreCase(filterText.getSearchText(), filterText.getSearchText(), filterText.getSearchText()).stream().
                            filter(m -> Character.isDigit(m.getTitle().charAt(0)));
                } else {
                    return movieRepository.findDistinctByTitleContainingIgnoreCaseOrDirectorsContainingIgnoreCaseOrCastContainingIgnoreCase(filterText.getSearchText(), filterText.getSearchText(), filterText.getSearchText()).stream().
                            filter(m -> m.getTitle().toLowerCase().startsWith(filterText.getCapital().toLowerCase()));
                }
            } else {
                return movieRepository.findDistinctByTitleContainingIgnoreCaseOrDirectorsContainingIgnoreCaseOrCastContainingIgnoreCase(filterText.getSearchText(), filterText.getSearchText(), filterText.getSearchText()).stream();
            }

        }
    }


}
