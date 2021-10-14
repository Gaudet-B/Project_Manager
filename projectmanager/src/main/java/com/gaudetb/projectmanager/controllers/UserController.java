package com.gaudetb.projectmanager.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gaudetb.projectmanager.models.LoginUser;
import com.gaudetb.projectmanager.models.User;
import com.gaudetb.projectmanager.services.UserService;


@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	// ============> DISPLAY ROUTES <============

	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		
		if (session.getAttribute("uuid") != null) return "redirect:/dashboard";
		
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		
		return "login.jsp";
	}
	
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("uuid");
		return "redirect:/";
	}
	
	// ============> ACTION ROUTES <============
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		
		userService.registerUser(newUser, result);
		
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "login.jsp";
		}
		
		session.setAttribute("uuid", newUser.getId());
		
		return "redirect:/dashboard";
	}
	
	@PostMapping("login")
	public String loginUser(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		
		User user = userService.loginUser(newLogin, result);
		
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "login.jsp";
		}
		
		session.setAttribute("uuid", user.getId());
		
		return "redirect:/dashboard";
	}

}