package com.rotnicki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rotnicki.model.Progress;
import com.rotnicki.model.Question;
import com.rotnicki.model.User;

@Repository
public interface ProgressRepository extends CrudRepository<Progress, Long> {
	public List <Progress> findByUserAndProg(User u, int p);
	
//	@Query("select round((select count(*) from Progress as p join Question q on(p.id_q=q.id_q) where p.id_u=1 and p.prog=1 and q.category='JM')/\r\n" + 
//			"(select count(*) from Progress as p join Question q on(p.id_q=q.id_q) where p.id_u=1 and q.category='JM'})*100)")
//	public Integer progressFromCategory();
	
}
