package com.attendanceapp.dao;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendanceapp.entity.Sigin;
import com.attendanceapp.entity.SignOut;
import com.attendanceapp.entity.UserLogin;
import com.attendanceapp.entity.UserRegister;
import com.attendanceapp.repository.SignIn;
import com.attendanceapp.repository.SignOutRepo;
import com.attendanceapp.repository.UserRegisterRepository;
import com.attendanceapp.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserRegisterRepository repository2;
	
	@Autowired
	private SignIn signIn;
	
	@Autowired
	private SignOutRepo outRepo;
	
	
	@Override
	public UserLogin save(UserLogin login) {
		return repository.save(login);   
	}

	@Override
	public UserRegister save1(UserRegister register) {
		
		return repository2.save(register);
	}

	

	@Override
	public List<UserLogin> getUsers(UserLogin login) {
		return repository.findAll();
	}

	@Override
	public Sigin save2(Sigin sigin) {
		return signIn.save(sigin);
	}

	@Override
	public SignOut save3(SignOut out) {
		return outRepo.save(out);
	}

	@Override
	public UserRegister getUserByUser(String user) {
		
		return repository2.findByRuser(user);
	}

	@Override
	public boolean getUser(String username) {
			return signIn.existsByUsername(username);
	}
	

	@Override
	public boolean checkUserPresent(String username) {
		return outRepo.existsByUsername(username);
	}

	@Override
	public List<SignOut> getByUsername(String username) {
		return outRepo.findByUsername(username);
	}

	@Override
	public List<Sigin> getByUsername1(String username) {
		return signIn.findByUsername(username);
	}

	@Override
	public List<UserRegister> getRegisteredUsers(UserRegister register) {
		return repository2.findAll();
	}

	@Override
	public UserRegister getUserByPassword(String pass) {
		return repository2.findByRpassword(pass);
	}

	

}
