package com.wclee.sbb;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository; 

	@Test
	void contextLoads() {
		Question q1 = new Question(); 
		q1.setSubject("sbb가 뭐지?");
		q1.setContent("sbb에 대해 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		Question q2 = new Question(); 
		q2.setSubject("스프링부트 모델 질문");
		q2.setContent("id는 자동부여?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2); 
	}

}
