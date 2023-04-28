package com.thrive.common.testdata.pojos;

import com.thrive.ui.core.BaseTestPage;

public class CategoryDetails extends BaseTestPage{
	
	private String categoryNameDefault;
	private String categoryFr;
	private String categoryUk;
	private String topicNameDefault;
	private String topicFr;
	private String topicUk;
	private String expertiseNameDefault;
	private String expertiseFr;
	private String expertiseUk;
	
	public CategoryDetails(String category, String topic, String expertise) {
		super();
		this.categoryNameDefault = category;
		this.topicNameDefault = topic;
		this.expertiseNameDefault = expertise;
	}
	
	public CategoryDetails(String categoryNameDefault, String categoryFr, String topicNameDefault, String topicFr, String expertiseNameDefault,
			String expertiseFr) {
		super();
		this.categoryNameDefault = category;
		this.categoryFr = categoryFr;
		this.topicNameDefault = topic;
		this.topicFr = topicFr;
		this.expertiseNameDefault = expertise;
		this.expertiseFr = expertiseFr;
	}
	
	

	public CategoryDetails(String categoryNameDefault, String categoryFr, String categoryUk, String topicNameDefault,
			String topicFr, String topicUk, String expertiseNameDefault, String expertiseFr, String expertiseUk) {
		super();
		this.categoryNameDefault = categoryNameDefault;
		this.categoryFr = categoryFr;
		this.categoryUk = categoryUk;
		this.topicNameDefault = topicNameDefault;
		this.topicFr = topicFr;
		this.topicUk = topicUk;
		this.expertiseNameDefault = expertiseNameDefault;
		this.expertiseFr = expertiseFr;
		this.expertiseUk = expertiseUk;
	}

	public String getCategoryUk() {
		return categoryUk;
	}

	public void setCategoryUk(String categoryUk) {
		this.categoryUk = categoryUk;
	}

	public String getTopicUk() {
		return topicUk;
	}

	public void setTopicUk(String topicUk) {
		this.topicUk = topicUk;
	}

	public String getExpertiseUk() {
		return expertiseUk;
	}

	public void setExpertiseUk(String expertiseUk) {
		this.expertiseUk = expertiseUk;
	}

	public String getCategoryFr() {
		return categoryFr;
	}

	public void setCategoryFr(String categoryFr) {
		this.categoryFr = categoryFr;
	}

	public String getTopicFr() {
		return topicFr;
	}

	public void setTopicFr(String topicFr) {
		this.topicFr = topicFr;
	}

	public String getExpertiseFr() {
		return expertiseFr;
	}

	public void setExpertiseFr(String expertiseFr) {
		this.expertiseFr = expertiseFr;
	}

	public String getCategory() {
		return categoryNameDefault;
	}

	public void setCategory(String category) {
		this.categoryNameDefault = category;
	}

	public String getTopic() {
		return topicNameDefault;
	}

	public void setTopic(String topic) {
		this.topicNameDefault = topic;
	}

	public String getExpertise() {
		return expertiseNameDefault;
	}

	public void setExpertise(String expertise) {
		this.expertiseNameDefault = expertise;
	}
	

}
