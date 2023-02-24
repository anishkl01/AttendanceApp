package com.attendanceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendanceapp.entity.UserRegister;

public interface UserRegisterRepository extends JpaRepository<UserRegister, Long> {
	
	UserRegister findByRuser(String email);
	UserRegister findByRpassword(String pass);
}
