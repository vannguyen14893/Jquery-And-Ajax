package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Role;
import com.example.demo.service.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService service;

	@GetMapping(value = "/list-user")
	public String ajax(ModelMap map) {
		List<Role> roles = service.fillAllByUserId();
		map.addAttribute("roles", roles);
		return "demoAjax";
	}

	
}
