package com.company.mentorconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.mentorconnect.model.Mentor;
import com.company.mentorconnect.model.MentorSkillDetails;
import com.company.mentorconnect.model.Skills;

public interface MentorSkillDetailsRepository extends JpaRepository<MentorSkillDetails, Integer> {
	List<MentorSkillDetails> findBySkill(Skills skill);
	List<MentorSkillDetails> findAllByMentor(Mentor mentor);
}
