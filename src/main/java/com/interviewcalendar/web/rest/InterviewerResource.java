package com.interviewcalendar.web.rest;

import com.interviewcalendar.service.InterviewerService;
import com.interviewcalendar.service.dto.InterviewerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * REST controller for managing the interviewers.
 */
@RestController
@RequestMapping("/interviewers")
public class InterviewerResource {
    private final Logger log = LoggerFactory.getLogger(InterviewerResource.class);

    private final InterviewerService interviewerService;

    public InterviewerResource(InterviewerService interviewerService) {
        this.interviewerService = interviewerService;
    }

    /**
     * {@code GET  /interviewers} : get all the interviewers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of interviewers in body.
     */
    @GetMapping()
    public ResponseEntity<List<InterviewerDTO>> getAllInterviewers(Pageable pageable) {
        log.info("REST request to get interviewers: {}", pageable);
        Page<InterviewerDTO> page = interviewerService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code POST  /interviewers} : Create a new interviewer.
     *
     * @param interviewerDTO the interviewerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new interviewerDTO, or with status {@code 400 (Bad Request)} if the interviewer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping
    public ResponseEntity<InterviewerDTO> createInterviewer(@Valid @RequestBody InterviewerDTO interviewerDTO) throws URISyntaxException {
        log.info("REST request to save Interviewer : {}", interviewerDTO);
        if (interviewerDTO.getId() != null) {
            throw new InvalidParameterException("A new interviewer cannot already have an ID");
        }

        InterviewerDTO result = interviewerService.save(interviewerDTO);
        return ResponseEntity
                .created(new URI("/api/interviewers/" + result.getId()))
                .body(result);
    }
}
