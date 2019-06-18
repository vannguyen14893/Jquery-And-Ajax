package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@RestController
public class ProductController {
	@Autowired
	private ProductRepository repository;

	@GetMapping(value = "/products")
	public List<Product> getAll() {
		return repository.all();
	}
}
