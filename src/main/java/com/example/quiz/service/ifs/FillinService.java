package com.example.quiz.service.ifs;

import com.example.quiz.vo.BasicRes;
import com.example.quiz.vo.FeedbackReq;
import com.example.quiz.vo.FeedbackRes;
import com.example.quiz.vo.FillinReq;
import com.example.quiz.vo.StatisticsRes;

public interface FillinService {

public BasicRes fillin(FillinReq req);
	
	public FeedbackRes feedback(FeedbackReq req);
	
	public StatisticsRes statistics(FeedbackReq req);
}
