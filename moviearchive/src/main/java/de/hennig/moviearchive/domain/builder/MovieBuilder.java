package de.hennig.moviearchive.domain.builder;

import de.hennig.moviearchive.domain.Genre;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.Person;

import java.util.List;
import java.util.Set;

public class MovieBuilder extends AbstractBuilder<Movie> {


    @Override
    public Movie newEntity() {
        return new Movie();
    }

    @Override
    protected void validate() {
        if (entity.getTitle() == null) {
            throw new IllegalStateException("Cannot build " + this.getClass().getSimpleName() + " without title.");
        }
    }

    public MovieBuilder withFolder(Integer folder) {
        entity.setFolder(folder);
        return this;
    }

    public MovieBuilder withActors(Set<Person> cast) {
        entity.setCast(cast);
        return this;
    }

    public MovieBuilder addActor(Person actor) {
        entity.getCast().add(actor);
        return this;
    }

    public MovieBuilder withDirector(Person director) {
        entity.setDirector(director);
        return this;
    }

    public MovieBuilder withTitle(String title) {
        entity.setTitle(title);
        return this;
    }

    public MovieBuilder withGenres(Set<String> genres) {
        entity.setGenres(genres);
        return this;
    }


}
