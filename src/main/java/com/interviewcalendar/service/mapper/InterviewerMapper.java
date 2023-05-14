package com.interviewcalendar.service.mapper;

import com.interviewcalendar.domain.Interviewer;
import com.interviewcalendar.service.dto.InterviewerDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Interviewer} and its DTO {@link InterviewerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InterviewerMapper extends EntityMapper<InterviewerDTO, Interviewer> {

    default Interviewer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Interviewer interviewer = new Interviewer();
        interviewer.setId(id);
        return interviewer;
    }
}
