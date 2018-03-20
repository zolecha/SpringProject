package com.rotnicki.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rotnicki.model.Question;


@Repository
public interface QuestionRepository extends CrudRepository<Question, Long>{
	

	public Question findOneById(Long id);
	public Question findOneByIdAndCategory(Long id, String category);
	
	
}


