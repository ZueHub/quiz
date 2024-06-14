package com.example.quiz.vo;

import java.util.List;

import com.example.quiz.entity.Quiz;

public class SearchRes extends BasicRes {

	private List<Quiz> quizList;

	public SearchRes() {
		super();

	}

	public SearchRes(int statsCode, String message, List<Quiz> quizList) {
		super(statsCode, message);
		this.quizList = quizList;
	}

	public List<Quiz> getQuizList() {
		return quizList;
	}

	public void setQuizList(List<Quiz> quizList) {
		this.quizList = quizList;
	}

}
