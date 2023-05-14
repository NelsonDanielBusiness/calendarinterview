package com.interviewcalendar.service.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * @author Nelson Daniel
 */
public class InterviewAvailabilityCreationDTO {

    private Long id;

    @NotNull
    private LocalDate day;

    @NotNull
    @Pattern(regexp = "^(0?[1-9]|1[0-2]):00 (am|pm)$")
    private String initialDate;

    @NotNull
    @Pattern(regexp = "^(0?[1-9]|1[0-2]):00 (am|pm)$")
    private String endDate;

    public InterviewAvailabilityCreationDTO() {
    }

    public InterviewAvailabilityCreationDTO(LocalDate day, String initialDate, String endDate) {
        this.day = day;
        this.initialDate = initialDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
