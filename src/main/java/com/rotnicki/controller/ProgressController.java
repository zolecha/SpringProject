package com.rotnicki.controller;

import java.util.Stack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import com.rotnicki.model.Progress;
import com.rotnicki.model.Question;
import com.rotnicki.repository.ProgressRepository;
import com.rotnicki.service.ProgressService;

@Component
@Controller
@SessionScope
public class ProgressController {

	@Autowired
	ProgressService progressService;
	@Autowired
	ProgressRepository progressRepository;
	Stack<Progress> stack;
	Progress progress;
	private String cate;
	Integer JMp, JQp;

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	@Autowired
	public ProgressController(ProgressService progressService) {
		this.progressService = progressService;
	}

	
	@RequestMapping("/JavaFiszka")
	public String javaMethodFiszka(Model model, @RequestParam String category) {
		cate = category;
		if (stack == null || stack.size()==0) {
			stack = progressService.losuj(category);
		} else {
			if (!stack.get(0).getQuestion().getCategory().equals(category)) {
				stack = progressService.losuj(category);
			}
		}
		progress = stack.pop();
		Question question = progress.getQuestion();
		model.addAttribute("question", question);

		return "JavaFiszka";

	}

	@RequestMapping("/known")
	public String knownProgress(Model model) {
		progress.setProg(1);
		progressRepository.save(progress);

		return "redirect:/JavaFiszka?category=" + cate;

	}

	@RequestMapping("/unknown")
	public String unknownProgress(Model model) {
		progress.setProg(0);
		progressRepository.save(progress);
		return "redirect:/JavaFiszka?category=" + cate;

	}

	@RequestMapping("/JavaProgress")
	public String progress(Model model) {
		JMp = progressService.progressCategory("JM");
		JQp = progressService.progressCategory("JQ");
		model.addAttribute("prog1", JMp.toString());
		model.addAttribute("prog2", JQp.toString());
		return "JavaProgress";

	}

}
