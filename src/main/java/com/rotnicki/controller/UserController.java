package com.rotnicki.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rotnicki.model.User;
import com.rotnicki.repository.UserRepository;


@Controller
public class UserController {
	
	@Autowired
    UserRepository userRepository;
       
    @Autowired
    User user;
	
	@RequestMapping("/add")
    public String addUser(@ModelAttribute User user){
		user = userRepository.save(user);
        return "redirect:/login?added";
	}
	
	@RequestMapping("/")
	public String langChoice(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
	    user = userRepository.findByLogin(login);
	    model.addAttribute("user", user);
		return "LangChoice";
	}
	
	@RequestMapping("/logout")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/login";

	}
	
}
