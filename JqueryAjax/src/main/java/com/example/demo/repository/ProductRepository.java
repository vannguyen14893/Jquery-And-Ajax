package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query(value = "SELECT p.id,p.name,p.description,p.price,p.total_import,p.total_sell ,new_function(total_import,total_sell ) as total FROM product p ", nativeQuery = true)
	List<Product> getAll();
	
	@Query(value = "Select (Select Count(*) From product ) - Count(*)" + 
			"From product" + 
			"Where id <= 1;", nativeQuery = true)
	List<Product> all();
}
