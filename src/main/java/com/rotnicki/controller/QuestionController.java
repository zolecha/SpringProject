package com.rotnicki.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rotnicki.model.Question;
import com.rotnicki.repository.QuestionRepository;

@Component
@Controller
public class QuestionController {

	QuestionRepository questionRepository;

	@Autowired
	public QuestionController(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	private Stack<Long> losuj() {
		Random r = new Random();
		Stack<Long> st = new Stack<Long>();
		HashSet<Long> l = new HashSet<Long>();
		List<Long> list = new ArrayList<Long>();
		while (l.size() < 10) {
			Long x = (long) r.nextInt(14) + 1;
			l.add(x);
		}
		for (long e : l) {
			list.add(e);
		}
		Collections.shuffle(list);
		for (long e : list) {
			st.push(e);
		}
		System.out.println(st);
		return st;
	}
	
	/*private Stack<Question> losuj() {
	Random r = new Random();
	Stack<Question> st = new Stack<Question>();
	HashSet<Question> set = new HashSet<Question>();
	List<Question> list = new ArrayList<Question>();
	//przypisanie do zmiennej list listy obiektów Question
	list = questionRepository.findAllByCategory("JM");
	
	//dodawanie do zbioru 10 unikatowych obiektów
	while (set.size() < 11) {
		int index = r.nextInt(list.size());
		//Long x = (long) r.nextInt(list.size());
		set.add(list.get(index));
	}
	//
	for (Question e : set) {
		list.add(e);
	}
	Collections.shuffle(list);
	for (Question e : list) {
		st.push(e);
	}
	System.out.println(st);
	return st;
}*/

	Stack<Long> stack = losuj();
	// ArrayList <Long> list = new ArrayList<Long>();
	
/*	@RequestMapping("/JavaFiszka")
	public String javaFiszka(Model model) {
		try {
			Question question = stack.pop();
			model.addAttribute("question", question);
			System.out.println(question);
		} catch (Exception e) {
			stack = losuj();
		}

		return "JavaFiszka";

	}*/
	

//	@RequestMapping("/JavaFiszka")
//	public String javaFiszka(Model model) {
//		try {
//			Question question = questionRepository.findOneByIdAndCategory(stack.pop(), "JM");
//			model.addAttribute("question", question);
//			System.out.println(question);
//		} catch (Exception e) {
//			stack = losuj();
//		}
//
//		return "JavaFiszka";
//
//	}

}
