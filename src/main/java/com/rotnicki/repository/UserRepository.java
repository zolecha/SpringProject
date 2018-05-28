package com.rotnicki.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.SessionScope;

import com.rotnicki.model.User;




@Repository
@SessionScope
public interface UserRepository extends CrudRepository<User, Long> {
	public User getByLoginAndPass(String login, String pass);
	public User findByLogin(String login);
	

}
