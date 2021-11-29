package com.quantcast.cookie.io.impl;

import com.quantcast.cookie.exception.CookieApplicationException;
import com.quantcast.cookie.exception.InvalidLogException;
import com.quantcast.cookie.io.LogReader;
import com.quantcast.cookie.model.Cookie;
import com.quantcast.cookie.request.CookieRequest;
import com.quantcast.cookie.util.Constants;
import com.quantcast.cookie.util.ValidationUtil;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
cookie log file reader
csv formatted file with no header
Sample format would be
AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00
each line will have cookie value and timestamp separated by comma
 */
public class CookieLogReader implements LogReader<CookieRequest, Cookie> {

    final static Logger LOG = Logger.getLogger(CookieLogReader.class.getName());

    @Override
    public List<Cookie> read(CookieRequest request) {

        LOG.debug("Reading log file..");

        String logFilePath = request.getLogFilePath();

        if (request == null || !ValidationUtil.isValidLogFile(logFilePath)) {
            LOG.debug("Invalid log file path. please check the input");
            throw new InvalidLogException();
        }

        List<Cookie> cookieList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(logFilePath))) {

            if (request.isHeaderPresentInFile()) {
                bufferedReader.readLine(); //skip header line if present
            }

            String line = null;
            boolean queryDateFound = false;

            while ((line = bufferedReader.readLine()) != null &&
                    ValidationUtil.isValidLogLine(line)) {
                String[] lineDetails = line.split(Constants.CSV_COLUMN_SEPERATOR);

                String cookieValue = lineDetails[0];
                String timestamp = lineDetails[1];

                Cookie cookie = new Cookie(cookieValue, timestamp);

                if (request.getQueryDate().equals(cookie.getDate())) { //consider only cookies falling under query date
                    cookieList.add(cookie);
                    queryDateFound = true;
                } else if (queryDateFound) {
                    break; // since log file is sorted by timestamp, skip processing older cookies
                }
            }
        } catch (IOException ie) {
            throw new CookieApplicationException(ie);
        }

        return cookieList;
    }
}
