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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotnicki.model.Progress;
import com.rotnicki.model.Question;
import com.rotnicki.model.User;
import com.rotnicki.repository.ProgressRepository;
import com.rotnicki.service.ProgressService;

@Component
@Controller
public class ProgressController {

	ProgressService progressService;
	@Autowired
	ProgressRepository progressRepository;
	Stack<Progress> stack;
	Progress progress;
	

	@Autowired
	public ProgressController(ProgressService progressService) {
		this.progressService = progressService;
	}

	
	//Sprawdzić czy można tak zrobić zeby po wybraniu buttona z kategorią przypisywać cat do zmiennej i wywoływać daną metodę z konkretną kategorią
	private String cate;
	
	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}


	@RequestMapping("/JavaFiszka")
	public String javaMethodFiszka(Model model, @RequestParam String category) {
		try {
			cate = category;
			stack = progressService.losuj(category);
			progress = stack.pop();
			Question question = progress.getQuestion();
			model.addAttribute("question", question);
		} catch (Exception e) {
			stack = progressService.losuj(category);

		}
		return "JavaFiszka";

	}
	

	@RequestMapping("/known")
	public String knownProgress(Model model) {
		progress.setProg(1);
		progressRepository.save(progress);
		
		return "redirect:/JavaFiszka?category="+cate;

	}

	@RequestMapping("/unknown")
	public String unknownProgress(Model model) {
		progress.setProg(0);
		progressRepository.save(progress);
		return "redirect:/JavaFiszka?category="+cate;

	}
	
 Integer JMp, JQp;

	@RequestMapping("/JavaProgress")
	public String progress(Model model) {
		JMp = progressService.progressCategory("JM");
		JQp = progressService.progressCategory("JQ");
		model.addAttribute("prog1",JMp.toString());
		model.addAttribute("prog2",JQp.toString());
		System.out.println("JM progress "+JMp+" JQ progress "+JQp);
		return "JavaProgress";

	}

}
