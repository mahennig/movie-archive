package de.hennig.moviearchive.services.dataprovider;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.DataProviderListener;
import com.vaadin.data.provider.Query;
import com.vaadin.shared.Registration;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.repositories.MovieRepository;
import de.hennig.moviearchive.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.stream.Stream;

public class MovieDataProviderImpl extends AbstractBackEndDataProvider<Movie, String> implements MovieDataProvider {

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
    protected Stream<Movie> fetchFromBackEnd(Query<Movie, String> query) {
        return null;
    }

    @Override
    protected int sizeInBackEnd(Query<Movie, String> query) {
        return 0;
    }
}
