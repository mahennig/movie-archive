package de.hennig.moviearchive.services.dataprovider;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.DataProviderListener;
import com.vaadin.data.provider.Query;
import com.vaadin.shared.Registration;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.repositories.MovieRepository;
import de.hennig.moviearchive.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
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
		return getItems(getFilter(query));
	}

	@Override
	protected int sizeInBackEnd(Query<Movie, String> query) {
		 return (int) getItems(getFilter(query)).count();
	}

	private String getFilter(Query<Movie, String> t) {
		return t.getFilter().orElse(null);
	}

	private Stream<Movie> getItems(String filterText) {
		if (filterText == null || filterText.isEmpty()) {
			return StreamSupport.stream(movieRepository.findAll().spliterator(), false);
		}
		return null;
	}
}
