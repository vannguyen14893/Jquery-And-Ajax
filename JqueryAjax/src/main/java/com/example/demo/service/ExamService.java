package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.ExamRepository;

@Service
@Transactional
public class ExamService {
@Autowired
private ExamRepository examRepository;

}
