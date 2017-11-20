package de.hennig.moviearchive.util;


import org.apache.commons.lang3.StringUtils;

public class DateTimeUtil {

    public static boolean isValidYear(String year) {
        if ((year.charAt(0) == '1' || year.charAt(0) == '2')
                && year.length() == 4 && StringUtils.isNumeric(year)) return true;
        return false;
    }
}
