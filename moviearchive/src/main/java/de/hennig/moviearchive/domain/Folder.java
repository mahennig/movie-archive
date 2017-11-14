package de.hennig.moviearchive.domain;

/*
    Stores the current location of the movie disc.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(unique=true)
    Integer folderNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFolderNumber() {
        return folderNumber;
    }

    public void setFolderNumber(Integer folderNumber) {
        this.folderNumber = folderNumber;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", folderNumber=" + folderNumber +
                '}';
    }
}
