package com.rotnicki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rotnicki.model.User;



@Controller
public class HomeController {

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/Java")
	public String javaMenu() {
		return "JavaMenu";
	}
	@RequestMapping("/Python")
	public String pythonMenu() {
		return "notnow";
	}
	@RequestMapping("/JavaScript")
	public String jsMenu() {
		return "notnow";
	}
	@RequestMapping("/MySQL")
	public String mysqlMenu() {
		return "notnow";
	}

	@RequestMapping("/CreateAccount")
	public String createAccount(Model model) {
		model.addAttribute("user", new User());
		return "CreateAccount";
	}

}
