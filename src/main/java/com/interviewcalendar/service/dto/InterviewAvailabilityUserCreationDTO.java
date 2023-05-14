package com.interviewcalendar.service.dto;

import com.interviewcalendar.domain.enums.Authority;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the creation of {@link com.interviewcalendar.domain.InterviewAvailability} entity.
 */
public class InterviewAvailabilityUserCreationDTO implements Serializable {

    @NotNull
    private Authority userAuthority;

    @NotNull
    private Long userId;

    @NotNull
    @NotEmpty
    private List<@Valid InterviewAvailabilityCreationDTO> interviewAvailabilities;

    public InterviewAvailabilityUserCreationDTO() {
    }

    public InterviewAvailabilityUserCreationDTO(Authority userAuthority, Long userId, List<InterviewAvailabilityCreationDTO> interviewAvailabilities) {
        this.userAuthority = userAuthority;
        this.userId = userId;
        this.interviewAvailabilities = interviewAvailabilities;
    }

    public Authority getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(Authority userAuthority) {
        this.userAuthority = userAuthority;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<InterviewAvailabilityCreationDTO> getInterviewAvailabilities() {
        return interviewAvailabilities;
    }

    public void setInterviewAvailabilities(List<InterviewAvailabilityCreationDTO> interviewAvailabilities) {
        this.interviewAvailabilities = interviewAvailabilities;
    }
}