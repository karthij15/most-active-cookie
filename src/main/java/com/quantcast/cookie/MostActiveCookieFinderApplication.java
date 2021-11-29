package com.quantcast.cookie;

import com.quantcast.cookie.analyzer.LogAnalyzer;
import com.quantcast.cookie.analyzer.impl.MostActiveCookieAnalyzer;
import com.quantcast.cookie.cli.CLIParser;
import com.quantcast.cookie.model.Cookie;
import com.quantcast.cookie.request.CookieRequest;
import org.apache.log4j.Logger;

import java.util.List;

public class MostActiveCookieFinderApplication {

    final static Logger LOG = Logger.getLogger(MostActiveCookieFinderApplication.class.getName());

    /*
    command line args format
    -f parameter for the filename to process
    -d parameter for query date (yyyy-MM-dd)
    please ensure there are no headers in csv file
     */
    public void process(String... args) {

        try {
            CLIParser cliParser = new CLIParser();
            CookieRequest request = cliParser.parse(args);

            LOG.debug("Request Info: File path - " + request.getLogFilePath() + " and Query Date - " + request.getQueryDate());

            LogAnalyzer<CookieRequest, List<Cookie>> analyzer = new MostActiveCookieAnalyzer();
            List<Cookie> mostActiveCookies = analyzer.analyze(request);

            for (Cookie cookie : mostActiveCookies) {
                System.out.println(cookie.getValue());
            }
        } catch (Exception e) {
            LOG.debug("Exception occurred " + e.getMessage());
            throw e;
        }
    }

}
