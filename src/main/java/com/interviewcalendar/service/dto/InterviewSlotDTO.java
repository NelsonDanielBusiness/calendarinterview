package com.interviewcalendar.service.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Nelson Daniel
 */
public class InterviewSlotDTO {

    private LocalDate day;

    private String initialDate;

    private String endDate;

    public InterviewSlotDTO() {
    }

    public InterviewSlotDTO(LocalDate day, String initialDate, String endDate) {
        this.day = day;
        this.initialDate = initialDate;
        this.endDate = endDate;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterviewSlotDTO that = (InterviewSlotDTO) o;
        return Objects.equals(day, that.day) && Objects.equals(initialDate, that.initialDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, initialDate, endDate);
    }
}
