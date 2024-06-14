package com.example.quiz.constants;

public enum ResMessage {

	SUCCESS(200,"Success!!"),//
	PARAM_QUIZ_NAME_ERROR(400, "Param quiz nmae error!!"),//
	PARAM_DESCRIPTION_ERROR(400, "Param description error!!"),//
	PARAM_START_DATE_ERROR(400, "Param start date error!!"),
	PARAM_END_DATE_ERROR(400, "Param end date error!!"),//	
	PARAM_QUESTION_LIST_NOT_FOUND(404," Param question list not found!!"),//
	PARAM_QUESTION_ID_ERROR(400," Param question id error!!"),//
	PARAM_QUES_ID_ERROR(400," Param ques id error!!"),//
	PARAM_TITLE_ERROR(400," Param title  error!!"),//
	PARAM_OPTIONS_ERROR(400," Param options  error!!"),//
	PARAM_TYPE_ERROR(400," Param type  error!!"),//
	JSON_PROCESSING_EXCEPTION(400,"Json Processing Exception!!"),//
	UPDATE_ID_NOT_FOUND(404,"Update id not found!!"),//
	PARAM_NAME_IS_REQUIRED(400, "Param name required !!"),//
	PARAM_PHONE_IS_REQUIRED(400, "Param phone required !!"),//
	PARAM_EMAIL_IS_REQUIRED(400, "Param email required !!"),//
	PARAM_AGE_NOT_QUALIFIDE (400,"Param age not qualifind!!"),//
	QUIZ_NOT_FOUND(404,"Quiz not found!!"),//
	ANSWER_IS_REQUIRED(400,"Answer is required!!"),//
	ANSWER_OPTION_IS_NOT_MATCH(400,"Answer option is not match"),//
	ANSWER_OPTION_TYPE_IS_NOT_MATCH(400,"Answer option type is not match"),//
	DUPLICATED_FILLIN(400,"Duplicated fillin");
	private int code;
	
	private String message;

	private ResMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}


	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	
	
}
