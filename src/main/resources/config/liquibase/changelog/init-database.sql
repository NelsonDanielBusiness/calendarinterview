--liquibase formatted sql

--changeset nelson.daniel:05122023-1
--comment: Create table user
CREATE TABLE candidate
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name varchar(50)
)

--changeset nelson.daniel:05122023-2
--comment: Create table interviewers
CREATE TABLE interviewer
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name varchar(50)
)

--changeset nelson.daniel:05122023-3
--comment: Create table interview_availability
CREATE TABLE interview_availability
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    day DATE NOT NULL,
    initial_date TIME NOT NULL,
    end_date TIME NOT NULL,
    candidate_id INT,
    interviewer_id INT,
    FOREIGN KEY (candidate_id) REFERENCES candidate(id),
    FOREIGN KEY (interviewer_id) REFERENCES interviewer(id)
)
