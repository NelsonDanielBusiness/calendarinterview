package com.interviewcalendar.web.rest;

import com.interviewcalendar.service.InterviewAvailabilityService;
import com.interviewcalendar.service.dto.InterviewAvailabilityDTO;
import com.interviewcalendar.service.dto.InterviewAvailabilityUserCreationDTO;
import com.interviewcalendar.web.rest.validators.InterviewAvailabilityValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * REST controller for managing the InterviewAvailabilities.
 */
@RestController
@RequestMapping("/interview-availabilities")
public class InterviewAvailabilityResource {
    private final Logger log = LoggerFactory.getLogger(InterviewAvailabilityResource.class);

    private final InterviewAvailabilityValidator interviewAvailabilityValidator;
    private final InterviewAvailabilityService interviewAvailabilityService;

    public InterviewAvailabilityResource(InterviewAvailabilityValidator interviewAvailabilityValidator, InterviewAvailabilityService interviewAvailabilityService) {
        this.interviewAvailabilityValidator = interviewAvailabilityValidator;
        this.interviewAvailabilityService = interviewAvailabilityService;
    }

    //TODO: With this approach any user can add availability to other user. Because validateBulkCreation only validates
    // if the user that is received is valid, we should look into this after implementing the authentication,
    // because we SHOULD receive user information directly from the token.

    /**
     * {@code POST  /interview-availabilities/batch} : Create interviewAvailabilityCreationDTO in bulk.
     *
     * @param interviewAvailabilityUserCreationDTO the  interviewAvailabilities to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new interviewAvailabilities, or with status {@code 400 (Bad Request)} if an error occurs.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/batch")
    public ResponseEntity<List<InterviewAvailabilityDTO>> createInterviewAvailability(@Valid @RequestBody InterviewAvailabilityUserCreationDTO interviewAvailabilityUserCreationDTO) {
        log.info("REST request to save InterviewAvailability : {}", interviewAvailabilityUserCreationDTO);

        if (!interviewAvailabilityValidator.validateBulkCreation(interviewAvailabilityUserCreationDTO)) {
            throw new InvalidParameterException("Invalid Parameters");
        }

        List<InterviewAvailabilityDTO> result = interviewAvailabilityService.saveAll(interviewAvailabilityUserCreationDTO);
        return ResponseEntity.ok(result);
    }

}
