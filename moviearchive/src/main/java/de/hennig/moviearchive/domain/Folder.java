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

@Entity
@Table(name = "FOLDERS")
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
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
