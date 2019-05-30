package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Test;
import com.example.demo.service.TestService;

@RestController
public class TestController {
	@Autowired
	private TestService service;

	@PostMapping(value = "/add-test/type/{type}/level/{level}/numberRandom/{numberRandomQuestion}")
	public void createTest(@PathVariable("type") String type, @PathVariable("level") String level,
			@PathVariable("numberRandomQuestion") Integer numberRandomQuestion, @RequestBody Test test) {
		service.createTest(type, level, numberRandomQuestion, test);
	}
	@GetMapping(value="/test/{testId}")
	public Test getTest(@PathVariable("testId") Integer testId) {
		return service.getTest(testId);
	}
}
