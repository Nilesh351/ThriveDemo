package com.thrive.common.testdata.utils;

import java.util.Map;

import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public enum ThriveAppSharedData {

	//User Data
	CATEGORY_TYPE("private.manage_categories.add_category"),
	CATEGORY_TOPIC("private.manage_categories.label_topic"),
	CATEGORY_EXPERTISE("private.manage_categories.label_expertise"),
	CREDIT_TYPE("Default"),
	MONTH("Jan"),
	MONTH_COL("Jan"),
	TYPE("admin.session.one_on_one"),
	TYPE_COL("One-to-one"),
	STATUS("Expired"),
	STATUS_COL("Expired"),
	TIMEFRAME("LAST 30 DAYS"),
	EVENTSTATUS("Client Cancelled"),
	EVENTSEARCH("Workshop"),
	DEPARTMENTS("IT"),
	INSIGHT_COACHES("Internal Coaches"),
	PREMEETING_STATUS("Completed"),

	COACHTYPE("Internal coach"),
	CLIENT_LANGUAGE("Russian"),
	CLIENT_DATEFORMAT("mm-dd-yyyy"),
	CLIENT_WORK_DURATION("5 years"),
	TIME_FORMAT("12"),
	CLIENT_TIME_FORMAT("24"),
	CLIENT_NATIONALITY("Irish"),
	CLIENT_COUNTRY("Ireland"),
	CLIENT_ETHNICITY("Middle Eastern"),
	CLIENT_AGE_RANGE("21-25"),
	PDFEXTENSION(".pdf"),
	EXTENSION(".csv"),
	DATE("2022-07-06"),
	FILETYPE("PDF"),
	
	
	ROLE("Internal Coach"),
	EMPLOYEE_DEPARTMENT("Project Management"),
	WORKSHOP_STATUS("COMPLETED"),
	

	INVITED_TAB("Invite"),

	
	//User Data Stage
	ENTERPRISE_NAME_STAGE("Test Enterprise UATG"),
	ARCHIVED_ENT_NAME("aj new ent06"),
	
	//Toaster Messages
	COACH_UPDATE_DETAILS_MESSAGE("ui.messages.user_details_updated"),
	//COACH_UPDATE_DETAILS_MESSAGE("Your user details have been updated."),
	COACH_UPDATE_DETAILS_MESSAGE_FRENCH("Vos informations utilisateur ont été mises à jour."),
	ENTERPRISE_UPDATE_DETAILS_MESSAGE("ui.messages.this_enterprise_details_updated"),
	CATEGORY_CREATED_MESSAGE("Category added successfully"),
	TOPIC_CREATED_MESSAGE("Topic added successfully"),
	EXPERTISE_CREATED_MESSAGE("Expertise added successfully"),
	INVITED_CLIENT_DELETED_MESSAGE("Invited client has been deleted successfully."),
	ARCHIVED_CLIENT_DELETED_MESSAGE("Client has been successfully deleted"),
	CLIENT_ARCHIVED_SUCCESSFULLY_MESSAGE("Client(s) has been successfully archived"),
	CLIENT_ARCHIVED_SUCCESSFULY_MESSAGE_RM("Clients has been successfully archived"),
	CLIENT_ENABLED_SUCCESSFULLY_MESSAGE("Client has been successfully enabled"),
	CLIENT_DELETED_SUCCESSFULY_MESSAGE("Client has been successfully deleted"),
	INVITED_CLIENT_DELETED_SUCCESSFULY_MESSAGE("ui.messages.invited_client_has_been_deleted"),
	ARCHIVE_ENT_SUCCESSFUL_MESSAGE("Enterprise has been successfully archived"),
	UNARCHIVE_ENT_SUCCESSFUL_MESSAGE("Enterprise has been successfully unarchived"),
	ENT_CREATED_MESSAGE("Enterprise created successfully"),
	ENT_CREATED_MESSAGE_RM("ui.messages.enterprise_created"),
	ENT_REINVITE_MESSAGE("ui.messages.invitation_resent"),
	ENT_DELETE_INVITED_MESSAGE("ui.messages.enterprise_has_been_deleted"),
	WORKSHOP_BOOKING_TOASTER("Booking is confirmed."),
	EMPLOYEE_REINVITE_MESSAGE("Invitation has been resent to this user."),
	INVITED_EMPLOYEE_DELETE_MESSAGE("Invited client has been deleted successfully."),
	ZOOM_LINK_ADDED("Zoom Link Added Successfully"),
	SESSION_BOOKED_EA_NOTIFICATION("Your coaching session’s booked"),
	SESSION_BOOKED_COACH_NOTIFICATION("You’ve got a booking from Thrive!"),
	SESSION_BOOKED_CLIENT_NOTIFICATION("Reminder: Your Thrive booking is coming up"),
	CLIENT_DETAILS_UPDATED("ui.messages.user_details_updated"),
	INVALID_EMAIL_NOTIFICATION("This needs to be a valid email"),
	ACCOUNT_MANAGER_ARCHIVE_MESSAGE("Account manager has been successfully archived"),
	ACCOUNT_MANAGER_ENABLE_MESSAGE("Account manager has been successfully enabled"),
	ACCOUNT_MANAGER_DELETE_MESSAGE("Account manager has been successfully deleted"),
	ACCOUNT_MANAGER_REINVITE_MESSAGE("ui.messages.invitation_resent"),
	COACH_REINVITE_MESSAGE("ui.messages.invitation_resent"),
	COACH_DELETE_MESSAGE("Coach has been successfully deleted"),
	COACH_DELETE_INVITED_MESSAGE("ui.messages.coach_has_been_deleted"),
	COACH_ARCHIVED_MESSAGE("Coach has been successfully archived"),
	COACH_ENABLED_MESSAGE("Coach has been successfully enabled"),
	ENT_ARCHIVE_MESSAGE("Enterprise has been successfully archived"),
	ENT_ENABLE_MESSAGE("Enterprise has been successfully enabled"),
	ENT_DELETE_MESSAGE("Enterprise has been successfully deleted"),
	WORKSHOP_CREATED_MESSAGE("Workshop has been created successfully"),
	//REMOVE_CREDIT_NOTIFICATION("Credits have been removed successfully."),
	REMOVE_CREDIT_NOTIFICATION("shared.modals.admin_remove_credits.credits_removed_successfully"),
	WORKSHOP_UPDATED_SUCCESSFULLY("Workshop updated successfully"),
	PREMEETING_UPDATED_SUCCESSFULLY("Details updated successfully"),
	LOGIN_ERROR_MESSAGE("Sorry, the details you entered were not recognised. Please use the support bubble at the bottom-right of your screen if you are still experiencing issues."),
	LOGIN_ERROR_MESSAGE_RM("Sorry, the details you entered were not recognized. Please use the Forgot Your Password link below or use the Contact Us link if you are still experiencing issues."),
	EMAIL_CHANGED_TOASTER("Thank you for registering. You can now log in with your email address and password."),
	SESSION_STATUS_UPDATE_TOASTER("Your status has been updated"),
	
//	CLIENT_REG_SUCCESS_MESSAGE("Thank you for registering. You can now log in with your email address and password."),
	CLIENT_REG_SUCCESS_MESSAGE("ui.messages.registered_verify"),
//	ACCOUNT_MANAGER_REGISTER_MESSAGE("Thank you for registering, once our team has approved your registration you will receive an email confirmation and have access to the system."),
	ACCOUNT_MANAGER_REGISTER_MESSAGE("ui.messages.registered_successful"),
//	COACH_REGISTER_MESSAGE("Thank you for registering, once our team has approved your registration you will receive an email confirmation and have access to the system."),
	COACH_REGISTER_MESSAGE("ui.messages.registered_successful"),
	ACCOUNT_MGR_WELCOME_MESSAGE("Welcome, Account Manager! Let's get started and create your account"),
	ACCOUNT_MGR_WELCOME_MESSAGE_FRENCH("Bienvenue sur RightCoach™ ! Commencons et créons votre compte."),
	ACCOUNT_MGR_ACNT_VERIFICATION_MESSAGE("Account verification"),
	//REMOVE_CREDIT_MESSAGE_EA("Credits have been successfully removed."),
	REMOVE_CREDIT_MESSAGE_EA("ui.messages.credits_removed"),
	LANGUAGE_TYPE_ENGLISH_US("English-US"),
	LANGUAGE_TYPE_FRENCH("French"),
	LANGUAGE_TYPE_IN_FRENCH("languages.fr"),
	LANGUAGE_EN("en"),
	LOCALE_EN("en"),
	TIMEZONE("Asia/Calcutta"),
	GENDER("Female"),
	CITY("Pune"),
	COUNTRY("Cuba"),
	COUNTRY_NAME_FRENCH("Inde"),
	ENTDEPT("Accounts"),
	//ENTUNIT_ASIA("Asia"),
	ENTUNIT("data.client.regions.asia"),
	POSTCODE("411028"),
	CLIENTPOSITION("Senior manager"),
	CLIENTREGION("North Africa"),
//	INVITE_INTERNAL_COACH_NOTIFICATION("The invitation email has been sent to email address you have entered."),
	INVITE_INTERNAL_COACH_NOTIFICATION("ui.messages.invitation_sent_to_entered_email"),
	//INVITE_ENTERPRISE_NOTIFICATION("The invitation email has been sent to email address you have entered."),
	INVITE_ENTERPRISE_NOTIFICATION("ui.messages.invitation_sent_to_entered_email"),
	
//	INVITE_CLIENT_NOTIFICATION("The invitation email has been sent to email address you have entered."),
	INVITE_CLIENT_NOTIFICATION("ui.messages.invitation_sent_to_entered_email"),
//	INVITE_ACCOUNT_MANAGER_NOTIFICATION("The invitation email has been sent to email address you have entered."),
	INVITE_ACCOUNT_MANAGER_NOTIFICATION("ui.messages.invitation_sent_to_entered_email"),
	
	EXPIRYDATE("19/08/2023"),
	//ALLOCATE_CREDIT_NOTIFICATION("Credits have been successfully allocated."),
	ALLOCATE_CREDIT_NOTIFICATION("employees.allocate.credits_successfully"),
	CLIENTCREDITEXPIRYDATE("19/08/2022"),
	IE_11_DRIVER_32_VERSION_3_141_59("3.141.59"),
	
	//Email Messages & Subjects
	ENT_WELCOME_EMAIL_SUBJECT("Welcome! Let us get started and create your account"),
	ENT_WELCOME_EMAIL_SUBJECT_RM("Welcome! Let's get started and create your account"),
	CLIENT_WELCOME_EMAIL_SUBJECT_RM_FRENCH("Bienvenue sur RightCoach"),
	CLIENT_WELCOME_EMAIL_SUBJECT_RM("Welcome to RightCoach™ - Please create your account"),
	CLIENT_WELCOME_EMAIL("Hello! Let us get you Thriving"),
	RESET_PASSWORD("Let us reset your password"),
	RESET_PASSWORD_RM("Please reset your RightCoach password"),
	RESET_PASSWORD_RM_FRENCH("Veuillez réinitialiser votre mot de passe"),
	ENTERPRISE_WELCOME_EMAIL_SUBJECT_RM("Welcome! Let's get started and create your account"),
	ACCOUNT_MANAGER_WELCOME_EMAIL_SUBJECT("Get ready to Thrive"),
	VERIFY_EMAIL_SUBJECT("Last step – Get verified!"),
	VERIFY_EMAIL_SUBJECT_RM("Account verification"),
	ACCOUNT_MANAGER_WELCOME_EMAIL_SUBJECT_RM("Welcome, Account Manager! Let's get started and create your account"),
	ACCOUNT_MANAGER_WELCOME_EMAIL_SUBJECT_FRENCH("Bienvenue sur RightCoach™ ! Commencons et créons votre compte"),
	COACH_WELCOME_EMAIL_SUBJECT("We would love you to Thrive with us"),
	COACH_WELCOME_EMAIL_SUBJECT_RM("Welcome to RightCoach"),
	//COACH_WELCOME_EMAIL_SUBJECT_RM("public.login.google_auth.title_sentence"),
	COACH_WELCOME_MESSAGE("Getting on board with our network of coaches and mentors"),
	COACH_INTERNAL_WELCOME_MESSAGE("Getting on board with our network of internal coaches and mentors"),
	NEW_COACH_ON_BOARD("New coach on board"),
	NEW_INTERNAL_COACH_ON_BOARD("An internal coach is ready for coaching"),
	COACH_REGISTRATION_CONFIRMATION("Thanks for taking the time to register"),
	CLIENT_REG_CONFIRM_EMAIL_SUBJECT("You're nearly ready to access our coaching and mentoring network"),
	SESSION_START_EMAIL_TO_CLIENT("Countdown to your coaching session"),
	SESSION_START_EMAIL_TO_COACH("Reminder: Your Thrive booking is coming up"),
	SESSION_COMPLETION_EMAIL_TO_CLIENT("Thanks for Thriving with me"),
	SESSION_COMPLETION_EMAIL_TO_COACH("Another session in the bag"),
	SESSION_BOOKING_EMAIL_TO_COACH("You have a booking from Thrive"),
	SESSION_BOOKING_EAMIL_TO_CLIENT("Your coaching session is booked"),
	SESSION_RESCHEDULE_NOTIFICATION("Same session, new time"),
	
	// Sort By Options
	MOST_BOOKED("Most Booked"),
	LEAST_BOOKED("Least Booked"),
	
	//Coommon Password
	COMMON_PAASWORD("HEAPtrace@2020"),
	
	//Client Details
	STREET_ADDRESS_SEARCH("2000 Walnut Avenue"),
	STREET_ADDRESS_FULL("2000 Walnut Avenue Fremont"),
	AGE("31-35"),
//	ETHINICITY("East Asian"),
	ETHINICITY("ui.ethnicity.east_asian"),
//	NATIONALITY("Indian"),
	NATIONALITY("data.nationality.indian"),

//	WORK_DURATION("2 years"),
	WORK_DURATION("data.client.work-duration.2_years"),
	DEPARTMENT("Finance"),
//	DEPARTMENT_PROJ_MANAGEMNT("IT"),
	DEPARTMENT_FINANCE("data.client.departments.finance"),
	DEPARTMENT_PRODUCTION("data.client.departments.production"),
//	INDUSTRY_ACCOUNTANCY("Accountancy"),
	INDUSTRY_ACCOUNTANCY("industries.accountancy"),
//	REGION("Asia"),
	REGION("data.client.regions.asia"),
	POSTION("Manager"),
//	POSTION("data.coach.coached-people.manager"),
	DATE_FORMAT("dd-mm-yyyy"),
	INDUSTRY("industries.education"),
	ENGINEERING_INDUSTRY("industries.engineering"),
	
	
	//Error Messages
	MANDATORY_FIELD_ERROR_MSG("shared.support_form.required_field"),
	PRIVACY_NOTICE_ERROR_MSG("ui.messages.privacy_notice.required_field"),
	PRIVACY_NOTICE_ERROR_MSG_RM("ui.messages.privacy_notice.required_field"),
	EXPERTISE_SELECT_ERROR_MSG("register.coach.step5.expertise_required"),
	CLIENT_CREDITS_ERROR_MESSAGE("shared.modals.admin_remove_credits.lower_numbers_of_credits_avaiable"),
//	PRIVACY_NOTICE_ERROR_MSG("This is a required question. If you do not consent to your personal data being processed by Thrive Partners Ltd, please contact your employer."),
//	PRIVACY_NOTICE_ERROR_MSG_RM("This is a required question. If you do not consent to your personal data being processed by Right Management, please contact your employer."),
//	EXPERTISE_SELECT_ERROR_MSG("Please select at least one expertise that you can coach in"),
//	CLIENT_CREDITS_ERROR_MESSAGE("One of the selected users has a lower number of credits than entered"),
	
	//Client Career Details
	ATTITUDE_RADIO_OPTION("I want to learn as much as I can so I can do it for myself one day"),
	
	//Coach Details
	LANGUAGE_ENGLISH_IN_FRENCH("Anglais"),
	LANGUAGE_ENGLISH("languages.en"),
	COACH_EXPERTISE("Achieving goals"),
	INTERNALCOACH_EXPERTISE("Test Internal Topic UATG"),
//	EMPLOYMENT_STATUS("Self-employed"),
	EMPLOYMENT_STATUS("data.coach.employment-status.self_employed"),
//	WORK_AS("Sole Trader"),
	WORK_AS("data.coach.company-types.sole_trader"),
//	COACH_OFFER("Individual coaching"),
	COACH_OFFER("private.coach.individual_coaching"),
//	TECH_PLATFORM_MAC("Mac"),
	TECH_PLATFORM_MAC("data.coach.tech-expertise.mac"),
//	COMPANY_TYPE_PUBLIC("Public sector"),
	COMPANY_TYPE_PUBLIC("data.coach.coached-companies.public_sector"),
//	SECTOR_EDUCATION("Education"),
	SECTOR_EDUCATION("industries.education"),
//	GENDER_MALE("Male"),
	GENDER_MALE("data.gender.male"),
	QUALIFICATION("BTech"),
	COACH_TYPE_TRAINING_COACH("admin.user_management.header_training_coach"),
	COACH_TYPE_GLOBAL_COACH("admin.user_management.global_coach"),
	MOST_BOOKED_OPTION("admin.user_management.coaches.most_booked"),
	LEAST_BOOKED_OPTION("admin.user_management.coaches.least_booked"),
	//Credit Consumption
	
	CREDIT_TYPE_ENTERPRISE("ENTERPRISE"),
	
	//Enterprise details
	SESSION_LENGTH("1 h"),
	
	//Create session deatils
	EVENT_LENGTH("60 minutes"),
	INTERACTIVITY("Polls"),
	CUSTOM_INTERACTIVITY("Polls"),

	//Book Seesion details
	
	SESSION_CREDIT("1 credit"),
	SESSION_CREDIT_FRENCH("1 Crédit"),
	SESSION_DATE("24"),
	SESSION_TIME("10:00 pm"),
	SESSION_EXPERTISE("Test-Auto-Internal-Expertise-1"),
	SESSION_FOLLOW_ON("Follow-On Session"),
	SESSION_HELP("On the test Automation"),
	SESSION_BACKGROUND("Automation basic knowledge"),
	SESSION_SHARE("Session will help"),
	SESSION_OPTION("No"),
	SESSION_CHOICE("Yes"),
	SESSION_COACHNAME("Test-Auto-Ent-1-Int-Coach1-First T."),
	SESSION_CLIENTNAME("Test-Auto-Client1-First Test-Auto-Client1-Last"),
			
	//Invited client
	INVITED_CLIENT_NAME("ajile first ajile last"),
	ACTION_OPTION("DELETE CLIENT"),
	
	// User Roles
	ROLE_ENTERPRISE_ADMIN("ENTERPRISE ADMIN"),
	ROLE_ACCOUNT_MANAGER("ACCOUNT MANAGER"),
	ROLE_CLIENT("CLIENT"),
	ROLE_COACH("COACH"),
	ROLE_INTERNAL_COACH("INTERNAL COACH"),
	ROLE_SUPER_ADMIN("SUPER ADMIN"),
			
	//Archied client
	ARCHIVED_CIENT_NAME("test client"),
	ACTION_NAME("DELETE CLIENT"),
	//PRE_QUESTION_SESSION_HELP_WITH("What specifically do you want this coaching session to help with"),
	PRE_QUESTION_SESSION_HELP_WITH("questions.what_specifically_do_you_want_this_coaching_session_to_help_with.label"),
	//PRE_QUESTION_CONTEXT_RELEVANT_CONVERSATION("What background or context is relevant for this conversation"),
	PRE_QUESTION_CONTEXT_RELEVANT_CONVERSATION("questions.what_background_or_context_is_relevant_for_this_conversation.label"),
	//PRE_QUESTION_SHARE_WITH_COACH("Is there anything else that would be helpful to share with your coach"),
	PRE_QUESTION_SHARE_WITH_COACH("questions.is_there_anything_else_that_would_be_helpful_to_share_with_your_coach.label"),
	PRE_QUESTION_RADIO_SUCCESSFUL_MAKING_CHANGES("Were you successful in making changes since your last session"),
	PRE_QUESTION_RADIO_DID_YOU_RECOMMEND("Did you recommend MyThrive to someone else"),
	
	YES("Yes"),
	NO("No"),
	
	// Session status action with coach login
	END_SESSION("End Session"),
	SESSION_COMPLTED_OFFLINE("Session completed offline"),
	CLIENT_DID_NOT_ATTEND("Client did not attend"),
	
	// Session status with SA/AM Login
	COMPLETED("Completed"),
	COMPLETED_OFFLINE("Completed Offline"),
	CANCALLED("Cancelled"),
	CANCALLED_EXCEPTION("Cancelled (Exception)"),
	COACH_CANCELLED("Coach cancelled"),
	CLIENT_CANCELLED("Client cancelled"),
	COACH_DID_NOT_ATTEND("Coach did not attend"),
	LATE_CANCELLATION("Late cancellation"),
	
	
	
	
	//client
	Action("ARCHIVE CLIENT"),
	
	//Error message
	SELECTED_ALL_TIME_SLOT_ERROR("You have selected all available time slots for this date. Please select another date."),
	
	//Email & Reporting
	
	EMAIL_BODY("Hi Team,<br/>"
		      + "<p>Please find the test automation execution result, click on below link.</p> <br/>" 
		      + "<a href='<REPORT_URL>'>Test Automation Result</a> <br/>"
		      + "This link will expire in 24 hrs."
		      + "<br/>"
		      + "<br/>"
		      + "Regards,<br> QA Team"),
	
	EMAIL_SUBJECT("Heroku | Test Automation Result"),
	


	;
	
	private String value;

	private ThriveAppSharedData(String value) {
		this.value = value;
	}


	public String getValue() {
		String val="";

		try {
			if(BaseTestPage.getResultByKey(value) != null) {
				val= BaseTestPage.getResultByKey(value);
			}
		} catch (Exception e) {
			val = value;
		}
		return val;
	}
	
}