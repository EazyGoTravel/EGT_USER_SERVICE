package com.egt.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

	@GetMapping("/hello")
	public String sayHello(@RequestParam("name") String name) {
		return "Hello "+name;
	}

}
