
package de.hennig.moviearchive.repositories;

import de.hennig.moviearchive.domain.core.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {

}

