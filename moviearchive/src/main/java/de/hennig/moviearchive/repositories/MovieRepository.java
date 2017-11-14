
package de.hennig.moviearchive.repositories;

import de.hennig.moviearchive.domain.Folder;
import de.hennig.moviearchive.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

}

