package com.example.quiz.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Question {

	private int id;

	private String Title;

	private String options;

	private String type;
	@JsonProperty("necessary")
	private boolean necessary;

	public Question() {
		super();

	}

	public Question(int id, String Title, String options, String type, boolean necessary) {
		super();
		this.id = id;
		this.Title = Title;
		this.options = options;
		this.type = type;
		this.necessary = necessary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isNecessary() {
		return necessary;
	}

	public void setNecessary(boolean necessary) {
		this.necessary = necessary;
	}

}
