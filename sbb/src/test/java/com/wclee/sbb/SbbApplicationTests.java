package com.wclee.sbb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wclee.sbb.answer.Answer;
import com.wclee.sbb.answer.AnswerRepository;
import com.wclee.sbb.question.Question;
import com.wclee.sbb.question.QuestionRepository;
import com.wclee.sbb.question.QuestionService;

import jakarta.transaction.Transactional;

@SpringBootTest
class SbbApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository; 
	
	@Autowired
	private AnswerRepository answerRepository; 
	
	@Autowired
	private QuestionService questionService; 

	@Test
	void contextLoads() {
//		Question q1 = new Question(); 
//		q1.setSubject("sbb가 뭐지?");
//		q1.setContent("sbb에 대해 알고 싶습니다.");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);
//		
//		Question q2 = new Question(); 
//		q2.setSubject("스프링부트 모델 질문");
//		q2.setContent("id는 자동부여?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2); 
	}
	
	@Test
	void testJpa() {
//		List<Question> all = this.questionRepository.findAll(); 
//		assertEquals(6, all.size());
//		
//		Question q = all.get(0); 
//		assertEquals("sbb가 뭐지?", q.getSubject());
		
		Question q = this.questionRepository.findBySubject("sbb가 뭐지?"); 
		assertEquals(2, q.getId());
		
		Question q2 = this.questionRepository.findBySubjectAndContent("sbb가 뭐지?", "sbb에 대해 알고 싶습니다."); 
		assertEquals(2, q2.getId());
		
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q3 = qList.get(0); 
		assertEquals("sbb가 뭐지?", q3.getSubject());
	}
	
	@Test 
	void testJpaSave() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		
		Question q = oq.get();
		q.setSubject("sbb가 뭐지?");
		this.questionRepository.save(q); 
	}
	
	@Test
	void testJpaDelete() {
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent()); 
		
		Question q = oq.get(); 
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
	}
	
	@Test
	void testAnswer() {
		Optional<Question> oq = this.questionRepository.findById(3); 
		assertTrue(oq.isPresent());
		Question q = oq.get(); 
		
		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a); 
	}
	
	@Test
	void testAnswerToQuestion() { // 답변에 연결된 질문 찾기 
		Optional<Answer> as = this.answerRepository.findById(1); 
		assertTrue(as.isPresent());
		Answer a = as.get(); 
		assertThat(a.getQuestion().getId()).isEqualTo(3); 
//		assertEquals(3, a.getQuestion().getId());
	}
	
	@Transactional // 메서드가 종료될때까지 db 세션 유지 
	@Test
	void testQuestionToAnswer() {
		Optional<Question> oq = this.questionRepository.findById(3); 
		assertTrue(oq.isPresent());
		Question q = oq.get(); 
		// 여기서 세션이 종료되어 could not initialize proxy - no Session 발생 
		List<Answer> answerList = q.getAnswerList(); // 필요한 시점에 데이터를 가져오는 방식을 Lazy 방식
		
		assertThat(answerList.size()).isEqualTo(3); 
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}
	
	@Test 
	void testDataSave() {
		 for (int i = 1; i < 300; i++) {
			 String subj = String.format("게시판 테스트 데이터 입니다.:[%03d]", i);
			 String content = "테스트 게시물 입니다."; 
			 this.questionService.create(subj, content, null);
		 }
	}
	
	
}
