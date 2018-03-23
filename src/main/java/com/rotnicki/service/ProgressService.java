package com.rotnicki.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotnicki.controller.UserController;
import com.rotnicki.model.Progress;
import com.rotnicki.model.Question;
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

}
