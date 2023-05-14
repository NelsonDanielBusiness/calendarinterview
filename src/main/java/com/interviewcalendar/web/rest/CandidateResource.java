package com.interviewcalendar.web.rest;

import com.interviewcalendar.service.CandidateService;
import com.interviewcalendar.service.dto.CandidateDTO;
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
 * REST controller for managing the candidates.
 */
@RestController
@RequestMapping("/candidates")
public class CandidateResource {
    private final Logger log = LoggerFactory.getLogger(CandidateResource.class);

    private final CandidateService candidateService;

    public CandidateResource(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    /**
     * {@code GET  /candidates} : get all the candidates.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of candidates in body.
     */
    @GetMapping()
    public ResponseEntity<List<CandidateDTO>> getAllCandidates(Pageable pageable) {
        log.info("REST request to get candidates: {}", pageable);
        Page<CandidateDTO> page = candidateService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code POST  /candidates} : Create a new candidate.
     *
     * @param candidateDTO the candidateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new candidateDTO, or with status {@code 400 (Bad Request)} if the candidate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping
    public ResponseEntity<CandidateDTO> createCandidate(@Valid @RequestBody CandidateDTO candidateDTO) throws URISyntaxException {
        log.info("REST request to save Candidate : {}", candidateDTO);
        if (candidateDTO.getId() != null) {
            throw new InvalidParameterException("A new candidate cannot already have an ID");
        }

        CandidateDTO result = candidateService.save(candidateDTO);
        return ResponseEntity
                .created(new URI("/api/candidates/" + result.getId()))
                .body(result);
    }
}
