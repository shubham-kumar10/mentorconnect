package com.company.mentorconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.mentorconnect.model.Skills;

public interface SkillRepository extends JpaRepository<Skills, Integer>{

	Skills findByName(String name);
	
}
