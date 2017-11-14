package de.hennig.moviearchive.services;

import com.google.common.collect.Lists;
import de.hennig.moviearchive.domain.Director;
import de.hennig.moviearchive.domain.Folder;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.builder.MovieBuilder;
import de.hennig.moviearchive.repositories.DirectorRepository;
import de.hennig.moviearchive.repositories.FolderRepository;
import de.hennig.moviearchive.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping
public class TestDataBuilder {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    FolderRepository folderRepository;


    @RequestMapping(value = "/createTestMovie", method = RequestMethod.GET, produces = {"application/json"})
    @Transactional
    public @ResponseBody
    String persistFolder() {
        Movie movie = createMovie();
        movieRepository.save(movie);
        return movie + " created";
    }

    @RequestMapping(value = "/getMovies", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody
    List<Movie> sizeFolder() {
        return Lists.newArrayList(movieRepository.findAll());
    }

    private Movie createMovie() {
        return new MovieBuilder()
                .withTitle("TestMovie")
                .withDirector(createDirector())
                .withFolder(getFolder())
                .build();
    }

    private Director createDirector() {
        Director director = new Director();
        director.setFirstName("Markus");
        director.setLastName("Regie");
        directorRepository.save(director);
        return director;
    }

    private Folder getFolder() {
        Folder folder;
        folder = folderRepository.findByFolderNumber(1);
        if (folder != null) {
            return folder;
        } else {
            return createFolder();
        }
    }

    private Folder createFolder() {
        Folder folder = new Folder();
        folder.setFolderNumber(1);
        folderRepository.save(folder);
        return folder;
    }
}
