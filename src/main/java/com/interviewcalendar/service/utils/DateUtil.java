package com.interviewcalendar.service.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nelson Daniel
 */
public class DateUtil {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");


    public static LocalTime stringToTime(String time) {
        return LocalTime.parse(time, dateTimeFormatter);
    }

    public static String timeToString(LocalTime time) {
        return time.format(dateTimeFormatter);
    }

}
