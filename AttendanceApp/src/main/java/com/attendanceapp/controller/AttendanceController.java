package com.attendanceapp.controller;

import java.util.Date;
import java.util.Optional;

import org.hibernate.type.TrueFalseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.attendanceapp.dao.UserService;
import com.attendanceapp.entity.Sigin;
import com.attendanceapp.entity.SignOut;
import com.attendanceapp.entity.UserLogin;
import com.attendanceapp.entity.UserRegister;
import com.attendanceapp.repository.SignIn;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AttendanceController {

	@Autowired
	private UserService service;

	private boolean buttonproperty = true;

	// for login user

	@GetMapping("/login")
	public String loginPage(Model model, UserLogin login) {
		model.addAttribute("userlogin", new UserLogin());
		model.addAttribute("usererror","username is invalid");
		model.addAttribute("passerror","password is invalid");
		return "Login";
	}

	@PostMapping("/login/success")
	public String loginPage(@ModelAttribute("userlogin") UserLogin userLogin, Model model, HttpSession session,
			HttpServletRequest request) {
		request.getSession().setAttribute("userid", userLogin.getUsername());
		UserRegister register = service.getUserByUser(userLogin.getUsername());
		UserRegister password = service.getUserByPassword(userLogin.getPassword());

		String user = userLogin.getUsername();
		String pass = userLogin.getPassword();
		System.out.println(user);
		session.setAttribute("user", user);

		if (user.equals("admin") && pass.equals("admin")) {
			return "redirect:/admin/admindata";
		}
		if (register != null && password != null) {
			service.save(userLogin);
			return "redirect:/display?success";
		}
		if(register==null) {
			return "redirect:/login?error";
		} else if (password==null) {
			return "redirect:/login?errorpass";
		} else { 
			
			return "redirect:/login?errorboth";
		}
		

	}

	

	// for register user
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("userregister", new UserRegister());
		model.addAttribute("usernameexist", "username already exist");
		return "Register";
	}

	@PostMapping("/register/success")
	public String register(@ModelAttribute("userregister") UserRegister register, Model model) {
			UserRegister username = service.getUserByUser(register.getRuser());
			
			if(username !=null) {
				return "redirect:/register?errorusername";
			}
			
		if (!register.getRuser().isBlank() && !register.getEmail().isBlank() && !register.getRpassword().isBlank()
				&& !register.getRegister_phonenumber().isBlank()) {
			service.save1(register);
			register.setRuser("");
			register.setRpassword("");
			register.setEmail("");
			register.setRegister_phonenumber("");
			register.setRuser("");
			return "redirect:/register?success";
		} else {

			return "redirect:/register?error";
		}

	}

	@GetMapping("/display")
	public String display(HttpServletRequest request, Model model, @ModelAttribute("username") Sigin sigin, SignOut out,
			UserLogin login, HttpSession session) {

		model.addAttribute("button", buttonproperty);
		Date date_time = new Date();
		String username = (String) session.getAttribute("user");
		System.out.println(username);
		model.addAttribute("date", date_time);
		model.addAttribute("signin", new Sigin("signIn", new Date(), username));
		model.addAttribute("signout", new SignOut("signOut", new Date(), username));
		model.addAttribute("userlogin", new UserLogin());
		model.addAttribute("username",username);
		return "Display";
//		request.getSession().setAttribute("action", "signout");
	}

	@PostMapping("/display/post")
	public String diplaypost(HttpSession session) {
		session.setAttribute("loggedIn", true);
		return "Display";
	}

	@PostMapping("/signin")
	public String signIn(@ModelAttribute("signin") Sigin sigin, HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("userid");
		if (username == null) {
			return "redirect:/login";
		}
		buttonproperty = false;
		service.save2(sigin);
		return "redirect:/display";
//		request.getSession().setAttribute("action", "signin");
	}

	@PostMapping("/signout")
	public String signOut(@ModelAttribute("signout") SignOut out, HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("userid");
		if (username == null) {
			return "redirect:/login";
		}
		buttonproperty = true;
		service.save3(out);
		return "redirect:/display";
		// request.getSession().setAttribute("action", "signout");
	}

	@GetMapping("/viewreport")
	public String viewReport(HttpSession session, Model model, Sigin sigin, SignOut out) {
		String username = (String) session.getAttribute("user");
		System.out.println(username);
		System.out.println(service.getUser(username));
		if (service.getUser(username) || service.checkUserPresent(username)) {
			model.addAttribute("signin", service.getByUsername1(username));
			model.addAttribute("signout", service.getByUsername(username));
			return "viewreport";
		}
		return "viewreport";
	}

	@PostMapping("/viewreport")
	public String postReport(Model model, Sigin sigin, SignOut out, @ModelAttribute("uservalue") String username,
			HttpServletRequest request) {
		String username1 = (String) request.getSession().getAttribute("userid");
		if (username1 == null) {
			return "redirect:/login";
		}
		String user = (String) model.getAttribute(username);
		System.out.println(user);
		model.addAttribute("signin", sigin);
		model.addAttribute("signout", out);
		return "redirect:/viewreport";
	}

	@GetMapping("/userreport/{username}")
	public String AttendanceReport(@PathVariable("username") String username, Model model,HttpServletRequest request) {
		String username1 = (String) request.getSession().getAttribute("userid");
		if (username1 == null) {
			return "redirect:/login";
		}
		model.addAttribute("username", service.getByUsername(username));
		model.addAttribute("username1", service.getByUsername1(username));
		return "adminreport";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login";
	}

}
