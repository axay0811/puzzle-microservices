package com.darkMatter.questionservice.model;

public class ResponseAnswer {

	Integer questionId;
	String answer;
	
	public ResponseAnswer(int questionId, String answer) {
		super();
		this.questionId = questionId;
		this.answer = answer;
	}

	public int getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
