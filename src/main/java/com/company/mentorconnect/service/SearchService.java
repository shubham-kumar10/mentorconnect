package com.company.mentorconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.mentorconnect.model.Mentor_Skill;
import com.company.mentorconnect.repository.Mentor_SkillRepository;
import com.company.mentorconnect.repository.SkillRepository;



@Service
public class SearchService {

	@Autowired
	Mentor_SkillRepository mentor_SkillRepository;

	@Autowired
	SkillRepository skillRepository;
	
	public List<Mentor_Skill> getAllDetails() {
		return mentor_SkillRepository.findAll();
	}
	
	public List<Mentor_Skill> searchMentor(int skillid) {
		return mentor_SkillRepository.findBySkill(skillRepository.findById(skillid).get());
	}
}
