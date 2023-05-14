package com.interviewcalendar.service.utils;

import com.interviewcalendar.InterviewCalendarApplication;
import com.interviewcalendar.service.InterviewAvailabilityService;
import com.interviewcalendar.service.dto.InterviewAvailabilityDTO;
import com.interviewcalendar.service.dto.InterviewSlotDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = InterviewCalendarApplication.class)
public class InterviewAvailabilityServiceTest {

    @Autowired
    private InterviewAvailabilityService interviewAvailabilityService;

    //List<InterviewAvailabilityDTO> availabilitiesInterviewer = List.of(,
    //      new InterviewAvailabilityDTO(LocalDate.now(), "09:00 am", "10:00 am", null , 1L));
    // List<InterviewAvailabilityDTO> availabilitiesCandidate

    @Test
    public void findPossibleInterviewTest() {
        InterviewSlotDTO commonSlot = new InterviewSlotDTO(LocalDate.now(), "09:00 am", "10:00 am");
        InterviewSlotDTO slot2 = new InterviewSlotDTO(LocalDate.now(), "10:00 am", "11:00 am");
        InterviewSlotDTO commonSlot2 = new InterviewSlotDTO(LocalDate.now(), "11:00 am", "12:00 pm");
        new InterviewAvailabilityDTO(LocalDate.now(), "09:00 am", "10:00 am", 1L , null);
        new InterviewSlotDTO(LocalDate.now(), "09:00 am", "10:00 am");
        Set<InterviewSlotDTO> hourSlotsInterviewer = Set.of(commonSlot2, slot2, commonSlot);
        Set<InterviewSlotDTO> hourSlotsCandidate = Set.of(commonSlot2, commonSlot);
        List<InterviewSlotDTO> possibleInterview = interviewAvailabilityService.findPossibleInterview(hourSlotsInterviewer, hourSlotsCandidate);

        assertTrue(possibleInterview.contains(commonSlot2));
        assertTrue(possibleInterview.contains(commonSlot));
        assertFalse(possibleInterview.contains(slot2));
    }

    @Test
    public void findPossibleInterviewNoCommonSlotTest() {
        InterviewSlotDTO slot1 = new InterviewSlotDTO(LocalDate.now(), "09:00 am", "10:00 am");
        InterviewSlotDTO slot2 = new InterviewSlotDTO(LocalDate.now(), "10:00 am", "11:00 am");
        Set<InterviewSlotDTO> hourSlotsInterviewer = Set.of( slot2);
        Set<InterviewSlotDTO> hourSlotsCandidate = Set.of(slot1);
        List<InterviewSlotDTO> possibleInterview = interviewAvailabilityService.findPossibleInterview(hourSlotsInterviewer, hourSlotsCandidate);

        assertTrue(possibleInterview.isEmpty());
    }

    @Test
    public void createHourSlotsForInterviewAvailableTest() {
        InterviewAvailabilityDTO interviewAvailabilityDTO = new InterviewAvailabilityDTO(LocalDate.now(), "09:00 am", "12:00 pm", 1L, null);

        InterviewSlotDTO slot1 = new InterviewSlotDTO(LocalDate.now(), "09:00 am", "10:00 am");
        InterviewSlotDTO slot2 = new InterviewSlotDTO(LocalDate.now(), "10:00 am", "11:00 am");
        InterviewSlotDTO slot3 = new InterviewSlotDTO(LocalDate.now(), "11:00 am", "12:00 pm");
        List<InterviewSlotDTO> hourSlots = interviewAvailabilityService.createHourSlotsForInterviewAvailable(interviewAvailabilityDTO);

        assertEquals( 3, hourSlots.size());
        assertTrue(hourSlots.contains(slot1));
        assertTrue(hourSlots.contains(slot2));
        assertTrue(hourSlots.contains(slot3));
    }


}
