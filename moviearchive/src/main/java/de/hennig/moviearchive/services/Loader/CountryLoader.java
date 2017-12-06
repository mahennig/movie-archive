package de.hennig.moviearchive.services.Loader;

import de.hennig.moviearchive.domain.core.Country;
import de.hennig.moviearchive.services.Mapper.CountryMapper;
import de.hennig.moviearchive.util.CSVFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountryLoader {

    List<Country> countryContainer = new ArrayList<>();

    @Bean
    public void load() {
        prepareFileImport();
    }

    private void prepareFileImport() {
        CSVFile csvFile = new CSVFile(new File("country.csv"));
        try {
            List<String> line = csvFile.readLine();
            Country country = CountryMapper.map(line);
            countryContainer.add(country);
        } catch (IOException e) {

        }
    }


}
