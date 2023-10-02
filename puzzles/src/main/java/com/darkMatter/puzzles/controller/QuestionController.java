package com.darkMatter.puzzles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darkMatter.puzzles.model.Question;
import com.darkMatter.puzzles.service.QuestionService;

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

	@PostMapping("add")
	public ResponseEntity<String> addQuestions(@RequestBody List<Question> question) {
		return questionService.addQuestions(question);

	}

	@PutMapping("update")
	public ResponseEntity<String> updateQuestions(@RequestBody List<Question> question) {
		return questionService.updateQuestions(question);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteQuestionById(@PathVariable int id) {
		return questionService.deleteQuestionById(id);
	}
}
