package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Answer;

public interface AnswerRepsository extends JpaRepository<Answer, Integer>{

}
