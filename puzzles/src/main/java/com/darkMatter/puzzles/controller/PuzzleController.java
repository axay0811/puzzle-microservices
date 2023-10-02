package com.darkMatter.puzzles.controller;

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

import com.darkMatter.puzzles.model.QuestionWrapper;
import com.darkMatter.puzzles.model.ResponseAnswer;
import com.darkMatter.puzzles.service.PuzzleService;

@RestController
@RequestMapping("puzzle")
public class PuzzleController {
	
	@Autowired
	private PuzzleService puzzleService;
	
	@PostMapping()
	public ResponseEntity<String> createPuzzle(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
		return puzzleService.createPuzzle(category,numQ,title);
	}
	
	@GetMapping("puzzle-questions/{id}")
	public ResponseEntity<List<QuestionWrapper>> getPuzzleQuestions(@PathVariable int id){
		
		return puzzleService.getPuzzleQuestions(id);
		
	}
	
	@PostMapping("answer/{puzzleId}")
	public ResponseEntity<Integer> calcuateMarks(@PathVariable int puzzleId, @RequestBody List<ResponseAnswer> answersFromUser){
		return puzzleService.calcuateMarks(puzzleId, answersFromUser);
		
	}

}
