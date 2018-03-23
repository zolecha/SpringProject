package com.rotnicki.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rotnicki.model.Progress;
import com.rotnicki.model.Question;
import com.rotnicki.service.ProgressService;

@Controller
public class ProgressController {
	//@Autowired
	ProgressService progressService;

	@Autowired
	public ProgressController(ProgressService progressService) {
		this.progressService = progressService;
	}
	
	private Stack<Progress> losuj() {
		Random r = new Random();
		Stack<Progress> stack = new Stack<Progress>();
		HashSet<Progress> set = new HashSet<Progress>();
		List<Progress> list = new ArrayList<Progress>();
		//przypisanie do zmiennej list listy obiektów Question
		list = progressService.unknownJM();;
		
		//dodawanie do zbioru 15 unikatowych obiektów
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
			stack.push(e);
		}
		return stack;
	}

		Stack<Progress> stack; 
	
	
	@RequestMapping("/JavaFiszka")
	public String javaFiszka(Model model) {
		try {
			stack = losuj();
			System.out.println(stack);
			Question question = stack.pop().getQuestion();
			model.addAttribute("question", question);
			System.out.println(question);
		} catch (Exception e) {
			stack = losuj();
			
		}
		return "JavaFiszka";

	}

}
