package com.quantcast.cookie.model;

import com.quantcast.cookie.exception.InvalidCookieTimeException;
import com.quantcast.cookie.exception.InvalidCookieValueException;
import com.quantcast.cookie.util.AppUtil;
import com.quantcast.cookie.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.Objects;

public class Cookie implements Comparable<Cookie> {

    private String value;

    private LocalDateTime dateTime;

    private String date;

    public Cookie() {

    }

    public Cookie(String value, String timestamp) {
        this.value = value;
        if(ValidationUtil.isEmpty(this.value)) {
            throw new InvalidCookieValueException();
        }

        this.setDateTime(timestamp);

        if (this.getDateTime() == null) {
            throw new InvalidCookieTimeException("Invalid date time");
        }
    }

    public String getValue() {
        return value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(String timestamp) {
        LocalDateTime dateTime = AppUtil.getDateTimeFromTimestamp(timestamp);
        this.setDateTime(dateTime);
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.date = this.dateTime.toLocalDate().toString();
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "CookieDetails{" +
                "cookie='" + value + '\'' +
                ", dateTime=" + dateTime +
                ", date='" + date + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Cookie cookieDetails = (Cookie) obj;
        return cookieDetails.value.equals(this.value) && cookieDetails.getDate().equals(this.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, date);
    }

    @Override
    public int compareTo(Cookie o) {
        return this.value.compareTo(o.value);
    }
}
