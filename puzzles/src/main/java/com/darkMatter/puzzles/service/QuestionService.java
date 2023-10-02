package com.darkMatter.puzzles.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.darkMatter.puzzles.dao.QuestionDao;
import com.darkMatter.puzzles.model.Question;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {

		try {
			return new ResponseEntity<>(questionDao.getAllQuestions(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(Collections.<Question>emptyList(), HttpStatus.EXPECTATION_FAILED);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {

		try {
			return new ResponseEntity<>(questionDao.findQuestionsByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(Collections.<Question>emptyList(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestions(List<Question> question) {

		try {
			List<Question> savedQuestions = questionDao.saveQuestions(question);

			if (!CollectionUtils.isEmpty(savedQuestions)) {
				return new ResponseEntity<>("Questions added successfully", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Failed to add questions", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> updateQuestions(List<Question> question) {
		try {
			List<Question> updatedQuestions = questionDao.saveQuestions(question);

			if (!CollectionUtils.isEmpty(updatedQuestions)) {
				return new ResponseEntity<>("Questions updated successfully", HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Failed to update questions", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> deleteQuestionById(int id) {

		try {
			Optional<Question> question = questionDao.findQuestionById(id);

			if (question.isPresent()) {
				questionDao.deleteQuestion(question.get());
			} else {
				return new ResponseEntity<>("Question doesn't exist for given Id, can't delete.",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Question deleted successfully.", HttpStatus.OK);
	}

}
