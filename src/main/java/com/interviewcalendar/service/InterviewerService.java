package com.interviewcalendar.service;

import com.interviewcalendar.domain.Interviewer;
import com.interviewcalendar.repository.InterviewerRepository;
import com.interviewcalendar.service.dto.InterviewerDTO;
import com.interviewcalendar.service.mapper.InterviewerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Interviewer}.
 */
@Service
@Transactional
public class InterviewerService {

    private final Logger log = LoggerFactory.getLogger(InterviewerService.class);

    private final InterviewerRepository interviewerRepository;

    private final InterviewerMapper interviewerMapper;

    public InterviewerService(InterviewerRepository interviewerRepository, InterviewerMapper interviewerMapper) {
        this.interviewerRepository = interviewerRepository;
        this.interviewerMapper = interviewerMapper;
    }

    /**
     * Save a interviewer.
     *
     * @param interviewerDTO the entity to save.
     * @return the persisted entity.
     */
    public InterviewerDTO save(InterviewerDTO interviewerDTO) {
        log.debug("Request to save Interviewer : {}", interviewerDTO);
        Interviewer interviewer = interviewerMapper.toEntity(interviewerDTO);
        interviewer = interviewerRepository.save(interviewer);
        return interviewerMapper.toDto(interviewer);
    }

    /**
     * Get all the interviewers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InterviewerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Interviewers");
        return interviewerRepository.findAll(pageable)
                .map(interviewerMapper::toDto);
    }

    /**
     * Get one interviewer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InterviewerDTO> findOne(Long id) {
        log.debug("Request to get Interviewer : {}", id);
        return interviewerRepository.findById(id)
                .map(interviewerMapper::toDto);
    }

    /**
     * Delete the interviewer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Interviewer : {}", id);
        interviewerRepository.deleteById(id);
    }

}
