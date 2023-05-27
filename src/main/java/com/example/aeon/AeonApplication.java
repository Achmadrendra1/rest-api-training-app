package com.example.aeon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AeonApplication {

	public static void main(String[] args) {
		SpringApplication.run(AeonApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return String.format("Welcome To Training App API \n\n\nDevelop By Achmad Rendra Artama");
	}

}
