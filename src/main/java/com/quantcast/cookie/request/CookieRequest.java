package com.quantcast.cookie.request;

public class CookieRequest {

    private String logFilePath;

    private String queryDate;

    private boolean isHeaderPresentInFile;

    public boolean isHeaderPresentInFile() {
        return isHeaderPresentInFile;
    }

    public void setHeaderPresentInFile(boolean headerPresentInFile) {
        isHeaderPresentInFile = headerPresentInFile;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }
}
