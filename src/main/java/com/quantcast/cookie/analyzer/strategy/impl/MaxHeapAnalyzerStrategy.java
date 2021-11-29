package com.quantcast.cookie.analyzer.strategy.impl;

import com.quantcast.cookie.analyzer.strategy.AnalyzerStrategy;
import com.quantcast.cookie.model.Cookie;

import java.util.*;

/*
Using priority to queue to sort the cookies based on its occurrence
 */
public class MaxHeapAnalyzerStrategy implements AnalyzerStrategy<Map<Cookie, Integer>, List<Cookie>> {

    @Override
    public List<Cookie> predict(Map<Cookie, Integer> cookieFrequencyMap) {
        List<Cookie> maxOccurrenceCookieList = new ArrayList<>();

        PriorityQueue<Map.Entry<Cookie, Integer>> queue = new PriorityQueue(new CookieComparator());

        for (Map.Entry<Cookie, Integer> entry : cookieFrequencyMap.entrySet()) {
            queue.offer(entry);
        }

        Map<Integer, List<Cookie>> occurrenceMap = new LinkedHashMap<>();
        int topFrequency = 0;
        while (!queue.isEmpty()) {
            int frequency = queue.peek().getValue();
            topFrequency = Math.max(frequency, topFrequency);

            if (frequency < topFrequency) // only most active cookie will be considered
                break;

            maxOccurrenceCookieList.add(queue.poll().getKey());
        }

        return maxOccurrenceCookieList;
    }

    class CookieComparator implements Comparator<Map.Entry<Cookie, Integer>> {

        @Override
        public int compare(Map.Entry<Cookie, Integer> entry1, Map.Entry<Cookie, Integer> entry2) {
            return Integer.compare(entry2.getValue(), entry1.getValue());
        }
    }

}
