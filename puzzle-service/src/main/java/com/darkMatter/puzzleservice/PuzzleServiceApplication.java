package com.darkMatter.puzzleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PuzzleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuzzleServiceApplication.class, args);
	}

}
