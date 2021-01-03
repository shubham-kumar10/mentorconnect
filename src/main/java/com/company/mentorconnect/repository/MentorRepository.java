package com.company.mentorconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.mentorconnect.model.Mentor;
import com.company.mentorconnect.model.User;

public interface MentorRepository extends JpaRepository<Mentor, Integer>{
	Mentor findByUser(User user);


}
