package de.hennig.moviearchive.domain.builder;

public abstract class AbstractBuilder<T> {

    protected T entity;

    protected abstract T newEntity();

    public AbstractBuilder() {
        this.entity = newEntity();
    }

    protected abstract void validate();

    public final T build() {
        validate();
        return entity;
    }
}
