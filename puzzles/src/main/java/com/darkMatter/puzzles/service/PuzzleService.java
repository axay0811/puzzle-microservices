package com.darkMatter.puzzles.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.darkMatter.puzzles.dao.PuzzleDao;
import com.darkMatter.puzzles.dao.QuestionDao;
import com.darkMatter.puzzles.model.Puzzle;
import com.darkMatter.puzzles.model.Question;
import com.darkMatter.puzzles.model.QuestionWrapper;
import com.darkMatter.puzzles.model.ResponseAnswer;

@Service
public class PuzzleService {

	@Autowired
	private PuzzleDao puzzleDao;

	@Autowired
	private QuestionDao questionDao;

	public ResponseEntity<String> createPuzzle(String category, int numQ, String title) {
		Puzzle puzzle = new Puzzle();
		puzzle.setTitle(title);

		List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ + 1);

		if (!CollectionUtils.isEmpty(questions)) {
			puzzle.setQuestions(questions);
		} else {
			return new ResponseEntity<>("No Questions Found for Category: " + category + ", failed to create Puzzle",
					HttpStatus.BAD_REQUEST);
		}

		Puzzle createdPuzzle = puzzleDao.savePuzzle(puzzle);

		if (Objects.nonNull(createdPuzzle)) {
			return new ResponseEntity<>("Puzzle Created Successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Issue occured while saving puzzle", HttpStatus.INTERNAL_SERVER_ERROR);

	}

	public ResponseEntity<List<QuestionWrapper>> getPuzzleQuestions(int id) {

		List<QuestionWrapper> questionsForUser = new ArrayList<>();

		Optional<Puzzle> puzzle = puzzleDao.findPuzzleById(id);

		if (puzzle.isPresent()) {
			List<Question> questions = puzzle.get().getQuestions();

			for (Question question : questions) {
				questionsForUser.add(new QuestionWrapper(question.getId(), question.getOption1(), question.getOption2(),
						question.getOption3(), question.getOption4(), question.getQuestiontitle()));
			}
		}
		return new ResponseEntity<List<QuestionWrapper>>(questionsForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calcuateMarks(int puzzleId, List<ResponseAnswer> answersFromUser) {

		int marksObtained = 0;
		Optional<Puzzle> puzzle = puzzleDao.findPuzzleById(puzzleId);

		if (puzzle.isPresent()) {
			List<Question> questionsForPuzzle = puzzle.get().getQuestions();
			for (ResponseAnswer answer : answersFromUser) {
				Question question = questionsForPuzzle.stream().filter(q -> q.getId().equals(answer.getQuestionId()))
						.findFirst().orElse(null);

				if (Objects.nonNull(question)) {
					if (question.getRightanswer().equals(answer.getAnswer()))
						marksObtained++;
				} else {
					return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
				}
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(marksObtained, HttpStatus.OK);
	}

}
