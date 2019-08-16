
package de.hennig.moviearchive.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString
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

    @Column(name = "DIRECTORS")
    private String directors;

    @Column(name = "CAST")
    private String cast;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "GENRE")
    private String genres;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RUNNING_TIME")
    private Integer runningTime;

    @Column(name = "FOLDER")
    private Integer folder;

    @Column(name = "PAGE")
    private Integer page;

    @Column(name = "TAG")
    private String tags;

}
