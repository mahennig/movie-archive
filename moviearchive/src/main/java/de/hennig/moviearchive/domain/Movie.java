
package de.hennig.moviearchive.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Movie implements Serializable {

    public Movie(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "MOVIE_ID")
    private Long id = -1L;

    @NotNull(message = "Bitte geben Sie einen Titel ein.")
    @Column(name = "TITLE")
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

    @Column(name = "DESCRIPTION", length = 1024)
    private String description;

    @Column(name = "RUNNING_TIME")
    private Integer runningTime;

    @Column(name = "FOLDER", length = 50)
    private String folder;

    @Column(name = "PAGE", length = 50)
    private String page;

    @Column(name = "TAG")
    private String tags;

}
