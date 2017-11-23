package de.hennig.moviearchive.services.Mapper;

import de.hennig.moviearchive.domain.core.Country;

import java.util.List;

public class CountryMapper {

    private static final int NAME = 1;
    private static final int ACTIVE_FLAG = 2;

    public static Country map(List<String> values) {
        if (values.isEmpty())
            throw new IllegalArgumentException();
        Country country = new Country();
        country.setName(values.get(NAME));
        country.setActiveFlag(Boolean.parseBoolean(values.get(ACTIVE_FLAG)));
        return country;
    }
}
