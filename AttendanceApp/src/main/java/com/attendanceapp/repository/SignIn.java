package com.attendanceapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendanceapp.entity.Sigin;
import com.attendanceapp.entity.SignOut;
import com.attendanceapp.entity.UserLogin;

public interface SignIn extends JpaRepository<Sigin, Long> {

	boolean existsByUsername(String username);
	List<Sigin> findByUsername(String username);
}
