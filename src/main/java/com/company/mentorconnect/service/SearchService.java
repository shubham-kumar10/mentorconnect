package com.company.mentorconnect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.mentorconnect.model.Skills;
import com.company.mentorconnect.repository.MentorRepository;
import com.company.mentorconnect.repository.SkillRepository;



@Service
public class SearchService {

	@Autowired
	MentorRepository mentorRepository;
	
	@Autowired
	SkillRepository skillRepository;
	
	public void searchMentor(int skillid) {
		Optional<Skills> sks = skillRepository.findById(skillid);
		System.out.println(sks);
	}
}
