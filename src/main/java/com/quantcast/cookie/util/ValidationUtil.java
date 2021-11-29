package com.quantcast.cookie.util;

public class ValidationUtil {

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidLogLine(String line) {
        return !isEmpty(line) && line.indexOf(Constants.CSV_COLUMN_SEPERATOR) != -1;
    }
}
