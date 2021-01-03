package com.company.mentorconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.mentorconnect.exception.EndDateCoincideException;
import com.company.mentorconnect.exception.MentorSkillAlreadyExistsException;
import com.company.mentorconnect.exception.StartDateCoincideException;
import com.company.mentorconnect.model.Mentor_Skill;
import com.company.mentorconnect.model.Training;
import com.company.mentorconnect.service.TrainingService;

@RestController
public class TrainingController {

	@Autowired
	private TrainingService trainingService;
	
	@PostMapping("/addTrainingRequest")
	public void addTrainingRequest(@RequestBody Training training) throws StartDateCoincideException, EndDateCoincideException {
		trainingService.addTrainingRequest(training);
	}

	@GetMapping("/getIncompleteTraining/{userName}")
	public List<Training> getIncompleteTraining(@PathVariable String userName) {
		System.out.println(userName);
		return trainingService.getIncompleteTraining(userName);
	}
	
	@PostMapping("/editStatus")
	public void editStatus(@RequestBody  Training training){
		trainingService.editStatus(training);
	}
	
	@PostMapping("/addSkillLogin")
	public void addSkillLogin(@RequestBody Mentor_Skill mentorSkill) throws MentorSkillAlreadyExistsException{
		trainingService.addSkillLogin(mentorSkill);
	}
}
