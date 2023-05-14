package com.interviewcalendar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Nelson Daniel
 */
@Entity
@Table(name = "interview_availability")
public class InterviewAvailability implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "day")
    private LocalDate day;

    @NotNull
    @Column(name = "initial_date")
    private LocalTime initialDate;

    @NotNull
    @Column(name = "end_date")
    private LocalTime endDate;

    @ManyToOne
    @JsonIgnoreProperties(value = "interviewAvailabilities", allowSetters = true)
    private Candidate candidate;

    @ManyToOne
    @JsonIgnoreProperties(value = "interviewAvailabilities", allowSetters = true)
    private Interviewer interviewer;

    public InterviewAvailability() {
    }


    public InterviewAvailability(Long id, LocalDate day, LocalTime initialDate, LocalTime endDate, Candidate candidate) {
        this.id = id;
        this.day = day;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.candidate = candidate;
    }

    public InterviewAvailability(Long id, LocalDate day, LocalTime initialDate, LocalTime endDate, Interviewer interviewer) {
        this.id = id;
        this.day = day;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.interviewer = interviewer;
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

    public LocalTime getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalTime initialDate) {
        this.initialDate = initialDate;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Interviewer interviewer) {
        this.interviewer = interviewer;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public LocalTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalTime endDate) {
        this.endDate = endDate;
    }
}
