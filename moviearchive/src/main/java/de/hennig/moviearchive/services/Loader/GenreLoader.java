package de.hennig.moviearchive.services.Loader;

import de.hennig.moviearchive.util.CSVFile;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class GenreLoader {

    public void load() {
        prepareFileImport();
    }

    private void prepareFileImport() {
        CSVFile csvFile = new CSVFile(new File("genre.csv"));
        try {
            List<String> line = csvFile.readLine();
            System.out.print(line);
        } catch (IOException e) {

        }
    }

}
