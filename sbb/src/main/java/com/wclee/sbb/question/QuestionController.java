package com.wclee.sbb.question;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wclee.sbb.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {
	
//	private final QuestionRepository questionRepository; 
	private final QuestionService questionService; 
	
	/*
	@GetMapping("/list")
//	@ResponseBody
	public String list(Model model) {
//		List<Question> questionList = this.questionRepository.findAll(); 
		List<Question> questionList = this.questionService.getList(); 
		model.addAttribute("questionList", questionList); 
		return "question_list"; 
	}*/
	
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		Page<Question> paging = this.questionService.getList(page); 
		model.addAttribute("paging", paging); 
		return "question_list"; 
	}
	
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question q = questionService.getQuestion(id); 
		model.addAttribute("question", q);
		return "question_detail"; 
	}
	
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_create";
	}
	
//	@PostMapping("/create")
//	public String questionCreate(@RequestParam String subject, @RequestParam String content) {
//		// todo 질문을 저장한다 
//		this.questionService.create(subject, content); 
//		return "redirect:/question/list"; 
//	}
//	
	
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "question_create";
		}
		this.questionService.create(questionForm.getSubject(), questionForm.getContent()); 
		return "redirect:/question/list"; 
	}	

}