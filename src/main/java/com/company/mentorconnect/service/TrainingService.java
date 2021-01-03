package com.company.mentorconnect.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.mentorconnect.exception.EndDateCoincideException;
import com.company.mentorconnect.exception.MentorSkillAlreadyExistsException;
import com.company.mentorconnect.exception.StartDateCoincideException;
import com.company.mentorconnect.model.Mentor;
import com.company.mentorconnect.model.Mentor_Skill;
import com.company.mentorconnect.model.Skills;
import com.company.mentorconnect.model.Training;
import com.company.mentorconnect.model.User;
import com.company.mentorconnect.repository.MentorRepository;
import com.company.mentorconnect.repository.Mentor_SkillRepository;
import com.company.mentorconnect.repository.TrainingRepository;
import com.company.mentorconnect.repository.UserRepository;

@Service
public class TrainingService {


	@Autowired
	private TrainingRepository trainingRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MentorRepository mentorRepository;
	@Autowired
	private Mentor_SkillRepository mentorSkillRepository;
	
	public void addSkillLogin(Mentor_Skill mentorSkill) throws MentorSkillAlreadyExistsException {
		String userName = mentorSkill.getMentor().getUser().getUserName();
		Skills skill = mentorSkill.getSkill();
		User user = userRepository.findByUserName(userName);
		Mentor mentor = mentorRepository.findByUser(user);
		List<Mentor_Skill> mentorExistingSkills = mentorSkillRepository.findAllByMentor(mentor);
		for(Mentor_Skill mentorExistingSkill:mentorExistingSkills){
			if(mentorExistingSkill.getSkill().getName().matches(skill.getName())){
				throw new MentorSkillAlreadyExistsException("This skill exists for this mentor");
			}
		}
		Mentor_Skill newMentorSkill = new Mentor_Skill();
		newMentorSkill.setMentor(mentor);
		newMentorSkill.setSelfRating(mentorSkill.getSelfRating());
		newMentorSkill.setYearsOfExperience(mentorSkill.getYearsOfExperience());
		newMentorSkill.setSkill(skill);
		mentorSkillRepository.save(mentorSkill);
	}
	
	@Transactional
	public List<Training> getIncompleteTraining(String userName) {
		User user = userRepository.findByUserName(userName);
		Mentor mentor = mentorRepository.findByUser(user);
		List<Training> trainingList = trainingRepository.findAllByMentor(mentor);
		List<Training> pendingTrainingList = new ArrayList<Training>();
		for(Training training:trainingList){
			if(training.getStatus().equals("Request Pending")){
				pendingTrainingList.add(training);
			}
		}
		return pendingTrainingList;
	}
	
	public void addTrainingRequest(Training training) throws StartDateCoincideException, EndDateCoincideException {
		List<Training> trainingListMentor = trainingRepository.findAllByMentor(training.getMentor());
		List<Training> trainingListUser = trainingRepository.findAllByUser(training.getUser());
		for(Training existingTraining:trainingListMentor){
			if(existingTraining.getStatus().equals("Approved")){
				if(training.getStartDate().after(existingTraining.getStartDate()) || training.getStartDate().equals(existingTraining.getStartDate())){
					if(training.getStartDate().before(existingTraining.getEndDate()) || training.getStartDate().equals(existingTraining.getEndDate())){
						throw new StartDateCoincideException("Start Date Coincides with an existing approved training of Mentor");
					}
				}
				else if(training.getEndDate().after(existingTraining.getStartDate()) || training.getEndDate().equals(existingTraining.getStartDate())){
					if(training.getEndDate().before(existingTraining.getEndDate()) || training.getEndDate().equals(existingTraining.getEndDate())){
						throw new EndDateCoincideException("End Date Coincides with an existing approved training of Mentor");
					}
				}
			}
		}
		for(Training existingTraining:trainingListUser){
			if(existingTraining.getStatus().equals("Approved")){
				if(training.getStartDate().after(existingTraining.getStartDate()) || training.getStartDate().equals(existingTraining.getStartDate())){
					if(training.getStartDate().before(existingTraining.getEndDate()) || training.getStartDate().equals(existingTraining.getEndDate())){
						throw new StartDateCoincideException("Start Date Coincides with an existing approved training of User");
					}
				}
				else if(training.getEndDate().after(existingTraining.getStartDate()) || training.getEndDate().equals(existingTraining.getStartDate())){
					if(training.getEndDate().before(existingTraining.getEndDate()) || training.getEndDate().equals(existingTraining.getEndDate())){
						throw new EndDateCoincideException("End Date Coincides with an existing approved training of User");
					}
				}
			}
		}
		trainingRepository.save(training);
	}

	public void editStatus(Training training) {
		trainingRepository.save(training);
	}


}
