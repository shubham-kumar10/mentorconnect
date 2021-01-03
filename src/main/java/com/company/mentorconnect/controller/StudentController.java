package com.company.mentorconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.company.mentorconnect.model.User;
import com.company.mentorconnect.service.StudentService;


@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/findUser/{userName}")
	public User getAllSkills(@PathVariable String userName) {
		return studentService.getUserByUserName(userName);
	}
}
