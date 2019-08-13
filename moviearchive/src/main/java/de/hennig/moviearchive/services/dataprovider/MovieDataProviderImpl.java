package de.hennig.moviearchive.services.dataprovider;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.DataProviderListener;
import com.vaadin.data.provider.Query;
import com.vaadin.sass.internal.util.StringUtil;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Registration;
import de.hennig.moviearchive.domain.Genre;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.Person;
import de.hennig.moviearchive.domain.core.FilterAttributes;
import de.hennig.moviearchive.repositories.MovieRepository;
import de.hennig.moviearchive.repositories.PersonRepository;
import de.hennig.moviearchive.services.MovieCrudLogic;
import de.hennig.moviearchive.services.MovieService;
import de.hennig.moviearchive.services.PersonService;
import de.hennig.moviearchive.userinterface.components.MovieForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class MovieDataProviderImpl extends AbstractBackEndDataProvider<Movie, FilterAttributes> implements MovieDataProvider {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

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
                    return movieRepository.findDistinctByTitleContainingIgnoreCaseOrDirector(filterText.getSearchText(), getFilteredDirectors(filterText.getSearchText())).stream().
                            filter(m -> Character.isDigit(m.getTitle().charAt(0)));
                } else {
                    return movieRepository.findDistinctByTitleContainingIgnoreCaseOrDirector(filterText.getSearchText(), getFilteredDirectors(filterText.getSearchText())).stream().
                            filter(m -> m.getTitle().toLowerCase().startsWith(filterText.getCapital().toLowerCase()));
                }
            } else {
                return movieRepository.findDistinctByTitleContainingIgnoreCaseOrDirector(filterText.getSearchText(), getFilteredDirectors(filterText.getSearchText())).stream();
            }

        }
    }

    private Collection<Person> getFilteredDirectors(String string) {
        return personRepository.findAllByNameContainingIgnoreCase(string);
    }

}
