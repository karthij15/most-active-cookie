package com.quantcast.cookie.util;

import java.time.format.DateTimeFormatter;

public class Constants {

    public static final String LOG_FILE_ARG = "f";
    public static final String QUERY_DATE_ARG = "d";
    public static final String LONG_NAME_FOR_LOG_FILE_OPTION = "logFilePath";
    public static final String LONG_NAME_FOR_LOG_QUERY_DATE_OPTION = "queryDate";
    public static final String DESC_LOG_FILE_OPTION = "provide log file path";
    public static final String DESC_QUERY_DATE_OPTION = "please provide query date as yyyy-MM-dd";
    public static final boolean FLAG_IS_INITIALIZED = true;
    public static final boolean FLAG_IS_MANDATORY = true;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
    public static final String CSV_COLUMN_SEPERATOR = ",";
}
