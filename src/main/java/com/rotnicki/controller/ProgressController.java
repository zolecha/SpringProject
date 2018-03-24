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
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/JavaFiszka")
	public String javaFiszka(Model model) {
		try {
			stack = progressService.losujJM();
			progress = stack.pop();
			Question question = progress.getQuestion();
			model.addAttribute("question", question);
		} catch (Exception e) {
			stack = progressService.losujJM();

		}
		return "JavaFiszka";

	}

	@RequestMapping("/known")
	public String knownProgress() {
		progress.setProg(1);
		progressRepository.save(progress);
		return "redirect:/JavaFiszka";

	}

	@RequestMapping("/unknown")
	public String unknownProgress(Model model) {
		progress.setProg(0);
		progressRepository.save(progress);
		return "redirect:/JavaFiszka";

	}

}
