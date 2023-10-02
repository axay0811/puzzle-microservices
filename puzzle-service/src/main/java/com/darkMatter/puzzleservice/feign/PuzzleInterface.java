package com.darkMatter.puzzleservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.darkMatter.puzzleservice.dto.QuestionDto;
import com.darkMatter.puzzleservice.model.ResponseAnswer;

@FeignClient("QUESTION-SERVICE")
public interface PuzzleInterface {

	@GetMapping("question/ids")
	public ResponseEntity<List<Integer>> getQuestionIdsForCategory(@RequestParam String category,
			@RequestParam Integer numQ);

	@PostMapping("question/questions")
	public ResponseEntity<List<QuestionDto>> getQuestionsByIds(@RequestBody List<Integer> ids);

	@PostMapping("question/marks")
	public ResponseEntity<Integer> getMarks(@RequestBody List<ResponseAnswer> answers);

}
