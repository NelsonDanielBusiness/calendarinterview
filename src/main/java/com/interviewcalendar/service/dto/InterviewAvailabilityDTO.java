package com.interviewcalendar.service.dto;

import java.time.LocalDate;

/**
 * @author Nelson Daniel
 */
public class InterviewAvailabilityDTO {


    private LocalDate day;

    private String initialDate;

    private String endDate;

    private Long interviewerId;
    private Long candidateId;

    public InterviewAvailabilityDTO() {
    }

    public InterviewAvailabilityDTO(LocalDate day, String initialDate, String endDate, Long interviewerId, Long candidateId) {
        this.day = day;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.interviewerId = interviewerId;
        this.candidateId = candidateId;
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

    public Long getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(Long interviewerId) {
        this.interviewerId = interviewerId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
}
