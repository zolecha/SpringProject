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
		model.addAttribute("login", UserController.logInUser.getLogin());	
		return "LangChoice";
	}
	
	@RequestMapping("/Java") 
	public String javaMenu() {
		return "JavaMenu";
	}
	
	@RequestMapping("/MySQL") 
	public String mySQLMenu() {
		return "MySQLMenu";
	}
	@RequestMapping("/Python") 
	public String pythonMenu() {
		return "PythonMenu";
	}
	
	@RequestMapping("/JavaScript") 
	public String jsMenu() {
		return "JSMenu";
	}
	
	@RequestMapping("/JavaProgress") 
	public String javaProgress() {
		return "JavaProgress";
	}
	
	@RequestMapping("/PythonProgress") 
	public String PythonProgress() {
		return "PythonProgress";
	}
	
	@RequestMapping("/JavaScriptProgress") 
	public String JavaScriptProgress() {
		return "JavaScriptProgress";
	}
	
	@RequestMapping("/MySQLProgress") 
	public String mySQLProgress() {
		return "MySQLProgress";
	}
	
	
	@RequestMapping("/CreateAccount") 
	public String createAccount(Model model) {
		model.addAttribute("user", new User());		
		return "CreateAccount";
	}
	
//	@RequestMapping("/JavaFiszka") 
//	public String javaFiszka() {
//		return "JavaFiszka";
//	}
	
	@RequestMapping("/PythonFiszka") 
	public String pythonFiszka() {
		return "PythonFiszka";
	}
	
	@RequestMapping("/MySQLFiszka") 
	public String mySQLFiszka() {
		return "MySQLFiszka";
	}
	
	@RequestMapping("/JavaScriptFiszka") 
	public String javaScriptFiszka() {
		return "JavaScriptFiszka";
	}

}
