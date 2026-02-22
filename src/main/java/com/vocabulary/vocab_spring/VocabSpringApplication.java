package com.vocabulary.vocab_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VocabSpringApplication {

	public static void main(String[] args) {
		try {
			io.github.cdimascio.dotenv.Dotenv.configure().systemProperties().load();
		} catch (Exception e) {
			// .env file not found, ignore error
		}
		SpringApplication.run(VocabSpringApplication.class, args);
	}

}
