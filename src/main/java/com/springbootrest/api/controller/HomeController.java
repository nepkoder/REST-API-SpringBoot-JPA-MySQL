package com.springbootrest.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@EnableAutoConfiguration
@RestController
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "Spring Boot";
	}
}
