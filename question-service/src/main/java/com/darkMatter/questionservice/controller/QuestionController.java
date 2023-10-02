package com.darkMatter.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darkMatter.questionservice.dto.QuestionDto;
import com.darkMatter.questionservice.model.Question;
import com.darkMatter.questionservice.model.ResponseAnswer;
import com.darkMatter.questionservice.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {

		return questionService.getAllQuestions();

	}

	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {

		return questionService.getQuestionsByCategory(category);

	}

//	@PostMapping("add")
//	public ResponseEntity<String> addQuestions(@RequestBody List<Question> question) {
//		return questionService.addQuestions(question);
//
//	}
//
//	@PutMapping("update")
//	public ResponseEntity<String> updateQuestions(@RequestBody List<Question> question) {
//		return questionService.updateQuestions(question);
//	}
//
//	@DeleteMapping("{id}")
//	public ResponseEntity<String> deleteQuestionById(@PathVariable int id) {
//		return questionService.deleteQuestionById(id);
//	}
	
	@GetMapping("ids")
	public ResponseEntity<List<Integer>> getQuestionIdsForCategory(@RequestParam String category,
			@RequestParam Integer numQ) {
		return questionService.getQuestionIdsForCategory(category,numQ);

	}
	
	@PostMapping("questions")
	public ResponseEntity<List<QuestionDto>> getQuestionsByIds(@RequestBody List<Integer> ids){
		return questionService.getQuestionsByIds(ids);
		
	}
	
	@PostMapping("marks")
	public ResponseEntity<Integer> getMarks(@RequestBody List<ResponseAnswer> answers) {
		return questionService.getMarks(answers);

	}
}
