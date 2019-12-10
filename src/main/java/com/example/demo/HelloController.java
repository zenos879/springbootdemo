package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/test")
	public String hello() {
		return "Hello world! Welcome to start SpringBoot!";
	}
}