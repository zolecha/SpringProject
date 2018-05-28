package com.rotnicki.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import com.rotnicki.model.Progress;
import com.rotnicki.model.User;
import com.rotnicki.repository.ProgressRepository;
import com.rotnicki.repository.UserRepository;

@Service
public class ProgressService {

	@Autowired
	ProgressRepository progressRepository;
	@Autowired
	UserRepository userRepository;

	private User returnUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		return userRepository.findByLogin(login);
	}

	// metoda pobiera listę obiektów typu progress danego użytkownika z progessem 0
	// następnie tworzony jest z niej strumień, który wybiera tylko te które mają
	// określona kategorię
	// kolejno strumień przekształcany jest na listę
	public List<Progress> unknownCat(String cat) {
		return progressRepository.findByUserAndProg(returnUser(), 0).stream()
				.filter(f -> f.getQuestion().getCategory().equalsIgnoreCase(cat)).collect(Collectors.toList());
	}

	// analogicznie jak powyżej tylko dla pytań, które mają już progess 1
	public List<Progress> knownCat(String cat) {
		return progressRepository.findByUserAndProg(returnUser(), 1).stream()
				.filter(f -> f.getQuestion().getCategory().equalsIgnoreCase(cat)).collect(Collectors.toList());
	}

	public Stack<Progress> losuj(String cat) {
		Stack<Progress> stack = new Stack<Progress>();
		List<Progress> know = knownCat(cat);
		List<Progress> unknow = unknownCat(cat);
		Collections.shuffle(know);
		Collections.shuffle(unknow);

		if (unknow.size() >= 15) {
			stack.addAll(IntStream.range(1, 16).mapToObj(i -> unknow.get(i)).collect(Collectors.toList()));
		} else {
			stack.addAll(
					IntStream.range(1, unknow.size()).mapToObj(i -> unknow.get(i)).collect(Collectors.toList()));
		}

		if (know.size() >= 5) {
			stack.addAll(IntStream.range(1, 6).mapToObj(i -> know.get(i)).collect(Collectors.toList()));
		} else {
			stack.addAll(IntStream.range(1, know.size()).mapToObj(i -> know.get(i)).collect(Collectors.toList()));
		}
		return stack;
	}
	
//	public Stack<Progress> losuj2(String cat) {
//		Random r = new Random();
//		Stack<Progress> stack = new Stack<Progress>();
//		HashSet<Progress> set = new HashSet<Progress>();
//		HashSet<Progress> knownSet = new HashSet<Progress>();
//		List<Progress> list = new ArrayList<Progress>();
//		List<Progress> knownList = new ArrayList<Progress>();
//		// przypisanie do zmiennej list listy obiektów Question
//		list = unknownCat(cat);
//		knownList = knownCat(cat);
//
//		// dodawanie do zbioru 15 unikatowych obiektów
//		// 10 lub wszystkich z z progresem 0
//		if (list.size() < 11) {
//			for (Progress e : list) {
//				set.add(e);
//			}
//		} else {
//			while (set.size() < 11) {
//				int index = r.nextInt(list.size());
//				set.add(list.get(index));
//
//			}
//		}
//		// 5 lub wszystkich z z progresem 1
//		if (knownList.size() < 6) {
//			for (Progress e : knownList) {
//				knownSet.add(e);
//			}
//		} else {
//			while (knownSet.size() < 6) {
//				int index = r.nextInt(knownList.size());
//				knownSet.add(knownList.get(index));
//			}
//		}
//
//		for (Progress e : set) {
//			list.add(e);
//		}
//		for (Progress e : knownSet) {
//			list.add(e);
//		}
//		Collections.shuffle(list);
//		for (Progress e : list) {
//			stack.push(e);
//		}
//		return stack;
//	}

	// Progress z danej kategorii
	public int progressCategory(String cat) {
		List<Progress> know = knownCat(cat);
		List<Progress> unknow = unknownCat(cat);
		float a = know.size();
		float b = unknow.size();
		int res = Math.round((a / (a + b)) * 100);
		return res;

	}

}
