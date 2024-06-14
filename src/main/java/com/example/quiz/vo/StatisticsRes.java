package com.example.quiz.vo;

import java.time.LocalDate;
import java.util.Map;

public class StatisticsRes extends BasicRes {

	private String quizName;

	private LocalDate startDate;

	private LocalDate endDate;

	private Map<Integer, Map<String, Integer>> countMap;

	public StatisticsRes() {
		super();

	}

	public StatisticsRes(int statsCode, String message) {
		super(statsCode, message);

	}

	public StatisticsRes(int statsCode, String message,String quizName, LocalDate startDate, LocalDate endDate,
			Map<Integer, Map<String, Integer>> countMap) {
		super(statsCode, message);
		this.quizName = quizName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.countMap = countMap;
	}

	public String getQuizName() {
		return quizName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public Map<Integer, Map<String, Integer>> getCountMap() {
		return countMap;
	}

	public void setCountMap(Map<Integer, Map<String, Integer>> countMap) {
		this.countMap = countMap;
	}

}
