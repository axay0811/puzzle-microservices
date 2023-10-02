package com.darkMatter.puzzleservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darkMatter.puzzleservice.dto.PuzzleDto;
import com.darkMatter.puzzleservice.dto.QuestionDto;
import com.darkMatter.puzzleservice.model.ResponseAnswer;
import com.darkMatter.puzzleservice.service.PuzzleService;

@RestController
@RequestMapping("puzzle")
public class PuzzleController {

	@Autowired
	private PuzzleService puzzleService;

	@PostMapping()
	public ResponseEntity<String> createPuzzle(@RequestBody PuzzleDto puzzleDto) {
		return puzzleService.createPuzzle(puzzleDto);
	}

	@GetMapping("puzzle-questions/{id}")
	public ResponseEntity<List<QuestionDto>> getPuzzleQuestions(@PathVariable int id) {

		return puzzleService.getPuzzleQuestions(id);

	}

	@PostMapping("answer/{puzzleId}")
	public ResponseEntity<Integer> calcuateMarks(@PathVariable int puzzleId,
			@RequestBody List<ResponseAnswer> answersFromUser) {
		return puzzleService.calcuateMarks(puzzleId, answersFromUser);

	}

}
