
package de.hennig.moviearchive.repositories;

import de.hennig.moviearchive.domain.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findDistinctByTitleContainingIgnoreCaseOrDirectorsContainingIgnoreCaseOrCastContainingIgnoreCase(String title, String director, String actor);

    List<Movie> findDistinctByTitleContainingIgnoreCaseOrDirectorsOrTags(String title, String director, String tag);
}

