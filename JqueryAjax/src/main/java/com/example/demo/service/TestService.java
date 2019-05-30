package com.example.demo.service;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.entity.Test;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.ExamRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.TestRepository;

@Service
@Transactional
public class TestService {
	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private TestRepository testRepository;
	@PersistenceContext
	private EntityManager entityManager;
	public static final String SQL = "select q from Question q ";

	public List<Test> findByExamId(Integer examId) {
		return testRepository.findByExam_Id(examId);
	}

	public void createTest(String type, String level, Integer numberRandomQuestion, Test test) {
		StringBuilder builder = new StringBuilder(SQL);
		builder.append(" WHERE 1=1 ");
		if (StringUtils.isNotBlank(type)) {
			builder.append(" AND q.type= '" + type + "' ");
		}

		if (StringUtils.isNotBlank(level)) {
			builder.append(" AND q.level= '" + level + "' ");
		}

		List<Question> questions = entityManager.createQuery(builder.toString(), Question.class).getResultList();
		List<Question> randomSeries = questions.subList(0, numberRandomQuestion);
		Collections.shuffle(questions);

		for (Question question : randomSeries) {
			List<Answer> answers = question.getAnswers();
			Collections.shuffle(answers);
			answers.forEach(item -> System.out.println(item.getId()));
		}

		test.setQuestions(randomSeries);
		test.setExam(examRepository.getOne(test.getExam().getId()));
		testRepository.save(test);
	}

	public Test getTest(Integer testId) {
		return testRepository.getOne(testId);
	}

	public void mark(Test test) {
		Test test2 = testRepository.getOne(test.getId());
		List<Question> questions = test2.getQuestions();
		for (Question question : questions) {
			List<Answer> answers = question.getAnswers();
			List<Answer> answers2 = answerRepository.findByIsTrueTrueAndQuestion_Id(question.getId());
			for (Answer answer : answers) {
				for (Answer answer2 : answers2) {
                  if(answer.getId()==answer2.getId()) {
                	  answer.setMark(+1);
                  }else {
                	  
                  }
				}
			}
		}
		
	}
}
