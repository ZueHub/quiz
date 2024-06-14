package com.example.quiz.vo;

import java.util.List;

public class FeedbackRes extends BasicRes {

	private List<Feedback> feedbackList;

	public FeedbackRes() {
		super();

	}

	public FeedbackRes(int statsCode, String message) {
		super(statsCode, message);
		
	}
	
	public FeedbackRes(int statsCode, String message, List<Feedback> feedbackList) {
		super(statsCode, message);
		this.feedbackList = feedbackList;
	}

	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

}
