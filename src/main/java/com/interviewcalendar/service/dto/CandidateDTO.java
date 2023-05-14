package com.interviewcalendar.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.interviewcalendar.domain.Candidate} entity.
 */
public class CandidateDTO implements Serializable {

    private Long id;

    @NotBlank(message = "First Name cannot be blank")
    @Size(max = 50)
    private String firstName;

    public CandidateDTO() {
    }

    public CandidateDTO(Long id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}