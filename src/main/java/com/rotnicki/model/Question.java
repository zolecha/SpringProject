package com.rotnicki.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
public class Question {
	
    @Id()
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_q")
    private Long id;

    private String question;

    private String answer;
    
    private String category;
    
    @OneToMany(mappedBy="question", cascade = CascadeType.ALL)
	@Transient
	@Autowired
	private Progress progress;



	public Question() {
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getQuestion() {
		return question;
	}



	public void setQuestion(String question) {
		this.question = question;
	}



	public String getAnswer() {
		return answer;
	}



	public void setAnswer(String answer) {
		this.answer = answer;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public Progress getProgress() {
		return progress;
	}



	public void setProgress(Progress progress) {
		this.progress = progress;
	}



	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", answer=" + answer + ", category=" + category
				+ ", progress=" + progress + "]";
	}
	
	

	
	

}