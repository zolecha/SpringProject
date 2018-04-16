package com.rotnicki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rotnicki.model.User;
import com.rotnicki.repository.UserRepository;

@Controller
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserController {
		
    UserRepository userRepository;
    
    @Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    
    public static User logInUser;
	//public static String login;
	
	@RequestMapping("/add")
    public String addUser(@ModelAttribute User user){
		logInUser = userRepository.save(user);
        return "LangChoice";
	}
	
	@RequestMapping("/UserLogIn")
	    public String userLogIn(@ModelAttribute User user, @RequestParam String login, @RequestParam String pass){
		logInUser = userRepository.getByLoginAndPass(login, pass);
		//login = logInUser.getLogin();
        return "LangChoice";
	}
	
	@RequestMapping("/logout")
	public String logOut() {
		return "LogIn";

	}
	
}