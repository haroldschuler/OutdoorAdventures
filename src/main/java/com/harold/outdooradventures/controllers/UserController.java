package com.harold.outdooradventures.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.harold.outdooradventures.models.LoginUser;
import com.harold.outdooradventures.models.User;
import com.harold.outdooradventures.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String loginPage(
			@ModelAttribute("newUser") User newUser,
			@ModelAttribute("newLogin") LoginUser newLogin,
			HttpSession session) {
		session.setAttribute("userId", null);
		return "login.jsp";
	}
	
	@GetMapping("/register")
	public String registerPage(
			@ModelAttribute("newUser") User newUser,
			@ModelAttribute("newLogin") LoginUser newLogin,
			HttpSession session) {
		session.setAttribute("userId", null);
		return "register.jsp";
	}
	
	@PostMapping("/register")
	public String register(
			HttpSession session,
			Model model,
			@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result) {
		if(userService.findUserByEmail(newUser.getEmail()) != null) {
			result.rejectValue("email", "email_match", "The email address already exists");
		}
		
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Match", "Passwords must match");
		}
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "register.jsp";
		}
		userService.register(newUser);
		session.setAttribute("userId", newUser.getId());
		if(session.getAttribute("currentPage") != null) {
			return "redirect:" + session.getAttribute("currentPage");
		}
		else {			
			return "redirect:/";
		}
	}
	
	@PostMapping("/login")
	public String login(
			HttpSession session,
			Model model,
			@Valid @ModelAttribute("newLogin") LoginUser newLogin,
			BindingResult result) {
		User foundUser = userService.login(newLogin, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "login.jsp";
		}
		session.setAttribute("userId", foundUser.getId());
		if(session.getAttribute("currentPage") != null) {
			return "redirect:" + session.getAttribute("currentPage");
		}
		else {			
			return "redirect:/";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("userId", null);
		if(session.getAttribute("currentPage") != null) {
			return "redirect:" + session.getAttribute("currentPage");
		}
		else {			
			return "redirect:/";
		}
	}
}
