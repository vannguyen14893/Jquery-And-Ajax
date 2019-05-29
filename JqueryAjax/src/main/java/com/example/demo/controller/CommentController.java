package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CommentService;

@RestController
public class CommentController {
	@Autowired
	private CommentService service;

	@DeleteMapping(value = "/delete/child/{id}/user/{userId}")
	public void deleteCommentChild(@PathVariable("id") Integer id, @PathVariable("userId") Integer userId) {
		service.deleteCommentChild(id, userId);
	}
	@DeleteMapping(value = "/delete/{id}/user/{userId}")
	public void deleteComment(@PathVariable("id") Integer id, @PathVariable("userId") Integer userId) {
		service.deleteComment(id, userId);
	}
	@GetMapping(value="/check/{userId}")
	public String check(@PathVariable("userId") Integer userId) {
		if(service.checkButtonComment(userId)) {
			return "";
		}else {
			return "Khong duoc";
		}
	}
}
