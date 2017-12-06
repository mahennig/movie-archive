package de.hennig.moviearchive.repositories;


import de.hennig.moviearchive.domain.Genre;
import de.hennig.moviearchive.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {


}