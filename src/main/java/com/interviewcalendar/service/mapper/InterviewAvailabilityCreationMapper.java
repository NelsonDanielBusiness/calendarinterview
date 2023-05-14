package com.interviewcalendar.service.mapper;

import com.interviewcalendar.domain.Candidate;
import com.interviewcalendar.domain.InterviewAvailability;
import com.interviewcalendar.domain.Interviewer;
import com.interviewcalendar.domain.enums.Authority;
import com.interviewcalendar.service.dto.InterviewAvailabilityUserCreationDTO;
import com.interviewcalendar.service.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nelson Daniel
 */
@Service
public class InterviewAvailabilityCreationMapper {

    private CandidateMapper candidateMapper;
    private InterviewerMapper interviewerMapper;

    public InterviewAvailabilityCreationMapper(CandidateMapper candidateMapper, InterviewerMapper interviewerMapper) {
        this.candidateMapper = candidateMapper;
        this.interviewerMapper = interviewerMapper;
    }


    public List<InterviewAvailability> toInterviewAvailabilityDTO(InterviewAvailabilityUserCreationDTO dto) {
        if (dto.getUserAuthority() == Authority.CANDIDATE) {
            Candidate candidate = candidateMapper.fromId(dto.getUserId());
            return dto.getInterviewAvailabilities().stream()
                    .map(ia -> new InterviewAvailability(null, ia.getDay(), DateUtil.stringToTime(ia.getInitialDate()), DateUtil.stringToTime(ia.getEndDate()), candidate))
                    .collect(Collectors.toList());
        } else if (dto.getUserAuthority() == Authority.INTERVIEWER) {
            Interviewer interviewer = interviewerMapper.fromId(dto.getUserId());
            return dto.getInterviewAvailabilities().stream()
                    .map(ia -> new InterviewAvailability(null, ia.getDay(), DateUtil.stringToTime(ia.getInitialDate()), DateUtil.stringToTime(ia.getEndDate()), interviewer))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
