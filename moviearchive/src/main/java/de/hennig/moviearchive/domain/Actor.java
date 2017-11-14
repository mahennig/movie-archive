package de.hennig.moviearchive.domain;

import javax.persistence.Entity;

import static de.hennig.moviearchive.domain.Person.Type.ACTOR;

@Entity
public class Actor extends Person {

    @Override
    protected Type getChildType() {
        return ACTOR;
    }
}
