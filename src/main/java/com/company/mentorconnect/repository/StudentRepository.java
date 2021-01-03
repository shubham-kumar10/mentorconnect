package com.company.mentorconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.mentorconnect.model.User;

public interface StudentRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

}
