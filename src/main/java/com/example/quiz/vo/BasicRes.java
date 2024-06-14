package com.example.quiz.vo;

public class BasicRes {
 
	
	private int statsCode;

	private String message;

	public BasicRes() {
		super();
		
	}

	public BasicRes(int statsCode, String message) {
		super();
		this.statsCode = statsCode;
		this.message = message;
	}

	public int getStatsCode() {
		return statsCode;
	}

	public void setStatsCode(int statsCode) {
		this.statsCode = statsCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
