package com.example.quiz.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchReq {

	private String name;

	@JsonProperty("star_tDate")
	private LocalDate startDate;

	@JsonProperty("end_tDate")
	private LocalDate endDate;

	public String getName() {
		return name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

}