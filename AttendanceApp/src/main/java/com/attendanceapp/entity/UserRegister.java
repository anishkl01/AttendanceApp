 package com.attendanceapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserRegister {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String ruser;
	private String rpassword;
	private String email;
	private String register_phonenumber;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getRuser() {
		return ruser;
	}

	public void setRuser(String ruser) {
		this.ruser = ruser;
	}

	
	public String getRpassword() {
		return rpassword;
	}

	public void setRpassword(String rpassword) {
		this.rpassword = rpassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegister_phonenumber() {
		return register_phonenumber;
	}

	public void setRegister_phonenumber(String register_phonenumber) {
		this.register_phonenumber = register_phonenumber;
	}

	public UserRegister(long id, String ruser, String rpassword, String email,
			String register_phonenumber) {
		super();
		this.id = id;
		this.ruser = ruser;
		this.rpassword = rpassword;
		this.email = email;
		this.register_phonenumber = register_phonenumber;
	}
	
	public UserRegister() {

	}
}
