package com.attendanceapp.dao;


import java.time.Duration;
import java.util.List;

import com.attendanceapp.entity.Sigin;
import com.attendanceapp.entity.SignOut;
import com.attendanceapp.entity.UserLogin;
import com.attendanceapp.entity.UserRegister;

public interface UserService{
	
	public  UserLogin save(UserLogin login);
	public UserRegister save1(UserRegister register);
	public List<UserLogin> getUsers(UserLogin login);
	
	public Sigin save2(Sigin sigin);
	
	public SignOut save3(SignOut out);
	
	public UserRegister getUserByUser(String user);
	public UserRegister getUserByPassword(String pass);
	
	public boolean getUser(String username);
	
	public List<Sigin> getByUsername1(String username);
	public List<SignOut> getByUsername(String username);
	
	public boolean checkUserPresent(String username);
	
	public List<UserRegister> getRegisteredUsers(UserRegister register);
	
	
	
}
