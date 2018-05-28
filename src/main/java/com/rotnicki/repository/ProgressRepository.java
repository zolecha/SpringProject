package com.rotnicki.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.SessionScope;

import com.rotnicki.model.Progress;
import com.rotnicki.model.User;




@Repository
@SessionScope
public interface ProgressRepository extends CrudRepository<Progress, Long> {
	public List <Progress> findByUserAndProg(User u, int p);


	
}
