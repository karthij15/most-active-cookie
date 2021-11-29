package com.quantcast.cookie.util;

import java.io.File;
import java.util.List;

public class ValidationUtil {

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidLogLine(String line) {
        return !isEmpty(line) && line.indexOf(Constants.CSV_COLUMN_SEPERATOR) != -1;
    }

    public static boolean isValidLogFile(String logFilePath) {
        if (isEmpty(logFilePath))
            return false;

        File logFile = new File(logFilePath);
        return logFile.exists();
    }

    public static boolean isInvalidList(List<? extends Object> list) {
        return (list == null || list.isEmpty());
    }
}
