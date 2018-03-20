package com.rotnicki.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rotnicki.model.Progress;
import com.rotnicki.model.Question;
import com.rotnicki.repository.ProgressRepository;


@Controller
public class ProgressController {
	//@Autowired
	ProgressRepository progressRepostory;
	
	
//	public ProgressController() {
//
//	}

	@Autowired
	public ProgressController(ProgressRepository progressRepostory) {
		this.progressRepostory = progressRepostory;
	}
	
	private Stack<Progress> losuj() {
	Random r = new Random();
	Stack<Progress> st = new Stack<Progress>();
	HashSet<Progress> set = new HashSet<Progress>();
	List<Progress> list = new ArrayList<Progress>();
	//przypisanie do zmiennej list listy obiektów Question
	list = progressRepostory.findByUserAndProg(UserController.logInUser, 0);
	System.out.println("Po losowaniu"+UserController.logInUser);
	
	//dodawanie do zbioru 10 unikatowych obiektów
	while (set.size() < 11) {
		int index = r.nextInt(list.size());
		//Long x = (long) r.nextInt(list.size());
		set.add(list.get(index));
	}
	//
	for (Progress e : set) {
		list.add(e);
	}
	Collections.shuffle(list);
	for (Progress e : list) {
		st.push(e);
	}
	System.out.println(st);
	return st;
}

	Stack<Progress> stack;
	
	@RequestMapping("/JavaFiszka")
	public String javaFiszka(Model model) {
		try {
			stack = losuj();
			System.out.println("W metodzie JF"+UserController.logInUser);
			Question question = stack.pop().getQuestion();
			model.addAttribute("question", question);
			System.out.println(question);
		} catch (Exception e) {
			System.out.println("Nowe losowanie");
			stack = losuj();
		}

		return "JavaFiszka";

	}


}
