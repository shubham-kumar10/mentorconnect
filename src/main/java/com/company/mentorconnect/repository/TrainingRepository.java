package com.company.mentorconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.mentorconnect.model.Mentor;
import com.company.mentorconnect.model.Training;
import com.company.mentorconnect.model.User;


public interface TrainingRepository extends JpaRepository<Training, Long> {

	List<Training> findAllByMentor(Mentor mentor);

	List<Training> findAllByUser(User user);

}
