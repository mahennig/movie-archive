
package de.hennig.moviearchive.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.io.Serializable;
import java.util.List;

@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MOVIE_ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @OneToOne(targetEntity = Person.class)
    private Person director;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Person.class,
            fetch = FetchType.EAGER, mappedBy = "movies")
    private List<Person> cast;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "GENRES")
    private String genres;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RUNNING_TIME")
    private Long runningTime;

    @OneToMany(targetEntity = Keyword.class, mappedBy = "id")
    @Fetch(FetchMode.SELECT)
    private List<Keyword> tags;

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

    public List<Person> getCast() {
        return cast;
    }

    public void setCast(List<Person> cast) {
        this.cast = cast;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
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

    public List<Keyword> getTags() {
        return tags;
    }

    public void setTags(List<Keyword> tags) {
        this.tags = tags;
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
                ", tags=" + tags +
                ", folder=" + folder +
                ", page=" + page +
                '}';
    }
}
