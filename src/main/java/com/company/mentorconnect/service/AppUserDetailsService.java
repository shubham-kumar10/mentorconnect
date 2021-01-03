package com.company.mentorconnect.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.mentorconnect.exception.UserAlreadyExistsException;
import com.company.mentorconnect.model.AppUser;
import com.company.mentorconnect.model.Mentor;
import com.company.mentorconnect.model.MentorDTO;
import com.company.mentorconnect.model.Mentor_Skill;
import com.company.mentorconnect.model.SkillDetails;
import com.company.mentorconnect.model.Skills;
import com.company.mentorconnect.model.User;
import com.company.mentorconnect.repository.MentorRepository;
import com.company.mentorconnect.repository.Mentor_SkillRepository;
import com.company.mentorconnect.repository.SkillRepository;
import com.company.mentorconnect.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppUserDetailsService.class);
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	Mentor_SkillRepository mentor_skillRepository;
	
	@Autowired
	MentorRepository mentorRepository;
	
	@Autowired
	SkillRepository skillRepository;

	public AppUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		else {
			LOGGER.info("user is:" + user);
			AppUser appUser = new AppUser(user);
			LOGGER.info("userDetails is: " + appUser);
			return appUser;
		}

	}

	public void signup(User newUser) throws UserAlreadyExistsException {
		LOGGER.info("NEW User IS: " + newUser);
		System.out.println(newUser);
		User user = userRepository.findByUserName(newUser.getUserName());
		if (user != null) {
			throw new UserAlreadyExistsException();
		} else {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
			newUser.setPassword(encodedPassword);
			userRepository.save(newUser);
		}

	}
	
	@Transactional
	public void addMentor(MentorDTO mentorDetails){
		Mentor mentor = mentorDetails.getMentor();
		User user = userRepository.findByUserName(mentor.getUser().getUserName());
		if(user!=null)
			mentor.setUser(user);
		mentorRepository.save(mentor);
		for(SkillDetails skill : mentorDetails.getSkills()){
			Skills mentorSkill = skillRepository.findById(skill.getSkill_id()).get();
			float self_rating = skill.getSelf_rating();
			int yearsOfExperience = skill.getYearsOfExperience();
			Mentor_Skill mentor_skill = new Mentor_Skill(mentor,mentorSkill,self_rating,yearsOfExperience);
			mentor_skillRepository.save(mentor_skill);
		}
	}
}
