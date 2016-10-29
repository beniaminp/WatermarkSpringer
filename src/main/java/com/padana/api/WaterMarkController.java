package com.padana.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WaterMarkController {
	@RequestMapping("/service")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@RequestMapping("/addDoc")
	public String addDocument() {
		return "Greetings from Spring Boot!";
	}
}
