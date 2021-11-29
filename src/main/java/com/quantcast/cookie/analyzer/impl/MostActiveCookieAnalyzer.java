package com.quantcast.cookie.analyzer.impl;

import com.quantcast.cookie.analyzer.LogAnalyzer;
import com.quantcast.cookie.analyzer.strategy.AnalyzerStrategy;
import com.quantcast.cookie.analyzer.strategy.impl.MaxHeapAnalyzerStrategy;
import com.quantcast.cookie.io.LogReader;
import com.quantcast.cookie.io.impl.CookieLogReader;
import com.quantcast.cookie.model.Cookie;
import com.quantcast.cookie.request.CookieRequest;
import com.quantcast.cookie.util.ValidationUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
Custom log analyzer to find max occurrence of a cookie at the give query date
 */
public class MostActiveCookieAnalyzer implements LogAnalyzer<CookieRequest, List<Cookie>> {

    final static Logger LOG = Logger.getLogger(MostActiveCookieAnalyzer.class.getName());

    /*
    algorithm to find the max occurrence
     */
    private final AnalyzerStrategy strategy;

    public MostActiveCookieAnalyzer() {
        this.strategy = new MaxHeapAnalyzerStrategy(); //as default strategy
    }

    public MostActiveCookieAnalyzer(AnalyzerStrategy strategy) { //provision to change strategy
        this.strategy = strategy;
    }

    @Override
    public List<Cookie> analyze(CookieRequest request) {

        List<Cookie> mostActiveCookieList = new ArrayList<>();

        try {
            LogReader<CookieRequest, Cookie> fileReader = new CookieLogReader();
            List<Cookie> cookieList = fileReader.read(request);

            if (ValidationUtil.isInvalidList(cookieList)) {
                return mostActiveCookieList;
            }

            Map<Cookie, Integer> cookieFrequencyMap = constructCookieFrequencyMap(cookieList);
            mostActiveCookieList = (List<Cookie>) strategy.predict(cookieFrequencyMap);

        } catch (Exception e) {
            LOG.debug("Exception during analyzing most active cookie " + e.getMessage());
            throw e;
        }

        return mostActiveCookieList;
    }

    /*
    cookie - frequency as key - value pair
     */
    private Map<Cookie, Integer> constructCookieFrequencyMap(List<Cookie> cookieList) {
        Map<Cookie, Integer> cookieFrequencyMap = new LinkedHashMap<>();

        for (Cookie cookie : cookieList) {
            int frequency = cookieFrequencyMap.getOrDefault(cookie, 0);
            frequency++;

            cookieFrequencyMap.put(cookie, frequency);
        }

        return cookieFrequencyMap;
    }
}
