package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.UserFilterKeyword;

@RestController
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping(value = "/users")
	public List<User> getAll(@RequestBody UserFilterKeyword filter) {
		return service.fillAll(filter);
	}
	@GetMapping(value = "/get-user/{userId}")
	public User get(@PathVariable("userId") Integer userId) {
		return service.getUser(userId);
	}
	@DeleteMapping(value = "/delete/{userId}")
	public void delete(@PathVariable("userId") Integer userId) {
		service.delete(userId);
	}
	@PostMapping(value = "/add-user")
	public void add(@RequestBody User user) {
		service.addUser(user);
	}
	@PutMapping(value = "/edit-user")
	public void edit(@RequestBody User user) {
		service.updateUser(user);
	}
	@GetMapping(value = "/list-role")
	public List<Role> getAllByUserId(){
		return service.fillAllByUserId();
	}
}
