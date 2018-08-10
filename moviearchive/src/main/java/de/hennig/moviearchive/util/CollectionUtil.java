package de.hennig.moviearchive.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectionUtil {

    private static final int startYear = 1799;

    public static List<Integer> getYearCollection() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int currentYear = cal.get(Calendar.YEAR);
        Stream<Integer> intStream = IntStream.range(startYear, currentYear + 2).boxed();
        return intStream.collect(Collectors.toList());
    }

}
