package com.company.mentorconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.mentorconnect.model.Mentor;
import com.company.mentorconnect.model.MentorDTO;
import com.company.mentorconnect.repository.MentorRepository;
import com.company.mentorconnect.repository.SkillRepository;

@Service
public class MentorService {

	@Autowired
	MentorRepository mentorRepository;

	@Autowired
	SkillRepository skillRepository;

	public void editSkill(MentorDTO mentorDetails) {

	}

	public List<Mentor> getAllMentors() {
		return mentorRepository.findAll();
	}

	public List<Mentor> getMentorsBySkill(int skillId) {
		return mentorRepository.findBySkills(skillRepository.findById(skillId).get());
	}

}
