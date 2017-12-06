package de.hennig.moviearchive.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GENRE_ID")
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean activeFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    @Override
    public String toString() {
        return name;
    }

}
