package com.quantcast.cookie.util;

import com.quantcast.cookie.exception.InvalidCookieTimeException;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AppUtil {

    final static Logger LOG = Logger.getLogger(AppUtil.class.getName());

    public static LocalDateTime getDateTimeFromTimestamp(String timestamp) {
        try {
            return LocalDateTime.parse(timestamp, Constants.DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            LOG.debug("Exception [" + e.getMessage() + "]while processing date " + timestamp);
            throw new InvalidCookieTimeException();
        }
    }
}
