package com.company.mentorconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.mentorconnect.model.Mentor;
import com.company.mentorconnect.model.Skills;
import com.company.mentorconnect.model.User;

public interface MentorRepository extends JpaRepository<Mentor, Integer>{
	Mentor findByUser(User user);

	List<Mentor> findBySkills(Skills skill);
}
