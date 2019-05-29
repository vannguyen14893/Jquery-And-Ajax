package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Test;

public interface TestRepsository extends JpaRepository<Test, Integer>{

}
