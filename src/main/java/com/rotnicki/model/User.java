package com.rotnicki.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity // This tells Hibernate to make a table out of this class
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id_u;

	private String login;

	private String pass;

	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	@Transient
	private Progress progress;

	public User() {
	}

	public Integer getId_u() {
		return id_u;
	}

	public void setId_u(Integer id_u) {
		this.id_u = id_u;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	@Override
	public String toString() {
		return "User [id_u=" + id_u + ", login=" + login + ", pass=" + pass + ", progress=" + progress + "]";
	}
	
	
	

}