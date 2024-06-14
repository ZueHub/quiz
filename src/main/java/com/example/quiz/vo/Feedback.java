package com.example.quiz.vo;

import java.time.LocalDateTime;

public class Feedback {

	private String userName;

	private LocalDateTime fillinDateTime;

	private FeedbackDetail feedbackDetail;

	public Feedback() {
		super();

	}

	public Feedback(String userName, LocalDateTime fillinDateTime, FeedbackDetail feedbackDetail) {
		super();
		this.userName = userName;
		this.fillinDateTime = fillinDateTime;
		this.feedbackDetail = feedbackDetail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDateTime getFillinDateTime() {
		return fillinDateTime;
	}

	public void setFillinDateTime(LocalDateTime fillinDateTime) {
		this.fillinDateTime = fillinDateTime;
	}

	public FeedbackDetail getFeedbackDetail() {
		return feedbackDetail;
	}

}
