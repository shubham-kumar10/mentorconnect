package com.company.mentorconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.mentorconnect.model.Mentor;
import com.company.mentorconnect.model.Mentor_Skill;
import com.company.mentorconnect.model.Skills;

public interface Mentor_SkillRepository extends JpaRepository<Mentor_Skill, Integer> {
	List<Mentor_Skill> findBySkill(Skills skill);
	List<Mentor_Skill> findAllByMentor(Mentor mentor);
}
