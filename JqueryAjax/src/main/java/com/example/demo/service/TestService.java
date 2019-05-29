package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Test;
import com.example.demo.repository.AnswerRepsository;
import com.example.demo.repository.ExamRepository;
import com.example.demo.repository.QuestionRepsository;
import com.example.demo.repository.TestRepsository;

@Service
@Transactional
public class TestService {
	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private QuestionRepsository questionRepsository;
	@Autowired
	private AnswerRepsository answerRepsository;
    @Autowired
    private TestRepsository testRepsository;
    
    public List<Test> findByExamId(Integer examId){
    	return testRepsository.findByExam_Id(examId);
    }
}
