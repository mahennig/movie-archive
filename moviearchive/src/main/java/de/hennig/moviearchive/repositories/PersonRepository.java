
package de.hennig.moviearchive.repositories;

import de.hennig.moviearchive.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findAllByNameContainingIgnoreCase(String name);


}

