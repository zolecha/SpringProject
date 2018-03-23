package com.rotnicki.model;

import javax.persistence.CascadeType;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Progress {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_p;
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_u")
	@Autowired
    private User user;
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_q")
	@Autowired
	private Question question;
    private Integer prog;
    
    
	public Progress() {
		
	}


	public Long getId_p() {
		return id_p;
	}


	public void setId_p(Long id_p) {
		this.id_p = id_p;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	public Integer getProg() {
		return prog;
	}


	public void setProg(Integer prog) {
		this.prog = prog;
	}


	@Override
	public String toString() {
		return "Progress [id_p=" + id_p + ", user=" + user + ", question=" + question + ", prog=" + prog + "]";
	}
	

	
}
