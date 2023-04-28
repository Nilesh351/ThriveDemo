package com.thrive.common.testdata.pojos.book_session;

import java.util.Map;

public class BookSessionSummaryDetails {

	private String 	preQuestionSessionHelpWith;
	private String 	preQuestionContextRelevantConversation;
	private String 	preQuestionShareWithCoach;
	private String 	preQuestionRadioSuccessfulMakingChange;
	private String 	preQuestionRadioRecommend;
	private String  isSuccessfulMakingChange;
	private String  isRecommend;
	private Map<String, String> preQuestionAnswers;
	
	public BookSessionSummaryDetails(String preQuestionSessionHelpWith, String preQuestionContextRelevantConversation,
			String preQuestionShareWithCoach, String preQuestionRadioSuccessfulMakingChange,
			String preQuestionRadioRecommend, String isSuccessfulMakingChange, String isRecommend) {
		super();
		this.preQuestionSessionHelpWith = preQuestionSessionHelpWith;
		this.preQuestionContextRelevantConversation = preQuestionContextRelevantConversation;
		this.preQuestionShareWithCoach = preQuestionShareWithCoach;
		this.preQuestionRadioSuccessfulMakingChange = preQuestionRadioSuccessfulMakingChange;
		this.preQuestionRadioRecommend = preQuestionRadioRecommend;
		this.isSuccessfulMakingChange = isSuccessfulMakingChange;
		this.isRecommend = isRecommend;
	}
	
	public BookSessionSummaryDetails(Map<String, String> preQuestionAnswers) {
		this.preQuestionAnswers = preQuestionAnswers;
		
	}


	public String getPreQuestionSessionHelpWith() {
		return preQuestionSessionHelpWith;
	}


	public void setPreQuestionSessionHelpWith(String preQuestionSessionHelpWith) {
		this.preQuestionSessionHelpWith = preQuestionSessionHelpWith;
	}


	public String getPreQuestionContextRelevantConversation() {
		return preQuestionContextRelevantConversation;
	}


	public void setPreQuestionContextRelevantConversation(String preQuestionContextRelevantConversation) {
		this.preQuestionContextRelevantConversation = preQuestionContextRelevantConversation;
	}


	public String getPreQuestionShareWithCoach() {
		return preQuestionShareWithCoach;
	}


	public void setPreQuestionShareWithCoach(String preQuestionShareWithCoach) {
		this.preQuestionShareWithCoach = preQuestionShareWithCoach;
	}


	public String getPreQuestionRadioSuccessfulMakingChange() {
		return preQuestionRadioSuccessfulMakingChange;
	}


	public void setPreQuestionRadioSuccessfulMakingChange(String preQuestionRadioSuccessfulMakingChange) {
		this.preQuestionRadioSuccessfulMakingChange = preQuestionRadioSuccessfulMakingChange;
	}


	public String getPreQuestionRadioRecommend() {
		return preQuestionRadioRecommend;
	}


	public void setPreQuestionRadioRecommend(String preQuestionRadioRecommend) {
		this.preQuestionRadioRecommend = preQuestionRadioRecommend;
	}


	public String isSuccessfulMakingChange() {
		return isSuccessfulMakingChange;
	}


	public void setSuccessfulMakingChange(String isSuccessfulMakingChange) {
		this.isSuccessfulMakingChange = isSuccessfulMakingChange;
	}


	public String isRecommend() {
		return isRecommend;
	}


	public void setRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Map<String, String> getPreQuestionAnswers() {
		return preQuestionAnswers;
	}

	public void setPreQuestionAnswers(Map<String, String> preQuestionAnswers) {
		this.preQuestionAnswers = preQuestionAnswers;
	}
	
}
