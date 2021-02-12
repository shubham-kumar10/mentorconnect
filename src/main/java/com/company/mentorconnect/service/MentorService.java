package com.company.mentorconnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.mentorconnect.model.Mentor;
import com.company.mentorconnect.model.MentorDTO;
import com.company.mentorconnect.model.Mentor_Skill;
import com.company.mentorconnect.model.SkillDetails;
import com.company.mentorconnect.model.Skills;
import com.company.mentorconnect.repository.MentorRepository;
import com.company.mentorconnect.repository.Mentor_SkillRepository;
import com.company.mentorconnect.repository.SkillRepository;

@Service
public class MentorService {

	@Autowired
	MentorRepository mentorRepository;

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	Mentor_SkillRepository mentor_skillRepository;

	public void editSkill(MentorDTO mentorDetails) {

		Optional<Mentor> newMentor = mentorRepository.findById(mentorDetails.getMentor().getId());
		if (newMentor.isPresent()) {
			
			for (SkillDetails skill : mentorDetails.getSkills()) {
				Skills mentorSkill = skillRepository.findById(skill.getSkill_id()).get();
				float self_rating = skill.getSelf_rating();
				int yearsOfExperience = skill.getYearsOfExperience();
				Mentor_Skill mentor_skill = new Mentor_Skill(newMentor.get(), mentorSkill, self_rating,
						yearsOfExperience);
				mentor_skillRepository.save(mentor_skill);
			}
			
		}
		else{
			
		}

	}
	
	public List<Mentor> getMentors(){
		return mentorRepository.findAll();
	}
	
	public List<Mentor> getMentorsBySkill(){
		return mentorRepository.findAll();
	}
}
