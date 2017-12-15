
package de.hennig.moviearchive.repositories;

import de.hennig.moviearchive.domain.Genre;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findDistinctByTitleContainingIgnoreCaseOrDirector(
            String title, Collection<Person> director);


}

