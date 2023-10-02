package com.darkMatter.puzzleservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PuzzleRepo extends JpaRepository<com.darkMatter.puzzleservice.model.Puzzle, Integer> {

}
