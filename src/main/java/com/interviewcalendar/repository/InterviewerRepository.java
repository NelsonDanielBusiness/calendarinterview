package com.interviewcalendar.repository;


import com.interviewcalendar.domain.Interviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Interviewer entity.
 */
@Repository
public interface InterviewerRepository extends JpaRepository<Interviewer, Long>, JpaSpecificationExecutor<Interviewer> {

}
