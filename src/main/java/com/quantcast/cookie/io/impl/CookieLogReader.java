package com.quantcast.cookie.io.impl;

import com.quantcast.cookie.exception.CookieApplicationException;
import com.quantcast.cookie.exception.InvalidCookieValueException;
import com.quantcast.cookie.exception.InvalidLogException;
import com.quantcast.cookie.io.LogReader;
import com.quantcast.cookie.model.Cookie;
import com.quantcast.cookie.request.CookieRequest;
import com.quantcast.cookie.util.Constants;
import com.quantcast.cookie.util.ValidationUtil;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CookieLogReader implements LogReader<CookieRequest, Cookie> {

    final static Logger LOG = Logger.getLogger(CookieLogReader.class.getName());

    @Override
    public List<Cookie> read(CookieRequest request) {

        LOG.debug("Reading log file..");

        if (request == null || ValidationUtil.isEmpty(request.getLogFilePath())) {
            LOG.debug("Invalid log file path. please check the input");
            throw new InvalidLogException();
        }

        List<Cookie> cookieList = new ArrayList<>();

        String logFilePath = request.getLogFilePath();
        File logFile = new File(logFilePath);

        if(!logFile.exists())
            throw new InvalidLogException();

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

                Cookie cookie = new Cookie(cookieValue, timestamp);;

                if (request.getQueryDate().equals(cookie.getDate())) { //consider cookies falling under query date
                    cookieList.add(cookie);
                    queryDateFound = true;
                } else if (queryDateFound) {
                    break; // skip processing older query date cookies
                }
            }
        } catch (FileNotFoundException fe) {
            LOG.debug("Invalid log file " + logFilePath);
            throw new InvalidLogException(fe);
        } catch (IOException ie) {
            throw new CookieApplicationException(ie);
        }

        return cookieList;
    }
}
