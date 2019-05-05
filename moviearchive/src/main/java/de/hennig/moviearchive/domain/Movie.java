
package de.hennig.moviearchive.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 2, message = "Movie title name must have at least two characters")
    @Column(name = "TITLE", unique = true)
    private String title;

    @OneToOne(targetEntity = Person.class)
    private Person director;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "MOVIE_AND_ACTOR", joinColumns = {@JoinColumn(name = "MOVIE_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "PERSON_ID")})
    private Set<Person> cast = new HashSet<>();

    @Column(name = "YEAR")
    private Integer year;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "COUNTRY")
    private Set<String> country = new HashSet<>();;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "GENRE")
    private Set<String> genres = new HashSet<>();

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RUNNING_TIME")
    private Integer runningTime;

    @Column(name = "FOLDER")
    private Integer folder;

    @Column(name = "PAGE")
    private Integer page;

    public Long getId() {
        return id;
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

    public void addActor(Person actor) {
        cast.add(actor);
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<String> getCountry() {
        return country;
    }

    public void setCountry(Set<String> country) {
        this.country = country;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Integer runningTime) {
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
                ", cast=" + cast.toString() +
                ", year=" + year +
                ", genres=" + genres.toString() +
                ", description='" + description + '\'' +
                ", runningTime=" + runningTime +
                ", folder=" + folder +
                ", page=" + page +
                '}';
    }
}
