package de.hennig.moviearchive.services;

import com.google.common.collect.Sets;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.Person;
import de.hennig.moviearchive.repositories.MovieRepository;
import de.hennig.moviearchive.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Set<Person> getAllPerson(){
        return Sets.newHashSet(personRepository.findAll());
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }
}
