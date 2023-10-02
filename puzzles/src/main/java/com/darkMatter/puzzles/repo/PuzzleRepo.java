package com.darkMatter.puzzles.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darkMatter.puzzles.model.Puzzle;

public interface PuzzleRepo extends JpaRepository<Puzzle, Integer>{

}
