package com.rotnicki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rotnicki.model.User;

@Controller

public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "LogIn";
	}

	@RequestMapping("/LangChoice")
	public String langChoice(Model model) {
		model.addAttribute("user", UserController.logInUser);
		return "LangChoice";
	}

	@RequestMapping("/Java")
	public String javaMenu() {
		return "JavaMenu";
	}

	@RequestMapping("/CreateAccount")
	public String createAccount(Model model) {
		model.addAttribute("user", new User());
		return "CreateAccount";
	}

}
