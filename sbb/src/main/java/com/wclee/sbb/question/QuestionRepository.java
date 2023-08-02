package com.wclee.sbb.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer>{

	Question findBySubject(String string);

	Question findBySubjectAndContent(String string, String string2);

	List<Question> findBySubjectLike(String string);
	
}
