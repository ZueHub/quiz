package com.example.quiz.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;



public class DeleteReq {
	@JsonProperty("id_list")
	private List<Integer> idlist;

	public DeleteReq() {
		super();
	
	}

	public DeleteReq(List<Integer> idlist) {
		super();
		this.idlist = idlist;
	}

	public List<Integer> getIdlist() {
		return idlist;
	}

}
