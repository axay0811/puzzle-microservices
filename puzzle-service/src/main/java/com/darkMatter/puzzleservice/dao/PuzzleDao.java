package com.darkMatter.puzzleservice.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.darkMatter.puzzleservice.model.Puzzle;

@Repository
public class PuzzleDao {
	
	@Autowired
	private com.darkMatter.puzzleservice.repo.PuzzleRepo puzzleRepo;

	public Puzzle savePuzzle(Puzzle puzzle) {
		return puzzleRepo.save(puzzle);
	}

	public Optional<Puzzle> findPuzzleById(int id) {
		return puzzleRepo.findById(id);
		
	}
	
}
