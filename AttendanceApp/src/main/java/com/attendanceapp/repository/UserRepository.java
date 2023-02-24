package com.attendanceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendanceapp.entity.UserLogin;

public interface UserRepository extends JpaRepository<UserLogin, Long>{

}
