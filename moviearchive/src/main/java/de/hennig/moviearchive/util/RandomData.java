package de.hennig.moviearchive.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomData {

    private RandomData() {
    }

    private final static int DEFAULT_STRING_LENGTH = 12;

    private final static String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";

    private final static Map<Character, String> letterMapping = new HashMap<>();

    private final static String digits = "0123456789";
    private final static String digitsWoZero = "123456789";

    private final static String alphaNumeric = upperCaseLetters + lowerCaseLetters + digits;

    static {
        letterMapping.put('A', "10");
        letterMapping.put('B', "11");
        letterMapping.put('C', "12");
        letterMapping.put('D', "13");
        letterMapping.put('E', "14");
        letterMapping.put('F', "15");
        letterMapping.put('G', "16");
        letterMapping.put('H', "17");
        letterMapping.put('I', "18");
        letterMapping.put('J', "19");
        letterMapping.put('K', "20");
        letterMapping.put('L', "21");
        letterMapping.put('M', "22");
        letterMapping.put('N', "23");
        letterMapping.put('O', "24");
        letterMapping.put('P', "25");
        letterMapping.put('Q', "26");
        letterMapping.put('R', "27");
        letterMapping.put('S', "28");
        letterMapping.put('T', "29");
        letterMapping.put('U', "30");
        letterMapping.put('V', "31");
        letterMapping.put('W', "32");
        letterMapping.put('X', "33");
        letterMapping.put('Y', "34");
        letterMapping.put('Z', "35");
    }

    public static Integer year() {
        return ThreadLocalRandom.current().nextInt(1600, 2018);
    }

    public static String name(int length) {
        return upperCaseString(1) + lowerCaseString(length - 1);
    }

    public static String name() {
        return name(DEFAULT_STRING_LENGTH);
    }

    public static String lowerCaseString(int length) {
        return string(lowerCaseLetters, length);
    }

    public static String lowerCaseString() {
        return lowerCaseString(DEFAULT_STRING_LENGTH);
    }

    public static String upperCaseString(int length) {
        return string(upperCaseLetters, length);
    }

    public static String digits(int length) {
        return string(digits, length);
    }

    private static String string(String characterSet, int length) {
        if (length <= 0) {
            return "";
        }

        Random rng = threadLocalRandom.get();
        char[] text = new char[length];

        for (int i = 0; i < length; i++) {
            text[i] = characterSet.charAt(rng.nextInt(characterSet.length()));
        }
        return new String(text);
    }

    private static final ThreadLocal<Random> threadLocalRandom = new ThreadLocal<Random>() {
        @Override
        protected Random initialValue() {
            return new Random();
        }
    };

}
