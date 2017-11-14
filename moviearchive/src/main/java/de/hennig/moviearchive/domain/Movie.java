
package de.hennig.moviearchive.domain;

import de.hennig.moviearchive.domain.core.GenreCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Year;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @OneToOne(optional = true, targetEntity = Director.class)
    private Director director;

    @OneToMany(targetEntity = Actor.class, mappedBy = "id")
    @Fetch(FetchMode.SELECT)
    private List<Actor> cast;

    @Column(name = "YEAR")
    private Year year;

    @Column(name = "GENRES")
    private String genres;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RUNNING_TIME")
    private Long runningTime;

    @OneToMany(targetEntity = Keyword.class, mappedBy = "id")
    @Fetch(FetchMode.SELECT)
    private List<Keyword> tags;

    @OneToOne(optional = true, targetEntity = Folder.class)
    private Folder folder;

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
