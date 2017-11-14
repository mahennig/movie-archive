package de.hennig.moviearchive.domain.builder;

import de.hennig.moviearchive.domain.Actor;
import de.hennig.moviearchive.domain.Director;
import de.hennig.moviearchive.domain.Folder;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.core.GenreCode;

import java.util.List;

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

    public MovieBuilder withFolder(Folder folder) {
        entity.setFolder(folder);
        return this;
    }

    public MovieBuilder withActors(List<Actor> cast) {
        entity.setCast(cast);
        return this;
    }

    public MovieBuilder addActor(Actor actor) {
        entity.getCast().add(actor);
        return this;
    }

    public MovieBuilder withDirector(Director director) {
        entity.setDirector(director);
        return this;
    }

    public MovieBuilder withTitle(String title) {
        entity.setTitle(title);
        return this;
    }

    public MovieBuilder withGenres(List<String> genres) {
        entity.setGenres(genres.toString());
        return this;
    }


}
