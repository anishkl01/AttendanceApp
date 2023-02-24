package com.attendanceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.attendanceapp.dao.UserService;
import com.attendanceapp.entity.UserLogin;
import com.attendanceapp.entity.UserRegister;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/admindata")
	public String runAdmin(Model model, UserRegister register) {
		
		model.addAttribute("admin",new UserRegister());
		model.addAttribute("username","admin");
		model.addAttribute("admin",service.getRegisteredUsers(register));
		return "userdisplay";
	}
	
}
