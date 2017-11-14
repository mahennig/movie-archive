
package de.hennig.moviearchive.repositories;

import de.hennig.moviearchive.domain.Folder;
import de.hennig.moviearchive.domain.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FolderRepository extends CrudRepository<Folder, Long> {

    Folder findByFolderNumber(Integer folderNumber);

}

