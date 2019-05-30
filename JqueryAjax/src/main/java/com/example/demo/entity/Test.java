package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Test implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "test_name")
	private String testName;
	private Integer status;
	@ManyToOne
	@JoinColumn(name = "exam_id")
	@JsonIgnoreProperties("tests")
	private Exam exam;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH })
	@JoinTable(name = "question_test", joinColumns = { @JoinColumn(name = "test_id") }, inverseJoinColumns = {
			@JoinColumn(name = "question_id") })
	@JsonIgnoreProperties("tests")
	private List<Question> questions = new ArrayList<Question>();

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public Test(Integer id, String testName, Integer status, Exam exam, List<Question> questions) {
		super();
		this.id = id;
		this.testName = testName;
		this.status = status;
		this.exam = exam;
		this.questions = questions;
	}
	public Test() {
		super();
	}

}
