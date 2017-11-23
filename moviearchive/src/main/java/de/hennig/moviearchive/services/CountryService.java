package de.hennig.moviearchive.services;

import com.google.common.collect.Lists;
import de.hennig.moviearchive.domain.core.Country;
import de.hennig.moviearchive.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepo;

    public List<Country> getAllCountrys() {
        return Lists.newArrayList(countryRepo.findAll());
    }

}
