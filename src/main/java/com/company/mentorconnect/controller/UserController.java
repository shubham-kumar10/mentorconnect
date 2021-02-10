package com.company.mentorconnect.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.mentorconnect.exception.UserAlreadyExistsException;
import com.company.mentorconnect.model.Mentor;
import com.company.mentorconnect.model.Skills;
import com.company.mentorconnect.model.User;
import com.company.mentorconnect.repository.SkillRepository;
import com.company.mentorconnect.service.AppUserDetailsService;

@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	AppUserDetailsService appUserDetailsService;

	@Autowired
	SkillRepository skillRepository;

	@PostMapping("/signup")
	public void signupUser(@RequestBody User newUser) throws UserAlreadyExistsException {
		LOGGER.info("Start of signupUser");
		LOGGER.debug("new User is {}", newUser);
		appUserDetailsService.signup(newUser);
		LOGGER.info("End of signupUser");
	}
	
	@GetMapping("/skills")
	public List<Skills> getAllSkills(){
		return skillRepository.findAll();
	}
	
	@PostMapping("/addmentor")
	public void signUpMentor(@RequestBody  Mentor mentorDetails) {
		appUserDetailsService.addMentor(mentorDetails);
	}
	
}
