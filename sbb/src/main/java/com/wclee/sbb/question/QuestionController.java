package com.wclee.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QuestionController {
	
//	private final QuestionRepository questionRepository; 
	private final QuestionService questionService; 
	
	@GetMapping("/question/list")
//	@ResponseBody
	public String list(Model model) {
//		List<Question> questionList = this.questionRepository.findAll(); 
		List<Question> questionList = this.questionService.getList(); 
		model.addAttribute("questionList", questionList); 
		return "question_list"; 
	}
	
	@GetMapping(value = "/question/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		return "question_detail"; 
	}
}