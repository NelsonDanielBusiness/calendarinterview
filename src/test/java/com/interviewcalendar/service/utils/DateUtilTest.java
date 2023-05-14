package com.interviewcalendar.service.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DateUtilTest {

    @Test
    public void nightTimeToString() {
        LocalTime night = LocalTime.of(21, 0);
        String nightExpected = "09:00 pm";
        String nightString = DateUtil.timeToString(night);
        assertThat(nightString).isEqualTo(nightExpected);
    }

    @Test
    public void morningTimeToString(){
        LocalTime morning = LocalTime.of(9, 0);
        String morningExpected = "09:00 am";
        String morningString = DateUtil.timeToString(morning);
        assertThat(morningString).isEqualTo(morningExpected);

    }

    @Test
    public void stringTonightTime() {
        String nightTime = "09:00 pm";
        LocalTime localTime = DateUtil.stringToTime(nightTime);
        assertThat(localTime.getHour()).isEqualTo(21);
        assertThat(localTime.getMinute()).isEqualTo(0);
    }


    @Test
    public void stringToMorningTime() {
        String morningTime = "09:00 am";
        LocalTime localTime = DateUtil.stringToTime(morningTime);
        assertThat(localTime.getHour()).isEqualTo(9);
        assertThat(localTime.getMinute()).isEqualTo(0);
    }
}
