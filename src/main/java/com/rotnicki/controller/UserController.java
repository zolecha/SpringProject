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
//@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserController {
		
    UserRepository userRepository;
    
    @Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    
    public static User logInUser;
	public static String login;
	
	@RequestMapping("/add")
    public String addUser(@ModelAttribute User user){
		logInUser = userRepository.save(user);
        return "LangChoice";
	}
	
	@RequestMapping("/UserLogIn")
	    public String userLogIn(@ModelAttribute User user, @RequestParam String login, @RequestParam String pass){
		logInUser = userRepository.getByLoginAndPass(login, pass);
		login = logInUser.getLogin();
        return "LangChoice";
	}
	

	
	
/*
	//Logowanie
	@RequestMapping("/login") 
	public String logIn(Model model) {	
		User user = new User("test","test");
		model.addAttribute("user", user);
		user = userRepository.getByLoginAndPass(user.getLogin(), user.getPass());
		System.out.println(user.getId_u());
		return "LangChoice";
	
	}
	
	*/
	
	/*   @RequestMapping("/save")
	    public String process(){
	        repository.save(new User("test1","test1"));
	        repository.save(new User("test2","test2"));
	        repository.save(new User("test3","test3"));
	        repository.save(new User("test4","test4"));
	        repository.save(new User("test5","test5"));

	        return "Dodano użytkowników do bazy";
	    }
	   
	   @PostMapping ("/add")
	   public String add(@ModelAttribute User user, Model model){
       model.addAttribute(
      		"user",
	        		model.addAttribute("user",user)
	        		);
	        return "LangChoice";

	   }
	   
	  @RequestMapping("/add")
	    public String addUser(Model model){
	        model.addAttribute(
	        		"user",
	        		repository.save(new User("login","haslo"))
	        		);
	        return "redirect:LangChoice";

  } */

}