package com.interviewcalendar.web.rest.validators;

import com.interviewcalendar.domain.enums.Authority;
import com.interviewcalendar.service.CandidateService;
import com.interviewcalendar.service.InterviewerService;
import com.interviewcalendar.service.dto.InterviewAvailabilityUserCreationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Nelson Daniel
 */
@Service
public class InterviewAvailabilityValidator {
    private final Logger log = LoggerFactory.getLogger(InterviewAvailabilityValidator.class);

    private InterviewerService interviewerService;
    private CandidateService candidateService;

    public InterviewAvailabilityValidator(InterviewerService interviewerService, CandidateService candidateService) {
        this.interviewerService = interviewerService;
        this.candidateService = candidateService;
    }

    public boolean validateBulkCreation(InterviewAvailabilityUserCreationDTO interviewAvailabilityUserCreationDTO) {

        return userIsValid(interviewAvailabilityUserCreationDTO.getUserAuthority(), interviewAvailabilityUserCreationDTO.getUserId());
    }

    private boolean userIsValid(Authority userAuthority, Long userId) {
        switch (userAuthority) {
            case INTERVIEWER:
                return interviewerService.findOne(userId).isPresent();
            case CANDIDATE:
                return candidateService.findOne(userId).isPresent();
        }
        log.warn("User with authority {} and id {} does not exist ", userAuthority, userId);
        return false;
    }
}
