
package de.hennig.moviearchive.repositories;

import de.hennig.moviearchive.domain.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {


}

