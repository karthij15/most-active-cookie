package com.quantcast.cookie;

public class CookieApplication {

    public static void main(String... args) {
        MostActiveCookieFinderApplication finder = new MostActiveCookieFinderApplication();
        finder.process(args);
    }
}
