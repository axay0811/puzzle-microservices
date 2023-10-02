package com.darkMatter.questionservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.darkMatter.questionservice.dto.QuestionDto;
import com.darkMatter.questionservice.model.Question;
import com.darkMatter.questionservice.model.ResponseAnswer;

@Service
public class QuestionService {

	@Autowired
	private com.darkMatter.questionservice.dao.QuestionDao questionDao;

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

	public ResponseEntity<List<Integer>> getQuestionIdsForCategory(String category, Integer numQ) {

		try {
			List<Integer> qIds = questionDao.findRandomQuestionsByCategory(category, numQ + 1).stream()
					.map(q -> q.getId()).collect(Collectors.toList());
			return new ResponseEntity<List<Integer>>(qIds, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(Collections.<Integer>emptyList(), HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<List<QuestionDto>> getQuestionsByIds(List<Integer> ids) {

		List<Question> questions = questionDao.findQuestionByIds(ids);
		List<QuestionDto> questionsForUser = new ArrayList<>();

		for (Question question : questions) {
			questionsForUser.add(new QuestionDto(question.getId(), question.getOption1(), question.getOption2(),
					question.getOption3(), question.getOption4(), question.getQuestiontitle()));
		}
		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getMarks(List<ResponseAnswer> answers) {
		int marksObtained = 0;

		List<Question> questionsForPuzzle = questionDao
				.findQuestionByIds(answers.stream().map(ans -> ans.getQuestionId()).collect(Collectors.toList()));
		for (ResponseAnswer answer : answers) {
			Question question = questionsForPuzzle.stream().filter(q -> q.getId().equals(answer.getQuestionId()))
					.findFirst().orElse(null);

			if (Objects.nonNull(question)) {
				if (question.getRightanswer().equals(answer.getAnswer()))
					marksObtained++;
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}

		return new ResponseEntity<Integer>(marksObtained, HttpStatus.OK);
	}

}
