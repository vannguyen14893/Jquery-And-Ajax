package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.ExamRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.TestRepository;

@Service
@Transactional
public class QuestionService {
	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private QuestionRepository questionRepsository;
	@Autowired
	private AnswerRepository answerRepsository;
    @Autowired
    private TestRepository testRepsository;
    
}
