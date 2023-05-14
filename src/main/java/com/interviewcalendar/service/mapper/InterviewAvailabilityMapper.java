package com.interviewcalendar.service.mapper;

import com.interviewcalendar.domain.InterviewAvailability;
import com.interviewcalendar.service.dto.InterviewAvailabilityDTO;
import com.interviewcalendar.service.utils.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalTime;

/**
 * Mapper for the entity {@link InterviewAvailability} and its DTO {@link InterviewAvailabilityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InterviewAvailabilityMapper extends EntityMapper<InterviewAvailabilityDTO, InterviewAvailability> {

    @Override
    @Mapping(target = "initialDate", source = "initialDate", qualifiedByName = "timeToString")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "timeToString")
    @Mapping(target = "interviewerId", source = "interviewer.id")
    @Mapping(target = "candidateId", source = "candidate.id")
    InterviewAvailabilityDTO toDto(InterviewAvailability entity);

    @Override
    @Mapping(target = "initialDate", source = "initialDate", qualifiedByName = "stringToTime")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "stringToTime")
    @Mapping(target = "interviewer.id", source = "interviewerId")
    @Mapping(target = "candidate.id", source = "candidateId")
    InterviewAvailability toEntity(InterviewAvailabilityDTO dto);

    @Named("timeToString")
    default String startScheduleToTime(LocalTime time) {
        return DateUtil.timeToString(time);
    }

    @Named("stringToTime")
    default LocalTime startScheduleToTime(String time) {
        return DateUtil.stringToTime(time);
    }

}
