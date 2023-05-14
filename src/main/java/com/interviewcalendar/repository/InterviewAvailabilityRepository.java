package com.interviewcalendar.repository;


import com.interviewcalendar.domain.InterviewAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Spring Data  repository for the InterviewAvailability entity.
 */
@Repository
public interface InterviewAvailabilityRepository extends JpaRepository<InterviewAvailability, Long>, JpaSpecificationExecutor<InterviewAvailability> {

    List<InterviewAvailability> findAllByCandidateIdOrInterviewerIdIn(Long candidateId, Set<Long> interviewerIds);
}
