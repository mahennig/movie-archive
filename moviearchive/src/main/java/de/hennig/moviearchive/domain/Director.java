package de.hennig.moviearchive.domain;

import javax.persistence.Entity;

import static de.hennig.moviearchive.domain.Person.Type.DIRECTOR;

@Entity
public class Director extends Person {

    @Override
    protected Type getChildType() {
        return DIRECTOR;
    }
}
