
package de.hennig.moviearchive.repositories;

import de.hennig.moviearchive.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {


}

