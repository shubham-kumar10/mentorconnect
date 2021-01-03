package com.company.mentorconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.mentorconnect.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUserName(String username);

}
