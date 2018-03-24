package com.rotnicki.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotnicki.controller.UserController;
import com.rotnicki.model.Progress;

import com.rotnicki.repository.ProgressRepository;

@Service
public class ProgressService {

	@Autowired
	ProgressRepository progressRepository;

	// Metoda zwracająca listę pytań danego użytkownika z progressem 0
	public List<Progress> unknown() {
		return progressRepository.findByUserAndProg(UserController.logInUser, 0);
	}

	// Metoda zwracająca listę pytań danego użytkownika z progressem 1
	public List<Progress> known() {
		return progressRepository.findByUserAndProg(UserController.logInUser, 1);
	}

	public List <Progress> unknownJM(){
		List<Progress> unknown = new ArrayList <Progress>();
		List<Progress> unknownJM = new ArrayList <Progress>();
		unknown =  unknown();
		
		for (int i = 0; i< unknown.size(); i++ ) {
			Progress p = unknown.get(i);
			if (p.getQuestion().getCategory().toUpperCase().equals("JM")) {
				unknownJM.add(p);
			}
		}
		System.out.println(unknownJM);
		return unknownJM;
	}
	public List <Progress> knownJM(){
		List<Progress> known = new ArrayList <Progress>();
		List<Progress> knownJM = new ArrayList <Progress>();
		known =  known();
		
		for (int i = 0; i< known.size(); i++ ) {
			Progress p = known.get(i);
			if (p.getQuestion().getCategory().toUpperCase().equals("JM")) {
				knownJM.add(p);
			}
		}
		return knownJM;
	}
	public Stack<Progress> losujJM() {
		Random r = new Random();
		Stack<Progress> stack = new Stack<Progress>();
		HashSet<Progress> set = new HashSet<Progress>();
		HashSet<Progress> knownSet = new HashSet<Progress>();
		List<Progress> list = new ArrayList<Progress>();
		List<Progress> knownList = new ArrayList<Progress>();
		// przypisanie do zmiennej list listy obiektów Question
		list = unknownJM();
		knownList = knownJM();

		// dodawanie do zbioru 15 unikatowych obiektów
		// 10 lub wszystkich z z progresem 0
		if (list.size() < 11) {
			for (Progress e : list) {
				set.add(e);
			}
		} else {
			while (set.size() < 11) {
				int index = r.nextInt(list.size());
				set.add(list.get(index));

			}
		}
		// 5 lub wszystkich z z progresem 1
		if (knownList.size() < 6) {
			for (Progress e : knownList) {
				knownSet.add(e);
			}
		} else {
			while (knownSet.size() < 6) {
				int index = r.nextInt(knownList.size());
				knownSet.add(knownList.get(index));
			}
		}

		for (Progress e : set) {
			list.add(e);
		}
		for (Progress e : knownSet) {
			list.add(e);
		}
		Collections.shuffle(list);
		for (Progress e : list) {
			stack.push(e);
		}
		return stack;
	}

}
