package com.company.mentorconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.mentorconnect.model.User;
import com.company.mentorconnect.repository.StudentRepository;



@Service
public class StudentService {

	@Autowired
	private StudentRepository userRepository;
	
	@Transactional
	public User getUserByUserName(String userName) {
		return userRepository.findByUserName(userName);	
	}
}
