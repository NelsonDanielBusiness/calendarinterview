package com.interviewcalendar.web.rest;

import com.interviewcalendar.service.InterviewAvailabilityService;
import com.interviewcalendar.service.dto.InterviewAvailabilityDTO;
import com.interviewcalendar.service.dto.InterviewSlotDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * REST controller for managing the interviews.
 */
@RestController
@RequestMapping("/interview")
public class InterviewResource {
    private final Logger log = LoggerFactory.getLogger(InterviewResource.class);

    private final InterviewAvailabilityService interviewAvailabilityService;

    public InterviewResource(InterviewAvailabilityService interviewAvailabilityService) {
        this.interviewAvailabilityService = interviewAvailabilityService;
    }

    @GetMapping("/arrange")
    public ResponseEntity<List<InterviewSlotDTO>> createInterviewAvailability(@Valid @NotNull @RequestParam Long candidateId, @Valid @NotEmpty @RequestParam("interviewId") Set<Long> interviewIds) {
        log.info("REST request to get possible Interview for candidate {} and interviewers {}", candidateId, interviewIds);

        List<InterviewAvailabilityDTO> allAvailabilities = interviewAvailabilityService.findAllByCandidateIdOrInterviewerIdIn(candidateId, interviewIds);
        List<InterviewSlotDTO> possibleInterview = interviewAvailabilityService.arrange(allAvailabilities);

        return ResponseEntity.ok(possibleInterview);
    }
}
