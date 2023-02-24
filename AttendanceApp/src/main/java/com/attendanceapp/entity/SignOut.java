package com.attendanceapp.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SignOut {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String out_value;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date out_date;

	private String username;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOut_value() {
		return out_value;
	}

	public void setOut_value(String out_value) {
		this.out_value = out_value;
	}

	public Date getOut_date() {
		return out_date;
	}

	public void setOut_date(Date out_date) {
		this.out_date = out_date;
	}

	public SignOut(String out_value, Date out_date, String username) {
		super();
		this.out_value = out_value;
		this.out_date = out_date;
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public SignOut() {
		
	}

	@Override
	public String toString() {
		return "SignOut [id=" + id + ", out_value=" + out_value + ", out_date=" + out_date + "]";
	}
}
