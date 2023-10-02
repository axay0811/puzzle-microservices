package com.darkMatter.puzzleservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.darkMatter.puzzleservice.dao.PuzzleDao;
import com.darkMatter.puzzleservice.dto.PuzzleDto;
import com.darkMatter.puzzleservice.dto.QuestionDto;
import com.darkMatter.puzzleservice.feign.PuzzleInterface;
import com.darkMatter.puzzleservice.model.Puzzle;
import com.darkMatter.puzzleservice.model.ResponseAnswer;

@Service
public class PuzzleService {

	@Autowired
	private PuzzleDao puzzleDao;

	@Autowired
	private PuzzleInterface puzzleInterface;

	public ResponseEntity<String> createPuzzle(PuzzleDto puzzleDto) {
		Puzzle puzzle = new Puzzle();

		String category = puzzleDto.getCategory();
		String title = puzzleDto.getTitle();
		Integer numQ = puzzleDto.getNumQuestions();

		List<Integer> qIds = puzzleInterface.getQuestionIdsForCategory(category, numQ).getBody();

		puzzle.setTitle(title);
		puzzle.setQuestionIds(qIds);

		Puzzle createdPuzzle = puzzleDao.savePuzzle(puzzle);

		if (Objects.nonNull(createdPuzzle)) {
			return new ResponseEntity<>("Puzzle Created Successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Issue occured while saving puzzle", HttpStatus.INTERNAL_SERVER_ERROR);

	}

	public ResponseEntity<List<QuestionDto>> getPuzzleQuestions(int id) {

		List<QuestionDto> questions = new ArrayList<>();
		Optional<Puzzle> puzzle = puzzleDao.findPuzzleById(id);

		if (puzzle.isPresent()) {
			List<Integer> questionIds = puzzle.get().getQuestionIds();

			questions = puzzleInterface.getQuestionsByIds(questionIds).getBody();
		}
		return new ResponseEntity<List<QuestionDto>>(questions, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calcuateMarks(int puzzleId, List<ResponseAnswer> answersFromUser) {


		return puzzleInterface.getMarks(answersFromUser);
	}

}
