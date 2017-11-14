
package de.hennig.moviearchive.domain;

import de.hennig.moviearchive.domain.core.GenreCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Year;
import java.util.List;

//@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private Director director;

    @OneToMany(targetEntity = Person.class, mappedBy = "id", fetch = FetchType.EAGER)
    private List<Actor> cast;

    private Year year;

    private String genres;

    private String description;

    private String runningTime;

    @OneToMany(targetEntity = Keyword.class, mappedBy = "id", fetch = FetchType.EAGER)
    private List<Keyword> tags;

    private Folder folder;

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

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
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

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public List<Keyword> getTags() {
        return tags;
    }

    public void setTags(List<Keyword> tags) {
        this.tags = tags;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
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
