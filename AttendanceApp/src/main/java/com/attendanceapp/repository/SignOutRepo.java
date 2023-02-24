package com.attendanceapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendanceapp.entity.Sigin;
import com.attendanceapp.entity.SignOut;

public interface SignOutRepo extends JpaRepository<SignOut, Long> {
	
	boolean existsByUsername(String username);
	
	List<SignOut> findByUsername(String username);
	
}
