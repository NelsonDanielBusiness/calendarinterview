package com.interviewcalendar.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "interviewer")
public class Interviewer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @OneToMany(mappedBy = "interviewer", fetch = FetchType.LAZY)
    private Set<InterviewAvailability> interviewAvailabilities = new HashSet<>();

    public Interviewer() {
    }

    public Interviewer(Long id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}