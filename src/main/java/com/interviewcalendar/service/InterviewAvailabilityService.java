package com.interviewcalendar.service;

import com.interviewcalendar.domain.InterviewAvailability;
import com.interviewcalendar.repository.InterviewAvailabilityRepository;
import com.interviewcalendar.service.dto.InterviewAvailabilityDTO;
import com.interviewcalendar.service.dto.InterviewAvailabilityUserCreationDTO;
import com.interviewcalendar.service.dto.InterviewSlotDTO;
import com.interviewcalendar.service.mapper.InterviewAvailabilityCreationMapper;
import com.interviewcalendar.service.mapper.InterviewAvailabilityMapper;
import com.interviewcalendar.service.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link InterviewAvailability}.
 */
@Service
@Transactional
public class InterviewAvailabilityService {

    private static final long INTERVIEW_TIME = 1L;
    private final Logger log = LoggerFactory.getLogger(InterviewAvailabilityService.class);

    private final InterviewAvailabilityRepository interviewerAvailabilityRepository;

    private final InterviewAvailabilityMapper interviewerAvailabilityMapper;
    private final InterviewAvailabilityCreationMapper interviewAvailabilityCreationMapper;

    public InterviewAvailabilityService(InterviewAvailabilityRepository interviewerAvailabilityRepository, InterviewAvailabilityMapper interviewerAvailabilityMapper, InterviewAvailabilityCreationMapper interviewAvailabilityCreationMapper) {
        this.interviewerAvailabilityRepository = interviewerAvailabilityRepository;
        this.interviewerAvailabilityMapper = interviewerAvailabilityMapper;
        this.interviewAvailabilityCreationMapper = interviewAvailabilityCreationMapper;
    }

    /**
     * Save a interviewerAvailability.
     *
     * @param interviewAvailabilityUserCreationDTO the entity to save.
     * @return the persisted entity.
     */
    public List<InterviewAvailabilityDTO> saveAll(@Valid InterviewAvailabilityUserCreationDTO interviewAvailabilityUserCreationDTO) {
        log.debug("Request to save InterviewAvailability : {}", interviewAvailabilityUserCreationDTO);
        List<InterviewAvailability> interviewerAvailability = interviewAvailabilityCreationMapper.toInterviewAvailabilityDTO(interviewAvailabilityUserCreationDTO);
        interviewerAvailability = interviewerAvailabilityRepository.saveAll(interviewerAvailability);
        return interviewerAvailability.stream().map(interviewerAvailabilityMapper::toDto).collect(Collectors.toList());
    }

    /**
     * List all the interviewerAvailabilities by Candidate id and Interviewer I«id in.
     *
     * @param candidateId    candidate id
     * @param interviewerIds interviewer ids
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InterviewAvailabilityDTO> findAllByCandidateIdOrInterviewerIdIn(Long candidateId, Set<Long> interviewerIds) {
        log.debug("Request to get all InterviewAvailabilities");
        return interviewerAvailabilityRepository.findAllByCandidateIdOrInterviewerIdIn(candidateId, interviewerIds).stream().map(interviewerAvailabilityMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Collect all interview availabilities and all candidate availabilities, then matches and returns the common availabilities.
     *
     * @param allAvailabilities allAvailabilities from the interviewers and the candidate
     */
    public List<InterviewSlotDTO> arrange(List<InterviewAvailabilityDTO> allAvailabilities) {
        List<InterviewAvailabilityDTO> availabilitiesInterviewer = allAvailabilities.stream().filter(avl -> avl.getInterviewerId() != null).collect(Collectors.toList());
        List<InterviewAvailabilityDTO> availabilitiesCandidate = allAvailabilities.stream().filter(avl -> avl.getCandidateId() != null).collect(Collectors.toList());

        Set<InterviewSlotDTO> hourSlotsInterviewer = createHourSlotForAvailabilities(availabilitiesInterviewer);
        Set<InterviewSlotDTO> hourSlotsCandidate = createHourSlotForAvailabilities(availabilitiesCandidate);

        return findPossibleInterview(hourSlotsInterviewer, hourSlotsCandidate);
    }

    /**
     * @param availabilitiesInterviewer interviewer hour slots availabilities
     * @param availabilitiesCandidate   candidate hour slots availabilities
     * @return the common hour slots for candidate and interviewer
     */
    public List<InterviewSlotDTO> findPossibleInterview(Set<InterviewSlotDTO> availabilitiesInterviewer, Set<InterviewSlotDTO> availabilitiesCandidate) {
        List<InterviewSlotDTO> commonElements = new ArrayList<>(availabilitiesCandidate);
        commonElements.retainAll(availabilitiesInterviewer);
        return commonElements;
    }

    /**
     * Creates hour slots for a possible interview
     *
     * @param allAvailabilities availability
     * @return hour slot available base on availability
     */
    public Set<InterviewSlotDTO> createHourSlotForAvailabilities(List<InterviewAvailabilityDTO> allAvailabilities) {
        return allAvailabilities
                .stream()
                .map(this::createHourSlotsForInterviewAvailable)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Creates a time slot of 1h hour based on the availability for the day
     *
     * @param interviewAvailabilityDTO availability
     * @return time slot of 1h
     */
    public List<InterviewSlotDTO> createHourSlotsForInterviewAvailable(InterviewAvailabilityDTO interviewAvailabilityDTO) {
        List<InterviewSlotDTO> interviewSlots = new ArrayList<>();

        LocalTime initialTime = DateUtil.stringToTime(interviewAvailabilityDTO.getInitialDate());
        LocalTime endTime = DateUtil.stringToTime(interviewAvailabilityDTO.getEndDate());
        while (initialTime.isBefore(endTime)) {
            LocalTime interviewEndTime = initialTime.plusHours(INTERVIEW_TIME);
            interviewSlots.add(new InterviewSlotDTO(interviewAvailabilityDTO.getDay(), DateUtil.timeToString(initialTime), DateUtil.timeToString(interviewEndTime)));
            initialTime = interviewEndTime;
        }

        return interviewSlots;
    }
}
