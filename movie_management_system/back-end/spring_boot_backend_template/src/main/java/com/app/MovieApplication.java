package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

// annotation to let know what this class does.
@SpringBootApplication
// to let know java that this class is rest controller class
@RestController
public class MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}
	
	// first end-point
	// this lets the framework the following method is get end point
//	@GetMapping("/root")
//	public String apiRoot() {
//		return "Hello world";
//	}

}
