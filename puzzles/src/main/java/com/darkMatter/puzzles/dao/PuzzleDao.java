package com.darkMatter.puzzles.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.darkMatter.puzzles.model.Puzzle;
import com.darkMatter.puzzles.repo.PuzzleRepo;

@Repository
public class PuzzleDao {
	
	@Autowired
	private PuzzleRepo puzzleRepo;

	public Puzzle savePuzzle(Puzzle puzzle) {
		return puzzleRepo.save(puzzle);
	}

	public Optional<Puzzle> findPuzzleById(int id) {
		return puzzleRepo.findById(id);
		
	}
	
}
