package com.rotnicki.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rotnicki.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public User getByLoginAndPass(String login, String pass);
	

}
