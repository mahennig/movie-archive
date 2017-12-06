
package de.hennig.moviearchive.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "MOVIE_ID")
    private Long id = -1L;

    @NotNull
    @Column(name = "TITLE", unique = true)
    private String title;

    @OneToOne(targetEntity = Person.class)
    private Person director;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "MOVIE_AND_ACTOR", joinColumns = {
            @JoinColumn(name = "MOVIE_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "PERSON_ID")})
    private Set<Person> cast = new HashSet<>();

    @Column(name = "YEAR")
    private Integer year;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "MOVIE_AND_GENRE", joinColumns = {
            @JoinColumn(name = "MOVIE_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "GENRE_ID")})
    private Set<Genre> genres = new HashSet<>();

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RUNNING_TIME")
    private Long runningTime;

    @Column(name = "FOLDER")
    private Integer folder;

    @Column(name = "PAGE")
    private Integer page;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public Set<Person> getCast() {
        return cast;
    }

    public void setCast(Set<Person> cast) {
        this.cast = cast;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Long runningTime) {
        this.runningTime = runningTime;
    }

    public Integer getFolder() {
        return folder;
    }

    public void setFolder(Integer folder) {
        this.folder = folder;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director=" + director +
                ", cast=" + cast +
                ", genres=" + genres +
                ", description='" + description + '\'' +
                ", runningTime='" + runningTime + '\'' +
                ", folder=" + folder +
                ", page=" + page +
                '}';
    }
}
