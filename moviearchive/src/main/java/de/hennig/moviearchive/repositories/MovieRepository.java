
package de.hennig.moviearchive.repositories;


import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.hennig.moviearchive.domain.Movie;

public interface MovieRepository extends GraphRepository<Movie> {

	Movie findByMovieId(String movieId);

	Movie findByMovieId(String movieId, @Depth int depth);

    void deleteByMovieId(String movieId);
}
