package com.thrive.common.testdata.utils;

public enum TestCategory {

	
	USER_AUTHENTICATION("User Authentication & Authorization"),
	USER_AUTHENTICATION_API("API - User Authentication & Authorization"),
	USER_PROFILE("User Profile"),
	USER_PROFILE_UPDATE("User profile update"),
	DASHBOARD("Dashboard"),
	INSIGHTS("Insights"),
	SESSION_FILTERS("Session filters operations"),
	HOME_HEADER_FOOTER("User Header & Footer"),
	USER_ONBOARDING_INVITE("User Onboarding | Invite Users"),
	USER_ONBOARDING_INVITE_API("API - User Onboarding | Invite Users"),
	USER_ONBOARDING_REGISTER("User Onboarding | Register Users"),
	USER_ONBOARDING_REGISTER_API("API - User Onboarding | Register Users"),
	CATEGORY_EXPERTISE_MANAGEMENT("Category & Expertise Management"),
	BOOK_SESSION_SINGLE("Book Session | One to One"),
	BOOK_SESSION_MULTIPLE("Book Session | Multiple"),
	COACH_BIO("Coach Bio"),
	CMS("CMS"),
	TRANSLATION("Translation"),
	EMAIL_EDITOR("Email Editor"),
	PASSWORD_POLICY("Password Policy"),
	ROLES_PERMISSION("Roles & Permissions"),
	USER_MANAGEMENT_SEARCH_VIEW("User Management | Search & Filters"),
	USER_MANAGEMENT_ACTIONS("User Management | Actions"),
	CREDIT_MANAGEMENT("Credit Management"),
	CREDIT_CONSUMPTION("Credit Consumption"),
	EVENTS("Events"),
	SESSIONS("Sessions"),
	ARRANGE_SESSIONS_VALIDATIONS("Arrange Sessions Validations"),
	VALIDATE_MULTIPLE_SESSIONS("Validate Multiple Sessions Booking"),
	BOOK_A_SESSION_LINK("Validating BookASession Link"),
	COACH_PROMPT_LINK("Validating Coach Prompt Link ")
	;
	
	private String value;

	private TestCategory(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
