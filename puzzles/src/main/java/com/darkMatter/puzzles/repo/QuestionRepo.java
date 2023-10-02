package com.darkMatter.puzzles.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.darkMatter.puzzles.model.Question;

public interface QuestionRepo extends JpaRepository<Question, Integer> {

	List<Question> findByCategory(String category);

	@Query(value = "SELECT * FROM Question q where q.category = :category  and rownum < :numQ order by q.option2", nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
