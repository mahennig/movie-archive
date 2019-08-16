
package de.hennig.moviearchive.repositories;

import de.hennig.moviearchive.domain.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findDistinctByTitleContainingIgnoreCaseOrDirectors(String title, String director);

    List<Movie> findDistinctByTitleContainingIgnoreCaseOrDirectorsOrTags(String title, String director, String tag);
}

